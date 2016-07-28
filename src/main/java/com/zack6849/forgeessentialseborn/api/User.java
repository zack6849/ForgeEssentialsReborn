package com.zack6849.forgeessentialseborn.api;

import com.zack6849.forgeessentialseborn.api.permissions.Group;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;


public class User {
    private ICommandSender sender;
    private String name;
    private String uniqueId;
    private Group group;
    private Location location;
    private boolean player = false;

    public User(ICommandSender sender) {
        this.setSender(sender);
        if (sender instanceof EntityPlayer) {

            int x = sender.getPosition().getX();
            int y = sender.getPosition().getY();
            int z = sender.getPosition().getZ();
            int pitch;
            int yaw;
            pitch = 0;
            yaw = 0;

            setPlayer(true);
            setLocation(z,y,z,pitch,yaw);
        }
        if (isPlayer()) {
            EntityPlayer p = (EntityPlayer) sender.getCommandSenderEntity();
            if (p != null) {
                setUniqueId(p.getCachedUniqueIdString());
            }
        }
    }

    public User(String name, String id) {
        setPlayer(true);
        setName(name);
        setUniqueId(id);
        int x = sender.getPosition().getX();
        int y = sender.getPosition().getY();
        int z = sender.getPosition().getZ();
        int pitch;
        int yaw;
        pitch = 0;
        yaw = 0;
        setLocation(x, y, z, pitch, yaw);
    }

    private void setLocation(int z, int y, int z1, int pitch, int yaw) {
        this.location = new Location(z,y,z,pitch,yaw);
    }

    public void sendMessage(String message) {
        if (sender != null) {
            sender.addChatMessage(new TextComponentString(message));
        }
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean hasPermission(Permission permission) {
        return getGroup().hasPermission(permission);
    }

    public Location getLocation() {

        return this.location;
    }
}

