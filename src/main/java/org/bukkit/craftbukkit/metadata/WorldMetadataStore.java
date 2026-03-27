package org.bukkit.craftbukkit.metadata;

import org.bukkit.World;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;
import org.jetbrains.annotations.NotNull;

public class WorldMetadataStore extends MetadataStoreBase<World> implements MetadataStore<World> {
    @Override
    protected @NotNull String disambiguate(@NotNull World subject, @NotNull String metadataKey) {
        return subject.getName() + ":" + metadataKey;
    }
}
