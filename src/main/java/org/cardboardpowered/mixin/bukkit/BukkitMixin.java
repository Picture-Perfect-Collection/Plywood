package org.cardboardpowered.mixin.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.CraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import org.plywoodmc.PlywoodFabricMods;

@Mixin(value = Bukkit.class, remap = false)
public class BukkitMixin {

	/**
	 * Use Fabric's ModMetadata for version
	 * info instead of grabbing from META-INF
	 * 
	 * @author cardboard
	 * @reason META-INF
	 */
	@Overwrite(remap = false)
    public static String getVersionMessage() {
		String ver = PlywoodFabricMods.friendlyVersionString();
        if (ver.isEmpty() || ver.contains("version") || ver.contains("${")) {
            ver = CraftServer.INSTANCE != null ? CraftServer.INSTANCE.getShortVersion() : "unknown";
        }

		return "This server is running " + Bukkit.getName() + " version " + ver + " (Implementing API version " + Bukkit.getBukkitVersion() + ")";
    }
	
}
