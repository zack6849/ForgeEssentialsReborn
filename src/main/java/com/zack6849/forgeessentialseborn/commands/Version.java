package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by Rory on 7/26/2016.
 */
public class Version extends Command {

    public Version() {
        super("version", "Shows version information");
    }

    @Override
    public CommandResult execute(User user, String[] args) {
        user.getSender().addChatMessage(new TextComponentString(Main.VERSION));
        return CommandResult.SUCCESS;
    }

}