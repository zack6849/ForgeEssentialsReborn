package com.zack6849.forgeessentialseborn.commands.warping;

import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.Location;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.command.Command;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.Level;

/**
 * Created by Rory on 7/22/2016.
 */
public class Warp extends Command {

    public Location loc;

    public Warp() {
        super("warp", "warps to a set location");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        User user = new User(sender);
        Main.log(Level.INFO, args[0]);
        if(!args[0].isEmpty() && Teleports.isWarp(user.getName(), args[0])){
                Location location = Teleports.getWarpLocation(user.getName(), args[0]);
                user.teleport(location);

        } else {
            user.sendMessage("warp name required");
        }

    }
}