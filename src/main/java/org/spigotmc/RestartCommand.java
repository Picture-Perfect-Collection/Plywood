package org.spigotmc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RestartCommand extends Command {

    public RestartCommand(@NotNull String name) {
        super(name);
        this.description = "Restarts the server";
        this.usageMessage = "/restart";
        this.setPermission("bukkit.command.restart");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
        if (!testPermission(sender)) return true;
        sender.sendMessage("Restart is not supported in Plywood. Please stop and start the server manually.");
        return true;
    }
}
