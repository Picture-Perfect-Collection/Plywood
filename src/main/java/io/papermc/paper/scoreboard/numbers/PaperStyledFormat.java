package io.papermc.paper.scoreboard.numbers;

import net.kyori.adventure.text.format.Style;
import org.jetbrains.annotations.NotNull;

public class PaperStyledFormat implements StyledFormat {
    private final Style style;

    public PaperStyledFormat(@NotNull Style style) {
        this.style = style;
    }

    @Override
    public @NotNull Style style() {
        return style;
    }

    @Override
    public void styleApply(final Style.Builder builder) {
        builder.merge(this.style);
    }
}
