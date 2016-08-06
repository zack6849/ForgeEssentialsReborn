package com.zack6849.forgeessentialseborn.api.permissions;

import com.google.common.io.Files;
import com.google.gson.*;
import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.utils.StorageHandler;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by zack6849 on 7/23/2016.
 */
public class PermissionManager {
    private static final List<Group> groups = new LinkedList<>();
    private static final List<User> usercache = new ArrayList<>();
    private static File file = new File(Main.getInstance().getServer().getDataDirectory().getAbsoluteFile() + File.separator + "config" + File.separator + "ForgeEssentialsReborn", "permissions.json");
    private static JsonObject data;

    public static Group getDefaultGroup() {
        for (Group g : groups) {
            if (g.isDefaultGroup()) {
                return g;
            }
        }
        return null;
    }

    public static List<User> getUsercache() {
        return usercache;
    }


    public void load() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            if(!file.exists()){
                StorageHandler.moveConfig("permissions.json");
            }

            data = StorageHandler.loadJson(file);
            groups.clear();
            String json = Files.toString(file, Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset());
            JsonObject group = data.get("groups").getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> set = group.entrySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                Map.Entry en = (Map.Entry) it.next();
                JsonObject obj = new JsonParser().parse(en.getValue().toString()).getAsJsonObject();
                String name = obj.get("name").toString().replaceAll("\"", "");
                List<Permission> permissions = new ArrayList<Permission>();
                boolean is_default = obj.get("default").getAsBoolean();
                for (JsonElement perm : obj.getAsJsonArray("permissions")) {
                    permissions.add(new Permission(perm.getAsString(), false));
                }
                groups.add(new Group(name, permissions, is_default));
                for (Group g : groups) {
                    JsonObject gr = data.get("groups").getAsJsonObject().get(g.getName()).getAsJsonObject();
                    if (gr.has("inheritance")) {
                        JsonArray inherit = gr.get("inheritance").getAsJsonArray();
                        List<String> inheritance = new ArrayList<String>();
                        for (int i = 0; i < inherit.size(); i++) {
                            inheritance.add(inherit.get(i).getAsString());
                        }
                        for (String in : inheritance) {
                            Group ing = getGroupByName(in);
                            g.addInherit(ing);
                        }
                    }
                }
            }
            Set<Map.Entry<String, JsonElement>> users = data.get("users").getAsJsonObject().entrySet();
            for (Map.Entry<String, JsonElement> user : users) {
                String[] mask = user.getKey().split(":");
                String name = mask[0];
                String id = mask[1];
                Group g = getGroupByName(user.getValue().getAsString());
                if (g != null) {
                    User u = new User(name, id);
                    u.setGroup(g);
                    g.addUser(u);
                    getUsercache().add(u);
                }
            }
        } catch (Exception ex) {
            Main.log(Level.ERROR, "Failed to load PermissionManager! ");
            ex.printStackTrace();
        }
    }

    /**
     * Gets a group by name, not case sensitive.
     *
     * @param id the name of the group to get, ie. "admin"
     * @return the group object if we find it, otherwise null.
     */
    public Group getGroupByName(String id) {
        for (Group group : groups) {
            if (group.getName().equalsIgnoreCase(id)) {
                return group;
            }
        }
        Main.log(Level.WARN, "Request for invalid group '" + id + "'");
        return null;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public PermissionManager getPermissionsManager() {
        return this;
    }

    /**
     * Returns the group a user belongs to, this ignores default
     * If a user belongs to two groups, then it will return the first it finds.
     *
     * @param user the User object to get the group of
     * @return the Group the user belongs to
     */
    public Group getUserGroup(User user) {
        //first check if any of the groups contain the user
        for (Group g : getGroups()) {
            for (User u : g.getUsers()) {
                if (u.getUniqueId().equalsIgnoreCase(user.getUniqueId())) {
                    Main.log(Level.DEBUG, "Found pre-existing group for user.");
                    return g;
                }
            }
        }
        //if they haven't, parse all the json
        Main.log(Level.DEBUG, "Couldn't find pre-existing group for user, searching.");
        String json = null;
        Set<Map.Entry<String, JsonElement>> users = data.get("users").getAsJsonObject().entrySet();
        Iterator it = users.iterator();
        int count = 0;
        while (it.hasNext()) {
            count++;
            Object next = it.next();
            Map.Entry en = (Map.Entry) next;
            String[] mask = en.getKey().toString().split(":");
            String id = mask[1];
            if (id.equalsIgnoreCase(user.getUniqueId())) {
                String rank = en.getValue().toString().replaceAll("\"", "");
                Group target = getGroupByName(rank);
                target.addUser(user);
                return target;
            }
        }
        //no group found, give them the default group.
        Group target = getDefaultGroup();
        if (target == null) {
            Main.log(Level.ERROR, "No default group defined, your configuration file is broken!!");
            return null;
        } else {
            target.addUser(user);
            return target;
        }
    }

    public void save() {
        Main.getLogger().log(Level.INFO, "Saving permissions data.");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();
        HashMap<String, String> names = new HashMap<>();

        try {
            //Loop through all users and add their masks & rank to the users map.
            HashMap<String, String> users = new HashMap<>();
            JsonObject gr = data.get("groups").getAsJsonObject();
            for (Group g : groups) {
                gr.remove(g.getName());
                for (User u : g.getUsers()) {
                    users.put(u.getName() + ":" + u.getUniqueId(), g.getName());
                }
                List<String> permissions = new ArrayList<String>();
                for (Permission perm : g.getPermissions()) {
                    if (!perm.isInheirited()) {
                        //no dupes, please.
                        if (!permissions.contains(perm.getPermission())) {
                            permissions.add(perm.getPermission());
                        }
                    }
                }

                List<String> inheiritance = new ArrayList<String>();
                //add all inheirited groups to a list
                for (String inheirited : g.getInheritance()) {
                    inheiritance.add(inheirited);
                }
                JsonObject group = parser.parse(gson.toJson(g)).getAsJsonObject();
                group.remove("permissions");
                group.add("permissions", parser.parse(gson.toJson(permissions)));
                group.remove("inheritance");
                group.add("inheritance", parser.parse(gson.toJson(inheiritance)));
                gr.add(g.getName(), group);
            }
            data.remove("groups");
            data.add("groups", gr);
            data.remove("users");
            data.add("users", parser.parse(gson.toJson(users)));
            Files.write(gson.toJson(data), file, Charset.isSupported("UTF-8") ? Charset.forName("UTF-8") : Charset.defaultCharset());
            Main.getLogger().log(Level.INFO, "Saving complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Just a convenience method, the exact same as calling save() and load()
     */
    public void reload() {
        save();
        load();
        System.out.println("[PermissionsManager] Reload Complete!");
    }
}
