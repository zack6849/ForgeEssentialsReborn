package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.Location;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
/**
 * Created by Rory on 7/27/2016.
 */
public class GetPosition extends Command {

    public GetPosition() {
        super("getpos", "get current position");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        User user = new User(sender);
        Location location = user.getLocation();
        user.sendMessage(user.getLocation().toString());

    }
}