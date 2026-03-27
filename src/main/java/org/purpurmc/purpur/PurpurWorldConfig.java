package org.purpurmc.purpur;

import org.bukkit.configuration.file.YamlConfiguration;

public class PurpurWorldConfig {

    private final String worldName;
    private final YamlConfiguration config;

    public boolean ridablesEnabled = false;
    public boolean ridablesInWater = false;
    public boolean mobsCanAlwaysBurnInDay = false;
    public boolean zombieAggressiveTowardsVillager = true;

    public double ironGolemHealthMax = 100;
    public boolean ironGolemCanSwim = false;

    public boolean villagerLobotomizeEnabled = false;
    public int villagerLobotomizeCheckInterval = 100;

    public boolean llamaJoinCaravan = true;

    public boolean beeFriendlyBees = false;
    public int beeBreedingTicks = 6000;

    public boolean phantomFlamesEnabled = false;

    public boolean creeperChargedChance = false;
    public double creeperChargedChancePercent = 0.0;

    public PurpurWorldConfig(String worldName) {
        this.worldName = worldName;
        this.config = PurpurConfig.config;
        init();
    }

    private void init() {
        ridablesEnabled = getBoolean("ridables.enabled", ridablesEnabled);
        ridablesInWater = getBoolean("ridables.in-water", ridablesInWater);
        mobsCanAlwaysBurnInDay = getBoolean("mobs.can-always-burn-in-day", mobsCanAlwaysBurnInDay);
        zombieAggressiveTowardsVillager = getBoolean("mobs.zombie.aggressive-towards-villager", zombieAggressiveTowardsVillager);
        ironGolemHealthMax = getDouble("mobs.iron_golem.health-max", ironGolemHealthMax);
        ironGolemCanSwim = getBoolean("mobs.iron_golem.can-swim", ironGolemCanSwim);
        villagerLobotomizeEnabled = getBoolean("mobs.villager.lobotomize.enabled", villagerLobotomizeEnabled);
        villagerLobotomizeCheckInterval = getInt("mobs.villager.lobotomize.check-interval", villagerLobotomizeCheckInterval);
        llamaJoinCaravan = getBoolean("mobs.llama.join-caravan", llamaJoinCaravan);
        beeFriendlyBees = getBoolean("mobs.bee.friendly-bees", beeFriendlyBees);
        beeBreedingTicks = getInt("mobs.bee.breeding-ticks", beeBreedingTicks);
        phantomFlamesEnabled = getBoolean("mobs.phantom.flames.enabled", phantomFlamesEnabled);
        creeperChargedChance = getBoolean("mobs.creeper.charged-chance.enabled", creeperChargedChance);
        creeperChargedChancePercent = getDouble("mobs.creeper.charged-chance.percent", creeperChargedChancePercent);
    }

    private boolean getBoolean(String path, boolean def) {
        if (config == null) return def;
        config.addDefault("world-settings.default." + path, def);
        return config.getBoolean("world-settings." + worldName + "." + path,
                config.getBoolean("world-settings.default." + path));
    }

    private int getInt(String path, int def) {
        if (config == null) return def;
        config.addDefault("world-settings.default." + path, def);
        return config.getInt("world-settings." + worldName + "." + path,
                config.getInt("world-settings.default." + path));
    }

    private double getDouble(String path, double def) {
        if (config == null) return def;
        config.addDefault("world-settings.default." + path, def);
        return config.getDouble("world-settings." + worldName + "." + path,
                config.getDouble("world-settings.default." + path));
    }

    private String getString(String path, String def) {
        if (config == null) return def;
        config.addDefault("world-settings.default." + path, def);
        return config.getString("world-settings." + worldName + "." + path,
                config.getString("world-settings.default." + path));
    }
}
