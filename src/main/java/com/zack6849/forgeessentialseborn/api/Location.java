package com.zack6849.forgeessentialseborn.api;

import net.minecraft.util.math.BlockPos;

public class Location {

    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;
    private World world;

    public Location(int x, int y, int z, int pitch, int yaw){
        this.setX(x);
        this.setY(y);
        this.setZ(z);
        this.setPitch(pitch);
        this.setYaw(yaw);
    }

    public Location(BlockPos pos) {
        this.setX(pos.getX());
        this.setY(pos.getY());
        this.setZ(pos.getZ());
        this.setPitch(0.0F);
        this.setYaw(0.0F);
    }

    public String toString(Location location){
        return this.getX() + " " + this.getY() + " " + this.getZ() + " -- Pitch: " + this.getPitch() + "  yaw:" + this.getYaw();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
