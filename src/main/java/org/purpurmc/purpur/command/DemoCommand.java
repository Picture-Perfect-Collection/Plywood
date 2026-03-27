package org.purpurmc.purpur.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DemoCommand extends Command {

    public DemoCommand(@NotNull String name) {
        super(name);
        this.description = "Shows the demo screen to a player";
        this.usageMessage = "/demo <player>";
        this.setPermission("bukkit.command.demo");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) return true;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /demo <player>");
            return true;
        }

        Player target = org.bukkit.Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "Showing demo screen to " + target.getName());
        return true;
    }
}
