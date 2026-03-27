package org.spigotmc;

import java.io.File;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SpigotCommand extends Command {

    public SpigotCommand(@NotNull String name) {
        super(name);
        this.description = "Spigot related commands";
        this.usageMessage = "/spigot reload";
        this.setPermission("bukkit.command.spigot");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!testPermission(sender)) return true;

        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(ChatColor.GREEN + "Spigot config reloaded.");
            SpigotConfig.init(new File("spigot.yml"));
        } else {
            sender.sendMessage(ChatColor.RED + "Usage: /spigot reload");
        }
        return true;
    }
}
