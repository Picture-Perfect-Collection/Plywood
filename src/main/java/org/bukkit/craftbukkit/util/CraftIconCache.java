package org.bukkit.craftbukkit.util;

import org.bukkit.util.CachedServerIcon;

public class CraftIconCache implements CachedServerIcon {
    public final String value;

    public CraftIconCache(final String value) {
        this.value = value;
    }

    public String getData() {
        return value;
    }

    @Override
    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}
