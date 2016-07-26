package com.zack6849.forgeessentialseborn;

import com.zack6849.forgeessentialseborn.api.permissions.Group;
import com.zack6849.forgeessentialseborn.api.permissions.PermissionManager;
import com.zack6849.forgeessentialseborn.api.permissions.User;
import com.zack6849.forgeessentialseborn.commands.Test;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.Level;

@Mod(modid = Main.MODID, version = Main.VERSION, acceptableRemoteVersions = "*")
public class Main {
    public static final String ID = "Forge Essentials Reborn";
    public static final String MODID = "ForgeEssentialsReborn";
    public static final String VERSION = "1.0";

    @Mod.Instance(ID)
    private static Main instance;
    private static PermissionManager permissionManager;
    private static org.apache.logging.log4j.Logger logger;
    private MinecraftServer server;

    public static Main getInstance() {
        return instance;
    }

    public static org.apache.logging.log4j.Logger getLogger() {
        return logger;
    }

    public static void log(Level level, String message) {
        logger.log(level, String.format("[%s] %s", Main.MODID, message));
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        instance = this;
        logger = FMLLog.getLogger();
        server = event.getServer();
        Main.getInstance().getServer().getPlayerProfileCache().load();
        ServerCommandManager manager = (ServerCommandManager) event.getServer().getCommandManager();
        manager.registerCommand(new Test());
        permissionManager = new PermissionManager();
        setPermissionManager(permissionManager);
        permissionManager.load();
        MinecraftForge.EVENT_BUS.register(new Events());
    }

    @EventHandler
    public void serverClose(FMLServerStoppingEvent event) {
        log(Level.INFO, "Saving permission data...");
        for (Group g : getPermissionManager().getGroups()) {
            for (User u : g.getUsers()) {
                log(Level.INFO, u.getName() + " (" + u.getUniqueId() + ") : " + g.getName());
            }
        }
        permissionManager.save();
    }

    public MinecraftServer getServer() {
        return server;
    }

    public PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public void setPermissionManager(PermissionManager permissionManager) {
        Main.permissionManager = permissionManager;
    }
}
