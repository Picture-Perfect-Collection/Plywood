package io.papermc.paper.threadedregions.scheduler;

import java.util.function.Consumer;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Folia-compatible region scheduler facade.
 *
 * On a non-region-threaded runtime this maps region tasks to global-region tasks.
 */
public final class FoliaRegionScheduler implements RegionScheduler {

    @Override
    public void execute(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull Runnable run) {
        plugin.getServer().getGlobalRegionScheduler().execute(plugin, run);
    }

    @Override
    public @NotNull ScheduledTask run(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ,
                                      @NotNull Consumer<ScheduledTask> task) {
        return plugin.getServer().getGlobalRegionScheduler().run(plugin, task);
    }

    @Override
    public @NotNull ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ,
                                             @NotNull Consumer<ScheduledTask> task, long delayTicks) {
        return plugin.getServer().getGlobalRegionScheduler().runDelayed(plugin, task, delayTicks);
    }

    @Override
    public @NotNull ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ,
                                                 @NotNull Consumer<ScheduledTask> task, long initialDelayTicks, long periodTicks) {
        return plugin.getServer().getGlobalRegionScheduler().runAtFixedRate(plugin, task, initialDelayTicks, periodTicks);
    }
}
