package com.zack6849.forgeessentialseborn.api;

import com.google.common.io.Files;
import com.google.gson.*;
import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.utils.StorageHandler;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class Teleports {

    private static File file = StorageHandler.moveConfig("warps.json");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static JsonObject data = StorageHandler.loadJson(file);


    public static ArrayList<String> getWarps(String uuid) {
        ArrayList<String> warpsbuilder = new ArrayList<String>();
        Set<Map.Entry<String, JsonElement>> entries = data.getAsJsonObject(uuid).entrySet();//will return members of the object
        for (Map.Entry<String, JsonElement> entry : entries) {
            Main.log(Level.INFO, entry.getKey());
            warpsbuilder.add(entry.getKey());
        }
        return warpsbuilder;
    }

    /**
     * Checks if a warp exists in our files for a player
     *
     * @param uuid the UUID of the player
     * @param name the name of the warp to check for
     * @return true if the warp exists
     */
    public static boolean isPlayerWarp(String uuid, String name) {
        return data.has(uuid) && data.get(uuid).getAsJsonObject().has(name);
    }

    /**
     * Checks if a global warp exists in our files
     *
     * @param name the name of the warp
     * @return true if the warp exists
     */
    public static boolean isGlobalWarp(String name) {
        return data.has("global") && data.get("global").getAsJsonObject().has(name);
    }

    /**
     * Converts a warp to a location
     *
     * @param uuid player uuid
     * @param name name of the warp to get
     * @return null if nothing found, otherwise the location
     */
    public static Location getPlayerWarpByName(String uuid, String name) {
        if (isPlayerWarp(uuid, name)) {
            JsonObject thiswarp = data.getAsJsonObject(uuid).getAsJsonObject(name);
            int warpx = thiswarp.get("x").getAsInt();
            int warpy = thiswarp.get("y").getAsInt();
            int warpz = thiswarp.get("z").getAsInt();
            int warppitch = thiswarp.get("pitch").getAsInt();
            int warpyaw = thiswarp.get("yaw").getAsInt();
            return new Location(warpx, warpy, warpz, warppitch, warpyaw);
        }
        return null;
    }

    /**
     * Deletes a global warp by name
     *
     * @param name the warp to remove
     */
    public static void deleteGlobalWarp(String name) {
        if (!data.has("global")) {
            data.add("global", new JsonObject());
        }
        if (data.get("global").getAsJsonObject().has(name)) {
            data.getAsJsonObject("global").remove(name);
        }
        saveData();
    }

    /**
     * Create a global warp
     *
     * @param name     the name for the warp
     * @param location the location
     */
    public static void createGlobalWarp(String name, Location location) {
        if (!data.has("global")) {
            data.add("global", new JsonObject());
        }
        data.getAsJsonObject("global").add(name, location.toJson());
        saveData();
    }

    /**
     * Creates a warp by name for a player
     *
     * @param uuid     player uuid
     * @param name     player name
     * @param location location to set the warp for
     */
    public static void createPlayerWarp(String uuid, String name, Location location) {
        if (!data.has("players")) {
            data.add("players", new JsonObject());
        }
        if (!data.get("players").getAsJsonObject().has(uuid)) {
            data.add(uuid, new JsonArray());
        }
        data.getAsJsonObject(uuid).add(name, location.toJson());
        saveData();
    }

    /**
     * Deletes a player's warp
     *
     * @param uuid player uuid
     * @param name warp name
     */
    public static void deletePlayerWarp(String uuid, String name) {
        if (!data.has("players")) {
            data.add("players", new JsonObject());
        }
        if (!data.get("players").getAsJsonObject().has(uuid)) {
            data.add(uuid, new JsonArray());
        }
        if (data.get("players").getAsJsonObject().get(uuid).getAsJsonObject().has(name)) {
            data.get("players").getAsJsonObject().get(uuid).getAsJsonObject().remove(name);
        }
        saveData();
    }

    /**
     * Write all changes to disk
     */
    public static boolean saveData() {
        boolean success = false;
        if (data != null) {
            try {
                Files.write(gson.toJson(data), file, Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset());
                success = true;
            } catch (IOException e) {
                Main.log(Level.WARN, "Failed to save warp data!");
                e.printStackTrace();
                success = false;
            }
        }
        return success;
    }
}
