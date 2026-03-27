package org.bukkit.craftbukkit.metadata;

import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;
import org.jetbrains.annotations.NotNull;

public class EntityMetadataStore extends MetadataStoreBase<Entity> implements MetadataStore<Entity> {
    @Override
    protected @NotNull String disambiguate(@NotNull Entity subject, @NotNull String metadataKey) {
        return subject.getUniqueId() + ":" + metadataKey;
    }
}
