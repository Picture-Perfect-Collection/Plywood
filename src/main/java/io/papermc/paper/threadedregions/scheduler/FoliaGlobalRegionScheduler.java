package io.papermc.paper.threadedregions.scheduler;

import java.util.function.Consumer;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Folia-compatible global scheduler facade for single-threaded runtimes.
 */
public final class FoliaGlobalRegionScheduler implements GlobalRegionScheduler {

    private final MainThreadGlobalRegionScheduler delegate = new MainThreadGlobalRegionScheduler();

    @Override
    public void execute(@NotNull Plugin plugin, @NotNull Runnable run) {
        this.delegate.execute(plugin, run);
    }

    @Override
    public @NotNull ScheduledTask run(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task) {
        return this.delegate.run(plugin, task);
    }

    @Override
    public @NotNull ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task, long delayTicks) {
        return this.delegate.runDelayed(plugin, task, delayTicks);
    }

    @Override
    public @NotNull ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                                 long initialDelayTicks, long periodTicks) {
        return this.delegate.runAtFixedRate(plugin, task, initialDelayTicks, periodTicks);
    }

    @Override
    public void cancelTasks(@NotNull Plugin plugin) {
        this.delegate.cancelTasks(plugin);
    }
}
