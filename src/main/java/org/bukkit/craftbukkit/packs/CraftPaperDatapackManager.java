package org.bukkit.craftbukkit.packs;

import com.google.common.collect.Collections2;
import io.papermc.paper.datapack.Datapack;
import io.papermc.paper.datapack.DatapackManager;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Predicate;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CraftPaperDatapackManager implements DatapackManager {

    private final PackRepository repository;

    public CraftPaperDatapackManager(PackRepository repository) {
        this.repository = repository;
    }

    @Override
    public void refreshPacks() {
        this.repository.reload();
    }

    @Override
    public @Nullable Datapack getPack(String name) {
        Pack pack = this.repository.getPack(name);
        if (pack == null) {
            return null;
        }
        return new CraftPaperDatapack(pack, this.repository.getSelectedPacks().contains(pack));
    }

    @Override
    public @NotNull Collection<Datapack> getPacks() {
        Collection<Pack> enabledPacks = this.repository.getSelectedPacks();
        return transformPacks(this.repository.getAvailablePacks(), enabledPacks::contains);
    }

    @Override
    public @NotNull Collection<Datapack> getEnabledPacks() {
        return transformPacks(this.repository.getSelectedPacks(), pack -> true);
    }

    private Collection<Datapack> transformPacks(Collection<Pack> packs, Predicate<Pack> enabled) {
        return Collections.unmodifiableCollection(
            Collections2.transform(packs, pack -> new CraftPaperDatapack(pack, enabled.test(pack)))
        );
    }
}
