package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.Location;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by zack6849 on 7/22/2016.
 */
public class Test extends Command {

    public Location loc;

    public Test() {
        super("testcmd", "testcmd");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

        server.getPlayerList().sendChatMsg(new TextComponentString("Hello World!"+loc));

    }
}