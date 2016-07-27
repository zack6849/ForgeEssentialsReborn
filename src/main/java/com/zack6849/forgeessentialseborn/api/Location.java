package com.zack6849.forgeessentialseborn.api;

/**
 * Created by Rory on 7/26/2016.
 */
public abstract class Location {

    private int x;
    private int y;
    private int z;
    private int pitch;
    private int yaw;
    public Location(int x, int y, int z, int pitch, int yaw)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }
}