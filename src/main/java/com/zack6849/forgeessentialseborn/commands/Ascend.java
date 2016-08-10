package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.Location;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class Ascend extends Command {

    public Ascend() {
        super("Ascend", "Shows version information");
    }

    @Override
    public CommandResult execute(MinecraftServer server, ICommandSender sender, String[] args) {

        User user = new User(sender);

        user.sendMessage("CommandAscend");

    }

    @Override
    public CommandResult execute(User user, String[] args) {
        if (!user.hasPermission(new Permission("forgeessentials.ascend", false))) {
            return CommandResult.NO_PERMISSION;
        }
        if (!user.isPlayer()) {
            return CommandResult.PLAYER_ONLY;
        }
        Location location = user.getLocation();
        World world = user.getSender().getEntityWorld();
        user.sendMessage(location.getX() + " " + location.getY() + " " + location.getZ());
        BlockPos bs = new BlockPos(location.getX(), location.getY(), location.getZ());
        IBlockState block = world.getBlockState(bs);
        int x = 0;
        int playerx = user.getSender().getPosition().getX();
        int playery = user.getSender().getPosition().getY();
        int playerz = user.getSender().getPosition().getZ();
        while (x < 30) {
            if (block.getMaterial() == Material.AIR) {
                bs = new BlockPos(playerx, playery, playerz);
                block = world.getBlockState(bs);
            } else {
                break;
            }
            x++;
        }
        x = 0;
        while (x < 30) {
            if (block.getMaterial() != Material.AIR) {
                bs = new BlockPos(bs.getX(), bs.getY() + 1, bs.getZ());
                block = world.getBlockState(bs);
            } else {
                block = world.getBlockState(bs);
                bs = new BlockPos(bs.getX(), bs.getY() + 1, bs.getZ());
                if (block.getMaterial() == Material.AIR) {
                    if (user.getSender().getCommandSenderEntity() != null) {
                        user.getSender().getCommandSenderEntity().setPositionAndUpdate(bs.getX(), bs.getY(), bs.getZ());
                        return CommandResult.SUCCESS;
                    }
                }
            }
            x++;
        }
        return CommandResult.FAILURE;
    }

}