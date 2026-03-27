package org.plywoodmc;

/**
 * Plywood - Bukkit/Spigot/Paper/Purpur API for Fabric
 * A fork of Cardboard by CardboardPowered
 *
 * @author anarxyyyy
 */
public final class PlywoodInfo {

    public static final String NAME = "Plywood";
    public static final String DESCRIPTION = "Bukkit/Spigot/Paper/Purpur API implementation for Fabric";
    public static final String AUTHOR = "anarxyyyy";
    public static final String BASED_ON = "Cardboard by CardboardPowered";

    private PlywoodInfo() {}

    public static String getVersion() {
        try {
            return GitVersion.GIT_SHA;
        } catch (Throwable t) {
            return "unknown";
        }
    }

    public static String getBranch() {
        try {
            return GitVersion.GIT_BRANCH;
        } catch (Throwable t) {
            return "unknown";
        }
    }
}
