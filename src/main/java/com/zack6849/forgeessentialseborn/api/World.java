package com.zack6849.forgeessentialseborn.api;

/**
 * Created by zack6849 on 7/28/2016.
 */
public class World {
    private Location spawn;
    private int id;
    private String name;
    private boolean raining;
    private boolean thunder;
    private net.minecraft.world.World nmsWorld;

    public World(net.minecraft.world.World world) {
        this.setNmsWorld(world);
        this.setSpawn(new Location(world.getSpawnPoint()));
        this.id = world.provider.getDimension();
        this.name = world.getWorldInfo().getWorldName();
        this.setRaining(world.isRaining());
        this.setThunder(world.isThundering());
    }

    public Location getSpawn() {
        return spawn;
    }

    public void setSpawn(Location spawn) {
        this.spawn = spawn;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
        this.nmsWorld.setRainStrength(1.0F);
    }

    public boolean isThunder() {
        return thunder;
    }

    public void setThunder(boolean thunder) {
        this.thunder = thunder;
        this.nmsWorld.setThunderStrength(1.0F);
    }

    public net.minecraft.world.World getNmsWorld() {
        return nmsWorld;
    }

    public void setNmsWorld(net.minecraft.world.World nmsWorld) {
        this.nmsWorld = nmsWorld;
    }
}
