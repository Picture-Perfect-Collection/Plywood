package org.spigotmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.AuthorNagException;

public class CustomTimingsHandler {

    private final String name;
    private final CustomTimingsHandler parent;

    public CustomTimingsHandler(String name) {
        this(name, null);
    }

    public CustomTimingsHandler(String name, CustomTimingsHandler parent) {
        this.name = name;
        this.parent = parent;
    }

    public void startTiming() {
        // No-op: Use Spark for profiling
    }

    public void stopTiming() {
        // No-op: Use Spark for profiling
    }

    public void reset() {
        // No-op
    }

    public static void reload() {
        // No-op
    }

    public static void tick() {
        // No-op
    }

    public String getName() {
        return name;
    }
}
