package com.zack6849.forgeessentialseborn.utils;

        import com.google.common.io.Files;
        import com.google.gson.*;
        import com.zack6849.forgeessentialseborn.Main;

        import java.io.File;
        import java.io.IOException;
        import java.nio.charset.Charset;
        import org.apache.logging.log4j.Level;

        public class StorageHandler {

    StorageHandler() {

    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void createConfig(String name) {
        try {
            File file = new File(Main.getInstance().getServer().getDataDirectory().getAbsolutePath().substring(0, Main.getInstance().getServer().getDataDirectory().getAbsolutePath().length()-1) + "config"+File.separator+ "ForgeEssentialsReborn"+File.separator+name);
            if (!file.exists()) {
                Files.write("{}", file, Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset());
            } else {
                Main.log(Level.INFO, name + " already exists, continuing...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFilledConfig(JsonObject name) {

    }


    public static JsonObject loadJson(File file) {
        String json;
        try {
            json = Files.toString(file, Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset());
            JsonElement jelement = new JsonParser().parse(json);
            return jelement.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static File getConfigLocation(String s) {
        return new File(Main.getInstance().getServer().getDataDirectory().getAbsolutePath().substring(0, Main.getInstance().getServer().getDataDirectory().getAbsolutePath().length()-1) + "config"+File.separator+ "ForgeEssentialsReborn"+File.separator+s);
    }
}
