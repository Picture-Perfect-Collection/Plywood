package org.purpurmc.purpur.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Bee;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public class BeeStopPollinatingEvent extends EntityEvent {
    private static final HandlerList handlers = new HandlerList();
    private final Location location;
    private final boolean success;

    @ApiStatus.Internal
    public BeeStopPollinatingEvent(Bee bee, @Nullable Location location, boolean success) {
        super(bee);
        this.location = location;
        this.success = success;
    }

    @Override public Bee getEntity() { return (Bee) super.getEntity(); }
    @Nullable public Location getLocation() { return location; }
    public boolean wasSuccessful() { return success; }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
