package com.zack6849.forgeessentialseborn.commands.warping;

import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.Location;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * Created by Rory on 7/22/2016.
 */
public class SetHome extends Command {

    public Location loc;

    public SetHome() {
        super("setwarp", "sets a warp at the current location");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        User user = new User(sender);
        if(!args[0].isEmpty()){
            Teleports.setWarp(user.getName(),args[0],user.getLocation());
            user.sendMessage("warp "+args[0]+" set successfully");
        } else{
            user.sendMessage("A warp name is required in arg[0]");
        }

    }
}