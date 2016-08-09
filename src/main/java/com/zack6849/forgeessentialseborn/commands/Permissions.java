package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.Location;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

/**
 * Created by zack6849 on 7/22/2016.
 */
public class Permissions extends Command {

    public Permissions() {
        super("p", "runs permission commands");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        switch (args[0].toLowerCase()) {
            case "setusergroup":
                groupAdd(args);
                break;
            case "addgrouppermission":
                addGroupPermission(args);
                break;
            case "deletegrouppermission":
                deleteGroupPermission(args);
                break;
        }
    }

    private void addGroupPermission() {
    }
    private void groupAdd() {
    }
    private void deleteGroupPermission() {
    }
}