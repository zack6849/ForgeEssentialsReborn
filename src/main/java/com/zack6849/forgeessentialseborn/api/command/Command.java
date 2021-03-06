package com.zack6849.forgeessentialseborn.api.command;

import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;
import com.zack6849.forgeessentialseborn.utils.PlayerUtils;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zack6849 on 7/23/2016.
 */
public abstract class Command implements ICommand {
    private String name;
    private String usage;
    private List<String> aliases;

    public Command(String name) {
        this(name, "No Syntax Set", new ArrayList<String>());
    }

    public Command(String name, String description) {
        this(name, description, new ArrayList<String>());
    }

    public Command(String name, String description, List<String> aliases) {
        this.name = name;
        this.usage = description;
        this.aliases = aliases;
        ServerCommandManager commandManager = (ServerCommandManager) Main.getInstance().getServer().getCommandManager();
        commandManager.registerCommand(this);
    }

    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return usage;
    }

    @Override
    public List<String> getCommandAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        User user = new User(sender);
        CommandResult result = execute(user, args);
        if (result == CommandResult.PLAYER_ONLY) {
            user.sendMessage("Sorry, you can't use this command unless you're a player!");
        }
        if (result == CommandResult.CONSOLE_ONLY) {
            user.sendMessage("Sorry, this command is console only!");
        }
        if (result == CommandResult.NO_PERMISSION) {
            user.sendMessage("Sorry, you don't have permission to run that command!");
        }
        if (result == CommandResult.FAILURE) {
            user.sendMessage("An unhandled error occurred while processing your command, check the server log for more details");
        }
        if (result == CommandResult.INVALID_SYNTAX) {
            user.sendMessage("Incorrect command usage, refer to documentation");
        }
    }

    public abstract CommandResult execute(User user, String[] args);

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        if (sender instanceof DedicatedServer) {
            //console, always has perm.
            return true;
        }
        if (sender instanceof EntityPlayer) {
            User u = PlayerUtils.fromUUID(((EntityPlayer) sender).getCachedUniqueIdString());
            if (u == null) {
                Main.log(Level.WARN, "Failed to convert EntityPlayer to User!");
                return false;
            }
            Permission perm = new Permission("command." + getCommandName(), false);
            Main.log(Level.DEBUG, "found group " + u.getGroup().getName() + " for " + sender.getName());
            if (u.isPlayer())
                return u.hasPermission(perm);
        }
        Main.log(Level.INFO, sender.getClass().getSimpleName());
        return false;
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
