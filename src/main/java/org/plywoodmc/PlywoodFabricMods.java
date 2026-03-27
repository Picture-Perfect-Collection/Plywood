package org.plywoodmc;

import java.util.Optional;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;

/**
 * Resolves this mod's Fabric container after the Cardboard → Plywood rename ({@code plywood} id, legacy {@code cardboard}).
 */
public final class PlywoodFabricMods {

    public static final String MOD_ID = "plywood";
    private static final String LEGACY_MOD_ID = "cardboard";

    private PlywoodFabricMods() {}

    public static Optional<ModContainer> modContainer() {
        FabricLoader loader = FabricLoader.getInstance();
        Optional<ModContainer> primary = loader.getModContainer(MOD_ID);
        return primary.isPresent() ? primary : loader.getModContainer(LEGACY_MOD_ID);
    }

    public static Optional<ModMetadata> metadata() {
        return modContainer().map(ModContainer::getMetadata);
    }

    /**
     * Semver-friendly version string from Fabric metadata, or empty if the mod is not loaded.
     */
    public static String friendlyVersionString() {
        return metadata()
            .map(m -> m.getVersion().getFriendlyString())
            .orElse("");
    }
}
