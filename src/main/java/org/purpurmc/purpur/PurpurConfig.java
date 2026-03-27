package org.purpurmc.purpur;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class PurpurConfig {

    private static File CONFIG_FILE;
    public static YamlConfiguration config;
    static int version;
    static boolean verbose;

    public static String serverName = "Unknown Server";
    public static boolean laggingThreshold = false;
    public static int laggingThresholdTPS = 19;

    public static boolean useAlternateKeepAlive = false;

    public static boolean enderChestSixRows = false;
    public static boolean enderChestPermissionRows = false;

    public static boolean afkEnabled = false;
    public static int afkTimeout = 0;
    public static boolean afkKickEnabled = false;
    public static boolean afkBroadcastEnabled = true;
    public static String afkBroadcastAway = "&e%s is now AFK";
    public static String afkBroadcastBack = "&e%s is no longer AFK";

    public static boolean ridablesEnabled = false;

    public static void init(File configFile) {
        CONFIG_FILE = configFile;
        config = new YamlConfiguration();
        try {
            if (CONFIG_FILE.exists()) {
                config.load(CONFIG_FILE);
            }
        } catch (IOException | InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not load purpur.yml", ex);
        }

        config.options().copyDefaults(true);
        verbose = getBoolean("verbose", false);
        version = getInt("config-version", 1);

        readConfig(PurpurConfig.class, null);

        try {
            config.save(CONFIG_FILE);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save " + CONFIG_FILE, ex);
        }
    }

    static void readConfig(Class<?> clazz, Object instance) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isPrivate(method.getModifiers())) {
                if (method.getParameterTypes().length == 0 && method.getReturnType() == Void.TYPE) {
                    try {
                        method.setAccessible(true);
                        method.invoke(instance);
                    } catch (InvocationTargetException ex) {
                        throw new RuntimeException(ex.getCause());
                    } catch (Exception ex) {
                        Bukkit.getLogger().log(Level.SEVERE, "Error invoking " + method, ex);
                    }
                }
            }
        }
    }

    private static void settings() {
        serverName = getString("settings.server-name", serverName);
        laggingThresholdTPS = getInt("settings.lagging-threshold", laggingThresholdTPS);
        useAlternateKeepAlive = getBoolean("settings.use-alternate-keepalive", useAlternateKeepAlive);
        enderChestSixRows = getBoolean("settings.blocks.ender_chest.six-rows", enderChestSixRows);
        enderChestPermissionRows = getBoolean("settings.blocks.ender_chest.use-permissions-for-rows", enderChestPermissionRows);
    }

    private static void afkSettings() {
        afkEnabled = getBoolean("settings.afk.enabled", afkEnabled);
        afkTimeout = getInt("settings.afk.timeout", afkTimeout);
        afkKickEnabled = getBoolean("settings.afk.kick.enabled", afkKickEnabled);
        afkBroadcastEnabled = getBoolean("settings.afk.broadcast.enabled", afkBroadcastEnabled);
        afkBroadcastAway = getString("settings.afk.broadcast.away", afkBroadcastAway);
        afkBroadcastBack = getString("settings.afk.broadcast.back", afkBroadcastBack);
    }

    private static void ridableSettings() {
        ridablesEnabled = getBoolean("settings.ridables.enabled", ridablesEnabled);
    }

    private static boolean getBoolean(String path, boolean def) {
        config.addDefault(path, def);
        return config.getBoolean(path, config.getBoolean(path));
    }

    private static int getInt(String path, int def) {
        config.addDefault(path, def);
        return config.getInt(path, config.getInt(path));
    }

    private static double getDouble(String path, double def) {
        config.addDefault(path, def);
        return config.getDouble(path, config.getDouble(path));
    }

    private static String getString(String path, String def) {
        config.addDefault(path, def);
        return config.getString(path, config.getString(path));
    }

    private static List<String> getList(String path, List<String> def) {
        config.addDefault(path, def);
        return config.getStringList(path);
    }
}
