package org.purpurmc.purpur.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;

public class BeeStartedPollinatingEvent extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Location location;

    @ApiStatus.Internal
    public BeeStartedPollinatingEvent(Bee bee, Location location) {
        super(bee);
        this.location = location;
    }

    @Override public Bee getEntity() { return (Bee) super.getEntity(); }
    public Location getLocation() { return this.location; }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
