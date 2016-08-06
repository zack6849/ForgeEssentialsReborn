package com.zack6849.forgeessentialseborn.utils;

        import com.google.common.io.Files;
        import com.google.gson.*;
        import com.zack6849.forgeessentialseborn.Main;
        import java.io.*;
        import java.net.URL;
        import java.nio.charset.Charset;
        import org.apache.commons.io.FileUtils;
        import org.apache.logging.log4j.Level;

        public class StorageHandler {

    public static File moveConfig(String name){
        try {
            URL inputUrl = Main.getInstance().getClass().getResource("/" + name);
            File dest = new File(Main.getInstance().getServer().getDataDirectory().getAbsoluteFile() + File.separator + "config" + File.separator + "ForgeEssentialsReborn", name);
            FileUtils.copyURLToFile(inputUrl, dest);
            return dest;
        } catch(IOException e){
            e.printStackTrace();
            Main.log(Level.INFO, "jar File missing json config");
        }
        return null;
    }

    public static JsonObject loadJson(File file) {
        String json;
        try {
            json = Files.toString(file, Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset());
            JsonElement jelement = new JsonParser().parse(json);
            return jelement.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
