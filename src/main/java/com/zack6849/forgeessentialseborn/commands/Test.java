package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.Location;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
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
    public CommandResult execute(User user, String[] args) {
        if (user.getSender() != null && user.getSender().getServer() != null) {
            user.getSender().getServer().getPlayerList().sendChatMsg(new TextComponentString("Hello World!" + loc));
            return CommandResult.SUCCESS;
        }
        return CommandResult.FAILURE;
    }
}