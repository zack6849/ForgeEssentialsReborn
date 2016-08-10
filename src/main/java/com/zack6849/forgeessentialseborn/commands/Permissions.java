package com.zack6849.forgeessentialseborn.commands;

import com.zack6849.forgeessentialseborn.api.User;
import com.zack6849.forgeessentialseborn.api.command.Command;
import com.zack6849.forgeessentialseborn.Main;
import com.zack6849.forgeessentialseborn.api.command.CommandResult;
import com.zack6849.forgeessentialseborn.api.permissions.Group;
import com.zack6849.forgeessentialseborn.api.permissions.Permission;

/**
 * Created by zack6849 on 7/22/2016.
 */
public class Permissions extends Command {

    public Permissions() {
        super("p", "runs permission commands. usage: '/p setusergroup {group} {user}'");
    }

    @Override
    public CommandResult execute(User user, String[] args) {
        switch (args[0].toLowerCase()) {
            case "setusergroup":
                groupAdd(args, user);
                break;
            case "addgrouppermission":
                addGroupPermission(args);
                break;
            case "deletegrouppermission":
                deleteGroupPermission(args);
                break;
            case "reload":
                reloadPermissions();
                break;
            default:
                return CommandResult.INVALID_SYNTAX;
        }
        Main.getInstance().getPermissionManager().save();
        return CommandResult.SUCCESS;
    }

    private void reloadPermissions() {
        Main.getInstance().getPermissionManager().reload();
    }

    private void addGroupPermission(String[] args) {
        Group group = Main.getInstance().getPermissionManager().getGroupByName(args[1]);
        Permission perm = new Permission(args[2], false);
        group.addPermission(perm);
    }

    private void groupAdd(String[] args, User user) {
        Group group = Main.getInstance().getPermissionManager().getGroupByName(args[1]);
        group.addUser(user);
    }
    private void deleteGroupPermission(String[] args) {
        Group group = Main.getInstance().getPermissionManager().getGroupByName(args[1]);
        Permission perm = new Permission(args[2], false);
        group.removePermission(perm);
    }

}