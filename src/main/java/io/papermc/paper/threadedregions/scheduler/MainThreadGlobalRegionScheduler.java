package io.papermc.paper.threadedregions.scheduler;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

/**
 * Paper {@link GlobalRegionScheduler} for single-threaded servers: runs tasks on the main server thread.
 */
public final class MainThreadGlobalRegionScheduler implements GlobalRegionScheduler {

    private final Set<MainThreadScheduledTask> tasks = ConcurrentHashMap.newKeySet();

    @Override
    public void execute(@NotNull Plugin plugin, @NotNull Runnable run) {
        Objects.requireNonNull(plugin, "Plugin may not be null");
        Objects.requireNonNull(run, "Runnable may not be null");
        Bukkit.getScheduler().runTask(plugin, run);
    }

    @Override
    public @NotNull ScheduledTask run(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task) {
        return runDelayed(plugin, task, 1L);
    }

    @Override
    public @NotNull ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task, long delayTicks) {
        Objects.requireNonNull(plugin, "Plugin may not be null");
        Objects.requireNonNull(task, "Task may not be null");
        if (delayTicks <= 0) {
            throw new IllegalArgumentException("Delay ticks may not be <= 0");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register task while disabled");
        }
        MainThreadScheduledTask st = new MainThreadScheduledTask(this, plugin, task, false, 0L);
        BukkitTask bt = Bukkit.getScheduler().runTaskLater(plugin, st::runOnce, delayTicks);
        st.attach(bt);
        tasks.add(st);
        return st;
    }

    @Override
    public @NotNull ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                                   long initialDelayTicks, long periodTicks) {
        Objects.requireNonNull(plugin, "Plugin may not be null");
        Objects.requireNonNull(task, "Task may not be null");
        if (initialDelayTicks <= 0 || periodTicks <= 0) {
            throw new IllegalArgumentException("Initial delay and period must be > 0");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalPluginAccessException("Plugin attempted to register task while disabled");
        }
        MainThreadScheduledTask st = new MainThreadScheduledTask(this, plugin, task, true, periodTicks);
        BukkitTask bt = Bukkit.getScheduler().runTaskTimer(plugin, st::runOnce, initialDelayTicks, periodTicks);
        st.attach(bt);
        tasks.add(st);
        return st;
    }

    @Override
    public void cancelTasks(@NotNull Plugin plugin) {
        Objects.requireNonNull(plugin, "Plugin may not be null");
        for (MainThreadScheduledTask t : tasks) {
            if (t.plugin == plugin) {
                t.cancelInternal();
            }
        }
    }

    void removeTask(MainThreadScheduledTask t) {
        tasks.remove(t);
    }

    private static final class MainThreadScheduledTask implements ScheduledTask {

        private final MainThreadGlobalRegionScheduler scheduler;
        private final Plugin plugin;
        private final Consumer<ScheduledTask> consumer;
        private final boolean repeating;
        @SuppressWarnings("unused")
        private final long periodTicks;

        private volatile BukkitTask bukkitTask;
        private volatile ExecutionState state = ExecutionState.IDLE;

        MainThreadScheduledTask(MainThreadGlobalRegionScheduler scheduler, Plugin plugin,
                                Consumer<ScheduledTask> consumer, boolean repeating, long periodTicks) {
            this.scheduler = scheduler;
            this.plugin = plugin;
            this.consumer = consumer;
            this.repeating = repeating;
            this.periodTicks = periodTicks;
        }

        void attach(BukkitTask task) {
            this.bukkitTask = task;
        }

        void runOnce() {
            if (state == ExecutionState.CANCELLED) {
                return;
            }
            state = ExecutionState.RUNNING;
            try {
                consumer.accept(this);
            } finally {
                if (!repeating) {
                    state = ExecutionState.FINISHED;
                    scheduler.removeTask(this);
                } else if (state == ExecutionState.RUNNING) {
                    state = ExecutionState.IDLE;
                }
            }
        }

        void cancelInternal() {
            if (bukkitTask != null) {
                bukkitTask.cancel();
            }
            state = repeating ? ExecutionState.CANCELLED_RUNNING : ExecutionState.CANCELLED;
            scheduler.removeTask(this);
        }

        @Override
        public @NotNull Plugin getOwningPlugin() {
            return plugin;
        }

        @Override
        public boolean isRepeatingTask() {
            return repeating;
        }

        @Override
        public @NotNull CancelledState cancel() {
            if (bukkitTask == null || bukkitTask.isCancelled()) {
                return state == ExecutionState.CANCELLED || state == ExecutionState.CANCELLED_RUNNING
                    ? CancelledState.CANCELLED_ALREADY
                    : CancelledState.ALREADY_EXECUTED;
            }
            bukkitTask.cancel();
            boolean wasRepeating = repeating;
            state = wasRepeating ? ExecutionState.CANCELLED_RUNNING : ExecutionState.CANCELLED;
            scheduler.removeTask(this);
            return wasRepeating ? CancelledState.NEXT_RUNS_CANCELLED : CancelledState.CANCELLED_BY_CALLER;
        }

        @Override
        public @NotNull ExecutionState getExecutionState() {
            return state;
        }
    }
}
