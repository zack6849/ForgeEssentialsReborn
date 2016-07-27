package com.zack6849.forgeessentialseborn.api;


import net.minecraft.command.ICommandSender;

public abstract class ILocation {

    private int x;
    private int y;
    private int z;
    private int pitch;
    private int yaw;

    public ILocation(int x, int y, int z, int pitch, int yaw)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }
}