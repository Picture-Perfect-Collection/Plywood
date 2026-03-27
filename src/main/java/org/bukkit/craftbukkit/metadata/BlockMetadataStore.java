package org.bukkit.craftbukkit.metadata;

import java.util.List;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BlockMetadataStore extends MetadataStoreBase<Block> implements MetadataStore<Block> {

    private final World owningWorld;

    public BlockMetadataStore(World owningWorld) {
        this.owningWorld = owningWorld;
    }

    @Override
    protected @NotNull String disambiguate(@NotNull Block subject, @NotNull String metadataKey) {
        return subject.getX() + ":" + subject.getY() + ":" + subject.getZ() + ":" + metadataKey;
    }

    @Override
    public @NotNull List<MetadataValue> getMetadata(@NotNull Block subject, @NotNull String metadataKey) {
        if (subject.getWorld() != owningWorld) {
            throw new IllegalArgumentException("Block does not belong to world " + owningWorld.getName());
        }
        return super.getMetadata(subject, metadataKey);
    }

    @Override
    public boolean hasMetadata(@NotNull Block subject, @NotNull String metadataKey) {
        if (subject.getWorld() != owningWorld) {
            throw new IllegalArgumentException("Block does not belong to world " + owningWorld.getName());
        }
        return super.hasMetadata(subject, metadataKey);
    }

    @Override
    public void removeMetadata(@NotNull Block subject, @NotNull String metadataKey, @NotNull Plugin owningPlugin) {
        if (subject.getWorld() != owningWorld) {
            throw new IllegalArgumentException("Block does not belong to world " + owningWorld.getName());
        }
        super.removeMetadata(subject, metadataKey, owningPlugin);
    }

    @Override
    public void setMetadata(@NotNull Block subject, @NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        if (subject.getWorld() != owningWorld) {
            throw new IllegalArgumentException("Block does not belong to world " + owningWorld.getName());
        }
        super.setMetadata(subject, metadataKey, newMetadataValue);
    }
}
