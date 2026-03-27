package org.purpurmc.purpur.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PingCommand extends Command {

    public PingCommand(@NotNull String name) {
        super(name);
        this.description = "Shows the player's ping";
        this.usageMessage = "/ping [player]";
        this.setPermission("bukkit.command.ping");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) return true;

        Player target;
        if (args.length > 0) {
            target = org.bukkit.Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found.");
                return true;
            }
        } else if (sender instanceof Player) {
            target = (Player) sender;
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /ping <player>");
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + target.getName() + "'s ping: " + ChatColor.GOLD + target.getPing() + "ms");
        return true;
    }
}
