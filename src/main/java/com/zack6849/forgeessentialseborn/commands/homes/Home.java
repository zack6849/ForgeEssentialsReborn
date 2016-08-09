package com.zack6849.forgeessentialseborn.commands.homes;

import com.zack6849.forgeessentialseborn.api.Location;
import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;

/**
 * Created by Rory on 7/22/2016.
 */
public class Home extends Command {

    public Home() {
        super("home", "Teleports you to the specified home");
    }

    @Override
    public CommandResult execute(User user, String[] args) {
        if (!user.hasPermission(new Permission("forgeessentials.home.use", false))) {
            return CommandResult.NO_PERMISSION;
        }
        if (!user.isPlayer()) {
            return CommandResult.PLAYER_ONLY;
        }
        if (args[0] == null || args[0].isEmpty()) {
            user.sendMessage("You need to specify a home name!");
            return CommandResult.HANDLED_ERROR;
        }
        if (!Teleports.isPlayerWarp(user.getUniqueId(), args[0])) {
            user.sendMessage("That home doesn't exist!");
            return CommandResult.HANDLED_ERROR;
        }

        Location location = Teleports.getPlayerWarpByName(user.getName(), args[0]);
        user.teleport(location);
        return CommandResult.SUCCESS;
    }
}