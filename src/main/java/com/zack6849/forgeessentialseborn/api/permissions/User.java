package com.zack6849.forgeessentialseborn.api.permissions;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by zack6849 on 7/23/2016.
 */
public class User {
    private ICommandSender sender;
    private String name;
    private String uniqueId;
    private boolean player = false;

    public User(ICommandSender sender) {
        this.setSender(sender);
        if (sender instanceof EntityPlayer) {
            setPlayer(true);
        }
        if (isPlayer()) {
            EntityPlayer p = (EntityPlayer) sender.getCommandSenderEntity();
            setUniqueId(p.getCachedUniqueIdString());
        }
    }

    public User(String name, String id) {
        setPlayer(true);
        setName(name);
        setUniqueId(id);
    }

    public ICommandSender getSender() {
        return this.sender;
    }

    public void setSender(ICommandSender sender) {
        this.sender = sender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public boolean isConsole() {
        return !player;
    }


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return String.format(name + " (" + uniqueId + ")");
    }
}
