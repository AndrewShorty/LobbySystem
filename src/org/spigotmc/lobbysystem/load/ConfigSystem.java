package org.spigotmc.lobbysystem.load;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class ConfigSystem {

    public static void init(File file) {
        if(file.exists()) {
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            Settings.language = cfg.getString("settings.language");
        } else {
            Settings.language = "english";
        }
    }

    public static class Settings {
        public static String language;
    }
}