package com.zack6849.forgeessentialseborn.utils;

import com.mojang.authlib.GameProfile;
import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.permissions.Group;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Created by zack6849 on 7/25/2016.
 */
public class PlayerUtils {

    public static User fromUUID(String id) {
        for (User u : Main.getInstance().getPermissionManager().getUsercache()) {
            if (u.getUniqueId().equals(id)) {
                return u;
            }
        }
        for (GameProfile profile : Main.getInstance().getServer().getPlayerList().getAllProfiles()) {
            if (profile.getId().toString().equals(id)) {
                User u = new User(profile.getName(), profile.getId().toString());
                Group g = Main.getInstance().getPermissionManager().getUserGroup(u);
                u.setGroup(g);
                g.addUser(u);
                Main.getInstance().getPermissionManager().getUsercache().add(u);
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
