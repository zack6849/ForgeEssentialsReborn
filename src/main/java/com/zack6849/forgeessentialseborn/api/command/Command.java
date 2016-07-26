package com.zack6849.forgeessentialseborn.api.command;

import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.permissions.Group;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;
import com.zack6849.forgeessentialseborn.api.permissions.User;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
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
    public List getCommandAliases() {
        return aliases;
    }

    @Override
    public abstract void execute(MinecraftServer server, ICommandSender sender, String[] args);

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        Group group = Main.getInstance().getPermissionManager().getUserGroup(new User(sender));
        Permission perm = new Permission("command." + getCommandName(), false);
        Main.getInstance().log(Level.DEBUG, "found group " + group.getName() + " for " + sender.getName());
        return group.hasPermission(perm);
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
