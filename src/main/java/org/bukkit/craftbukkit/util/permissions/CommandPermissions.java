package org.bukkit.craftbukkit.util.permissions;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.permissions.DefaultPermissions;

public final class CommandPermissions {
    private static final String ROOT = "minecraft.command";

    private CommandPermissions() {
    }

    public static Permission registerPermissions(Permission parent) {
        Permission commands = DefaultPermissions.registerPermission(ROOT, "Gives the user the ability to use all vanilla commands", parent);

        DefaultPermissions.registerPermission(ROOT + ".kick", "Allows the user to kick players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".stop", "Allows the user to stop the server", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".tp", "Allows the user to teleport players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".teleport", "Allows the user to teleport players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".give", "Allows the user to give items to players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".effect", "Allows the user to add effects to players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".gamemode", "Allows the user to change gamemodes", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".op", "Allows the user to change operators", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".deop", "Allows the user to remove operators", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".ban", "Allows the user to ban players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".unban", "Allows the user to unban players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".pardon", "Allows the user to pardon players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".banip", "Allows the user to ban IP addresses", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".pardonip", "Allows the user to pardon IP addresses", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".say", "Allows the user to say messages as the server", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".me", "Allows the user to use /me", PermissionDefault.TRUE, commands);
        DefaultPermissions.registerPermission(ROOT + ".tell", "Allows the user to privately message other players", PermissionDefault.TRUE, commands);
        DefaultPermissions.registerPermission(ROOT + ".msg", "Allows the user to privately message other players", PermissionDefault.TRUE, commands);
        DefaultPermissions.registerPermission(ROOT + ".kill", "Allows the user to kill entities", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".time", "Allows the user to alter the time of day", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".weather", "Allows the user to change the weather", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".seed", "Allows the user to view the world seed", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".list", "Allows the user to list all online players", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".help", "Allows the user to view the help menu", PermissionDefault.TRUE, commands);
        DefaultPermissions.registerPermission(ROOT + ".selector", "Allows the user to use entity selectors", PermissionDefault.OP, commands);
        DefaultPermissions.registerPermission(ROOT + ".purpur", "Allows the user to use purpur commands", PermissionDefault.OP, commands);

        commands.recalculatePermissibles();
        return commands;
    }
}
