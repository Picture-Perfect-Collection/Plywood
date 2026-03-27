package org.bukkit.craftbukkit.event;

import org.bukkit.Location;

public record PortalEventResult(Location location, boolean cancelled) {
}
