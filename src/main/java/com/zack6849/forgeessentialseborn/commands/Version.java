package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.command.Command;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import com.zack6849.forgeessentialseborn.Main;
/**
 * Created by Rory on 7/26/2016.
 */
public class Version extends Command {

    public Version() {
        super("version", "Shows version information");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        sender.addChatMessage(new TextComponentString(Main.VERSION));
    }

}