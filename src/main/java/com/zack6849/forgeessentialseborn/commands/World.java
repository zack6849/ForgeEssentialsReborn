package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import com.zack6849.forgeessentialseborn.Main;

/**
 * Created by Rory on 7/27/2016.
 */
public class World extends Command {

    public World() {
        super("world", "switches player World");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

        String world = sender.getEntityWorld().getWorldInfo().getWorldName();
        User user = new User(sender);

        user.sendMessage(world);
        sender.addChatMessage(new TextComponentString(Main.VERSION));
    }

}
