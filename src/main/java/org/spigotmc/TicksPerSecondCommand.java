package org.spigotmc;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TicksPerSecondCommand extends Command {

    public TicksPerSecondCommand(@NotNull String name) {
        super(name);
        this.description = "Gets the current ticks per second for the server";
        this.usageMessage = "/tps";
        this.setPermission("bukkit.command.tps");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
        if (!testPermission(sender)) return true;

        double[] tps = org.bukkit.Bukkit.getServer().getTPS();
        StringBuilder sb = new StringBuilder();
        sb.append(ChatColor.GOLD).append("TPS from last 1m, 5m, 15m: ");
        for (int i = 0; i < tps.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(formatTps(tps[i]));
        }
        sender.sendMessage(sb.toString());
        return true;
    }

    private String formatTps(double tps) {
        return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
                + ((tps > 21.0) ? "*" : "") + Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
    }
}
