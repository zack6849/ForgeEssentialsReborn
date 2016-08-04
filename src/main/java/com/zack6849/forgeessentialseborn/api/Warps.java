package com.zack6849.forgeessentialseborn.api;

import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.utils.StorageHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;



public class Warps {


    private static File file = StorageHandler.getConfigLocation("warps.json");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static JsonObject data = StorageHandler.loadJson(file);

    private static ArrayList<String> globalwarplist;




    public static ArrayList<String> getWarps(String playername) {
        ArrayList<String> warpsbuilder = new ArrayList<String>();
        Set<Map.Entry<String, JsonElement>> entries = data.getAsJsonObject(playername).entrySet();//will return members of the object
        for (Map.Entry<String, JsonElement> entry: entries) {
            Main.log(Level.INFO, entry.getKey());
            warpsbuilder.add(entry.getKey());
        }
        return warpsbuilder;

    }

    public static ArrayList<String> setGlobalWarpList() {

        ArrayList<String> globalwarpsbuilder = new ArrayList<String>();
        Set<Map.Entry<String, JsonElement>> entries = data.getAsJsonObject("~Global").entrySet();//will return members of the object
        for (Map.Entry<String, JsonElement> entry: entries) {
            Main.log(Level.INFO, entry.getKey());
            globalwarpsbuilder.add(entry.getKey());
        }
        return globalwarpsbuilder;
    }

    public static boolean isWarp(String playername, String warpname){
        try{
            Main.log(Level.INFO, playername);
        } catch(Exception e){
            e.printStackTrace();
        }
       if (!data.has(playername)){
           return false;
       } else if(!data.getAsJsonObject(playername).has(warpname)){
           return false;
       }
       return true;
    }

    public static Location getWarpLocation(String playername, String warpname){

        JsonObject thiswarp = data.getAsJsonObject(playername).getAsJsonObject(warpname);
        int warpx = thiswarp.get("x").getAsInt();
        int warpy = thiswarp.get("y").getAsInt();
        int warpz = thiswarp.get("z").getAsInt();
        int warppitch = thiswarp.get("pitch").getAsInt();
        int warpyaw = thiswarp.get("yaw").getAsInt();
        Location location = new Location(warpx,warpy,warpz,warppitch,warpyaw);
        return location;
    }

    public static void setWarp(String playername, String warpname, Location location){
        try{
            data.getAsJsonObject(playername).add(warpname, location.toJson());
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            Files.write(gson.toJson(data), file, Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
