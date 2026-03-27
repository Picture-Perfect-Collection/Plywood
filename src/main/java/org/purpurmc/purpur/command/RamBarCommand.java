package org.purpurmc.purpur.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RamBarCommand extends Command {

    public RamBarCommand(@NotNull String name) {
        super(name);
        this.description = "Toggle the RAM bar";
        this.usageMessage = "/rambar";
        this.setPermission("bukkit.command.rambar");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) return true;
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }
        sender.sendMessage(ChatColor.GREEN + "RAM bar toggled.");
        return true;
    }
}
