package com.zack6849.forgeessentialseborn;

import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.apache.logging.log4j.Level;

/**
 * Created by zack6849 on 7/25/2016.
 */
public class Events {
    @SubscribeEvent()
    public void join(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;
        Main.log(Level.INFO, event.player.getUniqueID().toString());
        User u = PlayerUtils.fromUUID(player.getUniqueID().toString());
        Main.log(Level.INFO, "User " + player.getName() + " is of the rank " + u.getGroup().getName());
        event.player.getServer().getPlayerList().sendChatMsg(new TextComponentString("User " + player.getName() + " is of the rank " + u.getGroup().getName()));
    }
}
