package org.purpurmc.purpur;

import com.destroystokyo.paper.util.VersionFetcher;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class PurpurVersionFetcher implements VersionFetcher {

    @Override
    public long getCacheTime() {
        return 720000;
    }

    @Override
    public Component getVersionMessage() {
        return Component.text("Plywood (Purpur-compatible) " + Bukkit.getVersion(), NamedTextColor.GREEN);
    }
}
