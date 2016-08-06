package com.zack6849.forgeessentialseborn.commands.warping;

import com.zack6849.forgeessentialseborn.api.Location;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.command.Command;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * Created by Rory on 8/4/2016.
 */
public class DelWarp extends Command {

    public Location loc;

    public DelWarp() {
        super("setwarp", "sets a warp at the current location");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        User user = new User(sender);
        if(Teleports.isWarp(user.getName(),args[0]))
        if(!args[0].isEmpty()){
            Teleports.delGlobalWarp(args[0]);
            user.sendMessage("warp "+args[0]+" set successfully");
        } else{
            user.sendMessage("A warp name is required in arg[0]");
        }
    }
}