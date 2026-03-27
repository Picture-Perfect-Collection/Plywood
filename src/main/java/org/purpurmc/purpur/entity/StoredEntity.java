package org.purpurmc.purpur.entity;

import org.bukkit.Nameable;
import org.bukkit.block.EntityBlockStorage;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StoredEntity<T extends Entity> extends PersistentDataHolder, Nameable {
    boolean hasBeenReleased();
    @Nullable T release();
    @Nullable EntityBlockStorage<T> getBlockStorage();
    @NotNull EntityType getType();
    void update();
}
