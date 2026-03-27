package org.purpurmc.purpur.event.entity;

import org.bukkit.entity.Llama;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;

public class LlamaJoinCaravanEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean canceled;
    private final Llama head;

    @ApiStatus.Internal
    public LlamaJoinCaravanEvent(Llama llama, Llama head) {
        super(llama);
        this.head = head;
    }

    @Override public Llama getEntity() { return (Llama) entity; }
    public Llama getHead() { return head; }
    @Override public boolean isCancelled() { return canceled; }
    @Override public void setCancelled(boolean cancel) { canceled = cancel; }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
