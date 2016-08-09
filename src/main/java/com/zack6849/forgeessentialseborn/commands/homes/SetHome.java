package com.zack6849.forgeessentialseborn.commands.homes;

import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;

/**
 * Created by Rory on 7/22/2016.
 */
public class SetHome extends Command {

    public SetHome() {
        super("sethome", "sets a home at the current location");
    }

    @Override
    public CommandResult execute(User user, String[] args) {
        if (!user.hasPermission(new Permission("forgeessentials.home.set", false))) {
            return CommandResult.NO_PERMISSION;
        }
        if (!user.isPlayer()) {
            return CommandResult.PLAYER_ONLY;
        }
        if (args[0] == null || args[0].isEmpty()) {
            user.sendMessage("You need to specify a home name!");
            return CommandResult.HANDLED_ERROR;
        }
        if (Teleports.isPlayerWarp(user.getUniqueId(), args[0])) {
            user.sendMessage("Sorry, a home by this name already exists!");
            return CommandResult.HANDLED_ERROR;
        }

        Teleports.createPlayerWarp(user.getName(), args[0], user.getLocation());
        user.sendMessage("warp " + args[0] + " set successfully");
        return CommandResult.SUCCESS;
    }
}