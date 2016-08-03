package com.zack6849.forgeessentialseborn.api;

import com.google.gson.JsonElement;
import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.utils.StorageHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class Warp {


    private static File file = StorageHandler.getConfigLocation("warps.json");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static JsonObject data = StorageHandler.loadJson(file);

    private static ArrayList<String> globalwarplist;

    private static List[] getList(){
        String[] warplist;
        
        return null;
    }


    public static void getWarps(String name) {
        //TODO: IMPLEMENT THIS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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

       if (!data.has(playername)){
           return false;
       } else if(!data.getAsJsonObject(playername).has(warpname)){
           return false;
       }
       return true;
    }


}
