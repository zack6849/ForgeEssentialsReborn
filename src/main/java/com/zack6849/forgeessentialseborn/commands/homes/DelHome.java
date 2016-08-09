package com.zack6849.forgeessentialseborn.commands.homes;

import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;

/**
 * Created by Rory on 8/4/2016.
 */
public class DelHome extends Command {

    public DelHome() {
        super("delhome", "Deletes a home by name");
    }

    @Override
    public CommandResult execute(User user, String[] args) {
        if (!user.hasPermission(new Permission("forgeessentials.home.delete", false))) {
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

        Teleports.deletePlayerWarp(user.getName(), args[0]);
        user.sendMessage("warp " + args[0] + " set successfully");
        return CommandResult.SUCCESS;
    }
}