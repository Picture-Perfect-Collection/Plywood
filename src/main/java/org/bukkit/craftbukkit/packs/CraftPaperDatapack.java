package org.bukkit.craftbukkit.packs;

import io.papermc.paper.adventure.PaperAdventure;
import io.papermc.paper.datapack.Datapack;
import io.papermc.paper.datapack.DatapackSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.kyori.adventure.text.Component;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.PackSource;
import org.bukkit.FeatureFlag;
import org.bukkit.craftbukkit.CraftFeatureFlag;
import org.bukkit.craftbukkit.CraftServer;
import org.jetbrains.annotations.NotNull;

public final class CraftPaperDatapack implements Datapack {

    private final Pack pack;
    private final boolean enabled;

    public CraftPaperDatapack(Pack pack, boolean enabled) {
        this.pack = pack;
        this.enabled = enabled;
    }

    @Override
    public @NotNull String getName() {
        return this.pack.getId();
    }

    @Override
    public @NotNull Component getTitle() {
        return PaperAdventure.asAdventure(this.pack.getTitle());
    }

    @Override
    public @NotNull Component getDescription() {
        return PaperAdventure.asAdventure(this.pack.getDescription());
    }

    @Override
    public boolean isRequired() {
        return this.pack.isRequired();
    }

    @Override
    public @NotNull Compatibility getCompatibility() {
        return Compatibility.valueOf(this.pack.getCompatibility().name());
    }

    @Override
    public @NotNull Set<FeatureFlag> getRequiredFeatures() {
        return CraftFeatureFlag.getFromNMS(this.pack.getRequestedFeatures()).stream()
            .map(FeatureFlag.class::cast)
            .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public @NotNull DatapackSource getSource() {
        PackSource src = this.pack.location().source();
        if (src == PackSource.DEFAULT) return DatapackSource.DEFAULT;
        if (src == PackSource.BUILT_IN) return DatapackSource.BUILT_IN;
        if (src == PackSource.FEATURE) return DatapackSource.FEATURE;
        if (src == PackSource.WORLD) return DatapackSource.WORLD;
        if (src == PackSource.SERVER) return DatapackSource.SERVER;
        return DatapackSource.DEFAULT;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        DedicatedServer server = CraftServer.console;
        PackRepository repo = server.getPackRepository();
        List<Pack> enabledPacks = new ArrayList<>(repo.getSelectedPacks());
        Pack packToChange = repo.getPack(this.getName());
        if (packToChange == null) {
            throw new IllegalStateException("Cannot toggle state of pack that doesn't exist: " + this.getName());
        }
        if (enabled == enabledPacks.contains(packToChange)) {
            return;
        }
        if (enabled) {
            packToChange.getDefaultPosition().insert(enabledPacks, packToChange, Pack::selectionConfig, false);
        } else {
            enabledPacks.remove(packToChange);
        }
        server.reloadResources(enabledPacks.stream().map(Pack::getId).toList());
    }

    @Override
    public @NotNull Component computeDisplayName() {
        return PaperAdventure.asAdventure(this.pack.getChatLink(this.enabled));
    }
}
