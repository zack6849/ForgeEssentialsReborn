package com.zack6849.forgeessentialseborn.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.zack6849.forgeessentialseborn.api.User;
import net.minecraft.command.ICommandSender;

import java.io.File;

/**
 * Created by Rory on 8/6/2016.
 */


public class StringHandler {

    private static File file = StorageHandler.moveConfig("messages.json");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static JsonObject data = StorageHandler.loadJson(file);


    private static String getString(String string){
        try {
            return data.get(string).toString();
        } catch(Exception e){
            return string;
        }
    }

    public static void sendMessage(ICommandSender sender, String message) {
        User user = new User(sender);
        message = parseString(user, message);
        user.sendMessage(message);

    }

    public static String parseString(User user, String message) {
        getString(message);
        String worldname = user.getSender().getEntityWorld().getWorldInfo().getWorldName();
        String time = ""+user.getSender().getEntityWorld().getWorldInfo().getWorldTime();
        String username = user.getName();
        String location = user.getLocation().toString();
        String group = user.getGroup().getName();
        String UUID = user.getUniqueId();

        message = message.replace("{WORLDTIME}",worldname);
        message = message.replace("{WORLDNAME}",worldname);
        message = message.replace("{USERNAME}",username);
        message = message.replace("{USERLOCATION}",location);
        message = message.replace("{USERGROUP}",group);
        message = message.replace("{UUID}",UUID);
        return message;
    }
}
