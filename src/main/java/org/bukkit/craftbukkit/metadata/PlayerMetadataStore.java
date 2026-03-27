package org.bukkit.craftbukkit.metadata;

import org.bukkit.OfflinePlayer;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;
import org.jetbrains.annotations.NotNull;

public class PlayerMetadataStore extends MetadataStoreBase<OfflinePlayer> implements MetadataStore<OfflinePlayer> {
    @Override
    protected @NotNull String disambiguate(@NotNull OfflinePlayer subject, @NotNull String metadataKey) {
        return subject.getUniqueId() + ":" + metadataKey;
    }
}
