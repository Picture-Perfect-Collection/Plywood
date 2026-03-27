package org.purpurmc.purpur.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreditsCommand extends Command {

    public CreditsCommand(@NotNull String name) {
        super(name);
        this.description = "Shows the credits screen to a player";
        this.usageMessage = "/credits [player]";
        this.setPermission("bukkit.command.credits");
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
            sender.sendMessage(ChatColor.RED + "Usage: /credits <player>");
            return true;
        }

        sender.sendMessage(ChatColor.GREEN + "Showing credits to " + target.getName());
        return true;
    }
}
