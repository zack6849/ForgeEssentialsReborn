package com.zack6849.forgeessentialseborn.api.permissions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zack6849.forgeessentialseborn.Main;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zack6849 on 7/23/2016.
 */
public class Group {
    private String name;
    @Expose(serialize = false)
    private List<Permission> permissions;
    @SerializedName("default")
    private boolean defaultGroup;
    //apparently transient works better, and it doesn't need to be static anyways.
    private List<String> inheritance;
    @Expose(serialize = false)
    private transient ArrayList<User> users;
    @Expose(serialize = false)
    private transient PermissionManager manager;

    public Group(String name, List<Permission> permissions, boolean isDefault) {
        manager = new PermissionManager();
        this.setName(name);
        this.setPermissions(permissions);
        setDefaultGroup(isDefault);
        this.setInheritance(new ArrayList<String>());
        this.users = new ArrayList<User>();
    }

    public Group(String name, List<Permission> permissions, boolean isDefault, List<String> inheritance) {
        manager = new PermissionManager();
        this.setName(name);
        this.setPermissions(permissions);
        this.setDefaultGroup(isDefault);
        this.setInheritance(inheritance);
        this.users = new ArrayList<User>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<String> getInheritance() {
        return inheritance;
    }

    public void setInheritance(List<String> inheritance) {
        this.inheritance = inheritance;
    }

    public boolean hasPermission(Permission permission) {
        for (Permission perm : permissions) {
            if (perm.getPermission().equalsIgnoreCase(permission.getPermission())) {
                return true;
            }
            if (perm.getPermission().equalsIgnoreCase("commands.*")) {
                return true;
            }
        }
        return false;
    }

    public void addPermission(Permission permission) {
        boolean found = false;
        for (Permission perm : permissions) {
            if (perm.getPermission().equals(permission.getPermission())) {
                found = true;
            }
        }
        if (!found) {
            permissions.add(permission);
        }
    }

    public void removePermission(String permission) {
        for (Permission perm : permissions) {
            if (perm.getPermission().equalsIgnoreCase(permission)) {
                this.permissions.remove(perm);
                return;
            }
        }
    }

    public void addInherit(Group toinherit) {
        for (Permission permission : toinherit.getPermissions()) {
            this.addPermission(new Permission(permission.getPermission(), true));
        }
        if (!this.inheritance.contains(toinherit.getName())) {
            this.inheritance.add(toinherit.getName());
        }
    }

    public void removeInherit(Group inherit) {
        for (Permission permission : inherit.getPermissions()) {
            this.removePermission(permission.getPermission());
        }
        if (this.inheritance.contains(inherit.getName())) {
            this.inheritance.remove(inherit.getName());
        }
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
        Main.log(Level.INFO, "Userlist for " + getName() + ":");
        for (User u : users) {
            Main.log(Level.INFO, " - " + u.toString());
        }
    }

    public void removeUser(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
        }
    }

    public boolean isDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }
}
