package com.zack6849.forgeessentialseborn.api;

import com.google.gson.JsonObject;
import net.minecraft.util.math.BlockPos;

public class Location {

    private int x;
    private int y;
    private int z;
    private float pitch;
    private float yaw;
    private World world;

    public Location(int x, int y, int z, int pitch, int yaw) {
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

    public String toString(Location location) {
        return this.getX() + " " + this.getY() + " " + this.getZ() + " -- Pitch: " + this.getPitch() + "  yaw:" + this.getYaw();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
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

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", this.x);
        jsonObject.addProperty("y", this.y);
        jsonObject.addProperty("z", this.z);
        jsonObject.addProperty("pitch", this.pitch);
        jsonObject.addProperty("yaw", this.yaw);

        return jsonObject;
    }
}
