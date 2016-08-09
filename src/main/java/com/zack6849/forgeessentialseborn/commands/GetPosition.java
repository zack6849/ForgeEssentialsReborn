package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.Location;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;

/**
 * Created by Rory on 7/27/2016.
 */
public class GetPosition extends Command {

    public GetPosition() {
        super("getpos", "get current position");
    }

    @Override
    public CommandResult execute(User user, String[] args) {
        if (!user.hasPermission(new Permission("forgeessentials.getpos", false))) {
            return CommandResult.NO_PERMISSION;
        }
        if (!user.isPlayer()) {
            return CommandResult.PLAYER_ONLY;
        }

        Location location = user.getLocation();
        user.sendMessage(user.getLocation().toString());
        return CommandResult.SUCCESS;
    }
}