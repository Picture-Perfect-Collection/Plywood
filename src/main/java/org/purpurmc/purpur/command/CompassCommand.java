package org.purpurmc.purpur.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CompassCommand extends Command {

    public CompassCommand(@NotNull String name) {
        super(name);
        this.description = "Toggle the compass action bar";
        this.usageMessage = "/compass";
        this.setPermission("bukkit.command.compass");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) return true;
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }
        sender.sendMessage(ChatColor.GREEN + "Compass toggled.");
        return true;
    }
}
