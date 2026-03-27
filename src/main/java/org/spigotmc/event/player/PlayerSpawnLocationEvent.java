package org.spigotmc.event.player;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerSpawnLocationEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private Location spawnLocation;

    public PlayerSpawnLocationEvent(@NotNull final Player who, @NotNull Location spawnLocation) {
        super(who);
        this.spawnLocation = spawnLocation;
    }

    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(@NotNull Location location) {
        this.spawnLocation = location;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
