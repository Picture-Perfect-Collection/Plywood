package org.purpurmc.purpur.event.entity;

import org.bukkit.ExplosionResult;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.ApiStatus;
import java.util.Collections;

public class PreEntityExplodeEvent extends EntityExplodeEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final float yield;
    private final Location location;

    @ApiStatus.Internal
    public PreEntityExplodeEvent(org.bukkit.entity.Entity what, final Location location, final float yield, ExplosionResult result) {
        super(what, location, Collections.emptyList(), yield, result);
        this.cancelled = false;
        this.yield = yield;
        this.location = location;
    }

    public float getYield() { return yield; }
    public Location getLocation() { return location; }
    @Override public boolean isCancelled() { return this.cancelled; }
    @Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
