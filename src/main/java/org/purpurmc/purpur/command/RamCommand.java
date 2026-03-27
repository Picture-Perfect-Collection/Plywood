package org.purpurmc.purpur.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RamCommand extends Command {

    public RamCommand(@NotNull String name) {
        super(name);
        this.description = "Shows the server RAM usage";
        this.usageMessage = "/ram";
        this.setPermission("bukkit.command.ram");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) return true;

        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        long totalMemory = runtime.totalMemory() / 1024 / 1024;
        long freeMemory = runtime.freeMemory() / 1024 / 1024;
        long usedMemory = totalMemory - freeMemory;

        sender.sendMessage(ChatColor.GREEN + "RAM Usage: " + ChatColor.GOLD + usedMemory + "MB" + ChatColor.GREEN + " / " + ChatColor.GOLD + maxMemory + "MB");
        sender.sendMessage(ChatColor.GREEN + "Free: " + ChatColor.GOLD + freeMemory + "MB");
        return true;
    }
}
