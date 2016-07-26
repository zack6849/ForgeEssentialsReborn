package com.zack6849.forgeessentialseborn.api.permissions;

/**
 * Created by zack6849 on 7/23/2016.
 */
public class Permission {
    private String permission;
    private boolean inheirited;

    /**
     * @param permission  the permission node to store
     * @param isInherited if the permission node is inherited from another group, then this needs to be true.
     */
    public Permission(String permission, boolean isInherited) {
        this.setPermission(permission);
        this.setInheirited(isInherited);
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isInheirited() {
        return inheirited;
    }

    public void setInheirited(boolean inheirited) {
        this.inheirited = inheirited;
    }
}
