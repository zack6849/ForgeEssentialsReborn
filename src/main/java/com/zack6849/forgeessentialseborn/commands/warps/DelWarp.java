package com.zack6849.forgeessentialseborn.commands.warps;

import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;

/**
 * Created by Rory on 8/4/2016.
 */
public class DelWarp extends Command {

    public DelWarp() {
        super("delwarp", "Deletes a warp by name");
    }

    @Override
    public CommandResult execute(User user, String[] args) {
        if (!user.hasPermission(new Permission("forgeessentials.warps.delete", false))) {
            return CommandResult.NO_PERMISSION;
        }
        if (!user.isPlayer()) {
            return CommandResult.PLAYER_ONLY;
        }
        if (args[0] == null || args[0].isEmpty()) {
            user.sendMessage("Sorry, you need to specify a warp name");
            return CommandResult.HANDLED_ERROR;
        }
        if (!Teleports.isGlobalWarp(args[0])) {
            user.sendMessage("Sorry, that wap doesn't exist!");
            return CommandResult.HANDLED_ERROR;
        }

        Teleports.deleteGlobalWarp(args[0]);
        user.sendMessage("warp " + args[0] + " set successfully");
        return CommandResult.SUCCESS;
    }
}