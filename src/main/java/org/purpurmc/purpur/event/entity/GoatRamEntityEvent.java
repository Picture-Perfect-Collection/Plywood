package org.purpurmc.purpur.event.entity;

import org.bukkit.entity.Goat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;

public class GoatRamEntityEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final LivingEntity rammedEntity;
    private boolean cancelled;

    @ApiStatus.Internal
    public GoatRamEntityEvent(Goat goat, LivingEntity rammedEntity) {
        super(goat);
        this.rammedEntity = rammedEntity;
    }

    public LivingEntity getRammedEntity() { return this.rammedEntity; }
    @Override public Goat getEntity() { return (Goat) super.getEntity(); }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
    @Override public boolean isCancelled() { return this.cancelled; }
    @Override public void setCancelled(boolean cancel) { this.cancelled = cancel; }
}
