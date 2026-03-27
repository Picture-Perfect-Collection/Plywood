package org.purpurmc.purpur.command;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.purpurmc.purpur.PurpurConfig;
import org.jetbrains.annotations.NotNull;

public class PurpurCommand extends Command {

    public PurpurCommand(@NotNull String name) {
        super(name);
        this.description = "Purpur related commands";
        this.usageMessage = "/purpur reload";
        this.setPermission("bukkit.command.purpur");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) return true;

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            PurpurConfig.init(new File("purpur.yml"));
            sender.sendMessage(ChatColor.GREEN + "Purpur config reloaded.");
        } else if (args.length > 0 && args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(ChatColor.GREEN + "Plywood (Purpur-compatible) for Fabric");
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /purpur <reload|version>");
        }
        return true;
    }
}
