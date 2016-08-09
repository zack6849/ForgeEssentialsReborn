package com.zack6849.forgeessentialseborn.commands.warps;

import com.zack6849.forgeessentialseborn.api.Location;
import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;

/**
 * Created by Rory on 7/22/2016.
 */
public class SetWarp extends Command {

    public Location loc;

    public SetWarp() {
        super("setwarp", "sets a warp at the current location");
    }

    @Override
    public CommandResult execute(User user, String[] args) {
        if (!user.hasPermission(new Permission("forgeessentials.warps.set", false))) {
            return CommandResult.NO_PERMISSION;
        }
        if (!user.isPlayer()) {
            return CommandResult.PLAYER_ONLY;
        }
        if (args[0] == null || args[0].isEmpty()) {
            user.sendMessage("You need to specify a warp name!");
            return CommandResult.HANDLED_ERROR;
        }
        if (!Teleports.isGlobalWarp(args[0])) {
            user.sendMessage("A warp by that name already exists!");
            return CommandResult.HANDLED_ERROR;
        }

        Teleports.createGlobalWarp(args[0], user.getLocation());
        user.sendMessage("warp " + args[0] + " set successfully");
        return CommandResult.SUCCESS;
    }
}