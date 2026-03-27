package org.purpurmc.purpur.event.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;

public class PlayerBookTooLargeEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final ItemStack book;
    private boolean kickPlayer = true;

    @ApiStatus.Internal
    public PlayerBookTooLargeEvent(Player player, ItemStack book) {
        super(player, !Bukkit.isPrimaryThread());
        this.book = book;
    }

    public ItemStack getBook() { return book; }
    public boolean shouldKickPlayer() { return kickPlayer; }
    public void setShouldKickPlayer(boolean kickPlayer) { this.kickPlayer = kickPlayer; }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
