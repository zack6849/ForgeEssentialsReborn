package com.zack6849.forgeessentialseborn.api;

import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.permissions.Group;
import com.zack6849.forgeessentialseborn.api.permissions.User;
import net.minecraft.command.ICommandSender;

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