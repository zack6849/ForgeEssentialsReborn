package com.zack6849.forgeessentialseborn.api;


import net.minecraft.command.ICommandSender;

public class Location extends ILocation {
    private int x;
    private int y;
    private int z;
    private int pitch;
    private int yaw;
    private Location location;

    public Location(int x, int y, int z, int pitch, int yaw){
        super(x,y,z,pitch,yaw);

    }

    public String toString(Location location){

        return this.location.x+" "+this.location.y+" "+this.location.z+" -- Pitch: "+this.location.pitch+"  yaw:"+this.location.yaw;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getPitch() {
        return pitch;
    }

    public int getYaw() {
        return yaw;
    }
}
