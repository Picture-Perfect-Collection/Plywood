package org.spigotmc;

import org.bukkit.configuration.file.YamlConfiguration;

public class SpigotWorldConfig {

    private final String worldName;
    private final YamlConfiguration config;
    private boolean verbose;

    public int witherExplosionRadius;
    public int entityTrackingRange;
    public int entityActivationRange;
    public int miscActivationRange;
    public int animalActivationRange;
    public int monsterActivationRange;
    public int raiderActivationRange;
    public int waterActivationRange;
    public int flyingMonsterActivationRange;
    public int villagerActivationRange;
    public boolean tickInactiveVillagers;
    public int hopperTransfer;
    public int hopperCheck;
    public int hopperAmount;
    public int itemDespawnRate;
    public int arrowDespawnRate;
    public int tridentDespawnRate;
    public boolean nerfSpawnerMobs;
    public int mobSpawnRange;
    public int itemMerge;
    public int expMerge;
    public double viewDistance;
    public double simulationDistance;
    public int hangingTickFrequency;
    public int tileMaxTickTime;
    public int entityMaxTickTime;
    public int maxTntTicksPerTick;
    public int dragonDeathSoundRadius;
    public boolean zombieAggressiveTowardsVillager;
    public boolean nerf_spawner_mobs;
    public int maxEntityCollisions;
    public double mergeRadius;

    public SpigotWorldConfig(String worldName) {
        this.worldName = worldName;
        this.config = SpigotConfig.config;
        init();
    }

    private void init() {
        witherExplosionRadius = getInt("wither-explosion-radius", 1);
        entityActivationRange = getInt("entity-activation-range.animals", 32);
        animalActivationRange = getInt("entity-activation-range.animals", 32);
        monsterActivationRange = getInt("entity-activation-range.monsters", 32);
        raiderActivationRange = getInt("entity-activation-range.raiders", 48);
        miscActivationRange = getInt("entity-activation-range.misc", 16);
        waterActivationRange = getInt("entity-activation-range.water", 16);
        flyingMonsterActivationRange = getInt("entity-activation-range.flying-monsters", 32);
        villagerActivationRange = getInt("entity-activation-range.villagers", 32);
        tickInactiveVillagers = getBoolean("entity-activation-range.tick-inactive-villagers", true);
        hopperTransfer = getInt("ticks-per.hopper-transfer", 8);
        hopperCheck = getInt("ticks-per.hopper-check", 1);
        hopperAmount = getInt("hopper-amount", 1);
        itemDespawnRate = getInt("item-despawn-rate", 6000);
        arrowDespawnRate = getInt("arrow-despawn-rate", 1200);
        tridentDespawnRate = getInt("trident-despawn-rate", 1200);
        nerfSpawnerMobs = getBoolean("nerf-spawner-mobs", false);
        mobSpawnRange = getInt("mob-spawn-range", 8);
        itemMerge = getInt("merge-radius.item", 2);
        expMerge = getInt("merge-radius.exp", 3);
        maxEntityCollisions = getInt("max-entity-collisions", 8);
        dragonDeathSoundRadius = getInt("dragon-death-sound-radius", 0);
        zombieAggressiveTowardsVillager = getBoolean("zombie-aggressive-towards-villager", true);
        hangingTickFrequency = getInt("hanging-tick-frequency", 100);
        tileMaxTickTime = getInt("max-tick-time.tile", 50);
        entityMaxTickTime = getInt("max-tick-time.entity", 50);
        maxTntTicksPerTick = getInt("max-tnt-per-tick", 100);
    }

    private boolean getBoolean(String path, boolean def) {
        if (config == null) return def;
        config.addDefault("world-settings.default." + path, def);
        return config.getBoolean("world-settings." + worldName + "." + path, config.getBoolean("world-settings.default." + path));
    }

    private int getInt(String path, int def) {
        if (config == null) return def;
        config.addDefault("world-settings.default." + path, def);
        return config.getInt("world-settings." + worldName + "." + path, config.getInt("world-settings.default." + path));
    }

    private double getDouble(String path, double def) {
        if (config == null) return def;
        config.addDefault("world-settings.default." + path, def);
        return config.getDouble("world-settings." + worldName + "." + path, config.getDouble("world-settings.default." + path));
    }

    private String getString(String path, String def) {
        if (config == null) return def;
        config.addDefault("world-settings.default." + path, def);
        return config.getString("world-settings." + worldName + "." + path, config.getString("world-settings.default." + path));
    }
}
