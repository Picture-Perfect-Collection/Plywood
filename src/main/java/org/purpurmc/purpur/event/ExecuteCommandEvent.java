package org.purpurmc.purpur.event;

import com.google.common.base.Preconditions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public class ExecuteCommandEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private CommandSender sender;
    private Command command;
    private String label;
    private @Nullable String[] args;

    @ApiStatus.Internal
    public ExecuteCommandEvent(CommandSender sender, Command command, String label, @Nullable String[] args) {
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;
    }

    public Command getCommand() { return command; }
    public void setCommand(Command command) {
        Preconditions.checkArgument(command != null, "Command cannot be null");
        this.command = command;
    }
    public CommandSender getSender() { return sender; }
    public void setSender(final CommandSender sender) {
        Preconditions.checkArgument(sender != null, "Sender cannot be null");
        this.sender = sender;
    }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String[] getArgs() { return args; }
    public void setArgs(String[] args) { this.args = args; }

    @Override public boolean isCancelled() { return cancel; }
    @Override public void setCancelled(boolean cancel) { this.cancel = cancel; }
    @Override public HandlerList getHandlers() { return handlers; }
    public static HandlerList getHandlerList() { return handlers; }
}
