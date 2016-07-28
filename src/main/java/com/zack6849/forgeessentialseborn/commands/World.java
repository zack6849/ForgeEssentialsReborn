package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import com.zack6849.forgeessentialseborn.Main;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;


/**
 * Created by Rory on 7/27/2016.
 */
public class World extends Command {

    public World() {
        super("world", "switches player World");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

        String world = sender.getEntityWorld().getWorldInfo().getWorldName();
        User user = new User(sender);

        int dimensionID = Integer.parseInt(args[0]);
        user.sendMessage(args[0]+"   "+Integer.parseInt(args[0]));
        ///try to change world by the first argument
        try{
            Integer[] dim = DimensionManager.getIDs();
            user.sendMessage(dim.toString());


            if(DimensionType.getById(dimensionID) != null){

                user.sendMessage("Current: "+world+"\n"+"Moving to world:"+args[0]+"\n"+DimensionType.getById(dimensionID).getName());
                sender.getCommandSenderEntity().changeDimension(dimensionID);
            } else {
                user.sendMessage("Error: dimension can not be found or is unavailable");
            }

        } catch(Exception exception){
            user.sendMessage("Error: dimension can not be found or is unavailable");
        }

    }

}
