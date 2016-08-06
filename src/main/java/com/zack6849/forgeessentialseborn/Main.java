package com.zack6849.forgeessentialseborn;

import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.Teleports;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.api.permissions.Group;
import com.zack6849.forgeessentialseborn.api.permissions.PermissionManager;
import com.zack6849.forgeessentialseborn.utils.StorageHandler;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.Level;
import org.reflections.Reflections;

import java.io.File;
import java.util.Set;

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
        permissionManager = new PermissionManager();
        setPermissionManager(permissionManager);
        permissionManager.load();
        MinecraftForge.EVENT_BUS.register(new Events());
        Main.log(Level.INFO, "Searching for command classes.");
        ServerCommandManager manager = (ServerCommandManager) event.getServer().getCommandManager();
        Reflections reflections = new Reflections("com.zack6849.forgeessentialseborn.commands");
        Set<Class<? extends Command>> subtypes = reflections.getSubTypesOf(Command.class);

        //
        //Load blank config files if any are required
        //
        StorageHandler.moveConfig("warps.json");
        StorageHandler.moveConfig("strings.json");
        StorageHandler.moveConfig("messages.json");



        Main.log(Level.INFO, "Complete. found " + subtypes.size() + " commands. "+Main.getInstance().getServer().getDataDirectory().getAbsolutePath().substring(0, Main.getInstance().getServer().getDataDirectory().getAbsolutePath().length()-1) + "config"+File.separator+ "ForgeEssentialsReborn"+File.separator);
        for (Class c : subtypes) {
            try {
                Main.log(Level.INFO, "Registering command: " + c.getSimpleName());
                manager.registerCommand((Command) c.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
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
