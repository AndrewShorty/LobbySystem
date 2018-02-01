package org.spigotmc.lobbysystem.load;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import java.io.File;

public class LanguageSystem {

    public static void init(Plugin plugin, String lang) {
        File file = new File("plugins/LobbySystem/Language", "lang_" + lang + ".yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        Message.prefix = cfg.getString("message.prefix");
        Message.noperms = cfg.getString("message.noperms");
        Message.notonline = cfg.getString("message.notonline");
    }

    public static class Message {
        public static String prefix;
        public static String noperms;
        public static String notonline;
    }
}