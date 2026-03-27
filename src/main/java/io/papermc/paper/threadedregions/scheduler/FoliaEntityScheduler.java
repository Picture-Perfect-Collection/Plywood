package io.papermc.paper.threadedregions.scheduler;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Folia-compatible entity scheduler for a non-region-threaded runtime.
 *
 * Tasks execute on the main thread and check entity validity before execution.
 */
public final class FoliaEntityScheduler implements EntityScheduler {

    private final CraftEntity entity;

    public FoliaEntityScheduler(final CraftEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean execute(@NotNull Plugin plugin, @NotNull Runnable run, @Nullable Runnable retired, long delay) {
        Objects.requireNonNull(plugin, "Plugin may not be null");
        Objects.requireNonNull(run, "Runnable may not be null");
        if (!plugin.isEnabled()) {
            return false;
        }
        final long actualDelay = Math.max(1L, delay);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!plugin.isEnabled()) {
                return;
            }
            if (!this.entity.isValid() || this.entity.isDead()) {
                if (retired != null) {
                    retired.run();
                }
                return;
            }
            run.run();
        }, actualDelay);
        return true;
    }

    @Override
    public @Nullable ScheduledTask run(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task, @Nullable Runnable retired) {
        return this.runDelayed(plugin, task, retired, 1L);
    }

    @Override
    public @Nullable ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                              @Nullable Runnable retired, long delayTicks) {
        Objects.requireNonNull(plugin, "Plugin may not be null");
        Objects.requireNonNull(task, "Task may not be null");
        if (delayTicks <= 0) {
            throw new IllegalArgumentException("Delay ticks may not be <= 0");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register task while disabled");
        }
        if (!this.entity.isValid() || this.entity.isDead()) {
            if (retired != null) {
                retired.run();
            }
            return null;
        }

        EntityScheduledTask ret = new EntityScheduledTask(plugin, task, retired, false);
        BukkitTask bt = Bukkit.getScheduler().runTaskLater(plugin, ret::tick, delayTicks);
        ret.setBukkitTask(bt);
        return ret;
    }

    @Override
    public @Nullable ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                                  @Nullable Runnable retired, long initialDelayTicks, long periodTicks) {
        Objects.requireNonNull(plugin, "Plugin may not be null");
        Objects.requireNonNull(task, "Task may not be null");
        if (initialDelayTicks <= 0) {
            throw new IllegalArgumentException("Initial delay ticks may not be <= 0");
        }
        if (periodTicks <= 0) {
            throw new IllegalArgumentException("Period ticks may not be <= 0");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register task while disabled");
        }
        if (!this.entity.isValid() || this.entity.isDead()) {
            if (retired != null) {
                retired.run();
            }
            return null;
        }

        EntityScheduledTask ret = new EntityScheduledTask(plugin, task, retired, true);
        BukkitTask bt = Bukkit.getScheduler().runTaskTimer(plugin, ret::tick, initialDelayTicks, periodTicks);
        ret.setBukkitTask(bt);
        return ret;
    }

    private final class EntityScheduledTask implements ScheduledTask {
        private final Plugin plugin;
        private final Consumer<ScheduledTask> task;
        private final Runnable retired;
        private final boolean repeating;
        private volatile ExecutionState state = ExecutionState.IDLE;
        private volatile BukkitTask bukkitTask;

        private EntityScheduledTask(Plugin plugin, Consumer<ScheduledTask> task, Runnable retired, boolean repeating) {
            this.plugin = plugin;
            this.task = task;
            this.retired = retired;
            this.repeating = repeating;
        }

        private void setBukkitTask(BukkitTask bukkitTask) {
            this.bukkitTask = bukkitTask;
        }

        private void tick() {
            if (this.state == ExecutionState.CANCELLED || this.state == ExecutionState.CANCELLED_RUNNING) {
                return;
            }
            if (!this.plugin.isEnabled()) {
                this.state = ExecutionState.CANCELLED;
                cancelInternal();
                return;
            }
            if (!FoliaEntityScheduler.this.entity.isValid() || FoliaEntityScheduler.this.entity.isDead()) {
                try {
                    if (this.retired != null) {
                        this.retired.run();
                    }
                } finally {
                    this.state = ExecutionState.CANCELLED;
                    cancelInternal();
                }
                return;
            }

            this.state = ExecutionState.RUNNING;
            try {
                this.task.accept(this);
            } catch (Throwable throwable) {
                this.plugin.getLogger().log(Level.WARNING, "Entity task for " + this.plugin.getDescription().getFullName() + " generated an exception", throwable);
            } finally {
                if (!this.repeating) {
                    this.state = ExecutionState.FINISHED;
                    cancelInternal();
                } else if (this.state == ExecutionState.RUNNING) {
                    this.state = ExecutionState.IDLE;
                }
            }
        }

        private void cancelInternal() {
            BukkitTask bt = this.bukkitTask;
            if (bt != null) {
                bt.cancel();
            }
        }

        @Override
        public @NotNull Plugin getOwningPlugin() {
            return this.plugin;
        }

        @Override
        public boolean isRepeatingTask() {
            return this.repeating;
        }

        @Override
        public @NotNull CancelledState cancel() {
            if (this.state == ExecutionState.CANCELLED || this.state == ExecutionState.CANCELLED_RUNNING) {
                return CancelledState.CANCELLED_ALREADY;
            }
            if (this.state == ExecutionState.FINISHED) {
                return CancelledState.ALREADY_EXECUTED;
            }
            if (this.state == ExecutionState.RUNNING && !this.repeating) {
                return CancelledState.RUNNING;
            }
            if (this.state == ExecutionState.RUNNING) {
                this.state = ExecutionState.CANCELLED_RUNNING;
                return CancelledState.NEXT_RUNS_CANCELLED;
            }
            this.state = ExecutionState.CANCELLED;
            cancelInternal();
            return CancelledState.CANCELLED_BY_CALLER;
        }

        @Override
        public @NotNull ExecutionState getExecutionState() {
            return this.state;
        }
    }
}
