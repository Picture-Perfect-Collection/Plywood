package org.purpurmc.purpur.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class UptimeCommand extends Command {

    private static final long START_TIME = System.currentTimeMillis();

    public UptimeCommand(@NotNull String name) {
        super(name);
        this.description = "Shows the server uptime";
        this.usageMessage = "/uptime";
        this.setPermission("bukkit.command.uptime");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) return true;

        long uptime = System.currentTimeMillis() - START_TIME;
        long seconds = uptime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("d ");
        if (hours % 24 > 0) sb.append(hours % 24).append("h ");
        if (minutes % 60 > 0) sb.append(minutes % 60).append("m ");
        sb.append(seconds % 60).append("s");

        sender.sendMessage(ChatColor.GREEN + "Server uptime: " + ChatColor.GOLD + sb.toString().trim());
        return true;
    }
}
