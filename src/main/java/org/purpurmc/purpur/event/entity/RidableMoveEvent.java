package org.purpurmc.purpur.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;

public class RidableMoveEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final Player rider;
    private Location from;
    private Location to;

    @ApiStatus.Internal
    public RidableMoveEvent(Mob entity, Player rider, Location from, Location to) {
        super(entity);
        this.rider = rider;
        this.from = from;
        this.to = to;
    }

    @Override public Mob getEntity() { return (Mob) entity; }
    public Player getRider() { return rider; }
    public boolean isCancelled() { return canceled; }
    public void setCancelled(boolean cancel) { canceled = cancel; }
    public Location getFrom() { return from; }
    public void setFrom(Location from) { validateLocation(from); this.from = from; }
    public Location getTo() { return to; }
    public void setTo(Location to) { validateLocation(to); this.to = to; }

    private void validateLocation(Location loc) {
        Preconditions.checkArgument(loc != null, "Cannot use null location!");
        Preconditions.checkArgument(loc.getWorld() != null, "Cannot use null location with null world!");
    }

    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
