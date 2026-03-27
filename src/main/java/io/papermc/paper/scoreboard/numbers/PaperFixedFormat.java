package io.papermc.paper.scoreboard.numbers;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public class PaperFixedFormat implements FixedFormat {
    private final Component component;

    public PaperFixedFormat(@NotNull Component component) {
        this.component = component;
    }

    @Override
    public @NotNull Component component() {
        return component;
    }

    @Override
    public @NotNull Component asComponent() {
        return component;
    }
}
