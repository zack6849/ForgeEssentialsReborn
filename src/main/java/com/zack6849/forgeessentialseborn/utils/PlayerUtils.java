package com.zack6849.forgeessentialseborn.utils;

import com.mojang.authlib.GameProfile;
import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.permissions.User;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by zack6849 on 7/25/2016.
 */
public class PlayerUtils {

    public static User fromUUID(String id) {
        for (GameProfile profile : Main.getInstance().getServer().getPlayerList().getAllProfiles()) {
            if (profile.getId().toString().equals(id)) {
                User u = new User(profile.getName(), profile.getId().toString());
                u.setGroup(Main.getInstance().getPermissionManager().getUserGroup(u));
                return u;
            }
        }
        return null;
    }

    public String getPlayerIp(String UUID) {
        return getPlayerByUUID(UUID).getPlayerIP();
    }

    public EntityPlayerMP getPlayerByUUID(String UUID) {
        for (EntityPlayerMP player : Main.getInstance().getServer().getPlayerList().getPlayerList()) {
            if (player.getUniqueID().toString().equals(UUID)) {
                return player;
            }
        }
        return null;
    }
}
