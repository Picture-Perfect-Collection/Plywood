package org.purpurmc.purpur.language;

import net.kyori.adventure.translation.Translatable;
import org.jetbrains.annotations.Nullable;

public abstract class Language {
    private static @Nullable Language language;

    @Nullable
    public static Language getLanguage() {
        return language;
    }

    public static void setLanguage(Language language) {
        if (Language.language != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton Language");
        }
        Language.language = language;
    }

    abstract public boolean has(String key);

    public boolean has(Translatable key) {
        return has(key.translationKey());
    }

    abstract public String getOrDefault(String key);

    public String getOrDefault(Translatable key) {
        return getOrDefault(key.translationKey());
    }
}
