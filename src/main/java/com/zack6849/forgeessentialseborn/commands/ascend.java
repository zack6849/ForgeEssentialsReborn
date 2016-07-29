package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.*;
import com.zack6849.forgeessentialseborn.api.command.Command;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class ascend extends Command {

    public ascend() {
        super("ascend", "Shows version information");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

        User user = new User(sender);
        Location location = user.getLocation();
        World world = sender.getEntityWorld();
        user.sendMessage(location.getX()+" "+location.getY()+" "+location.getZ());

        BlockPos bs = new BlockPos(location.getX(),location.getY(),location.getZ());

        IBlockState block = world.getBlockState(bs);
        int x = 0;
        int y = (int)location.getY();
        while(x < 20){
            if(block.getMaterial() == Material.AIR){
                bs = new BlockPos(bs.getX(),bs.getY()+1,bs.getZ());
                block = world.getBlockState(bs);
            } else{
                break;
            }
            x++;
        }
        x = 0;
        while(x < 10){
            if(block.getMaterial() != Material.AIR){
                bs = new BlockPos(bs.getX(),bs.getY()+1,bs.getZ());
                block = world.getBlockState(bs);

            } else{
                block = world.getBlockState(bs);
                bs = new BlockPos(bs.getX(),bs.getY()+1,bs.getZ());

                if(block.getMaterial() == Material.AIR){

                    //moveEntity does not teleport!

                    user.getSender().getCommandSenderEntity().setPositionAndUpdate(bs.getX(),bs.getY(),bs.getZ());
                    user.sendMessage("Ascending to "+bs.getX()+" "+bs.getY()+" "+bs.getZ());
                    break;
                }
            }
            x++;
        }
        user.sendMessage("Ascension Complete");

    }

}