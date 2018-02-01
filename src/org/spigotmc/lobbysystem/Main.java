package org.spigotmc.lobbysystem;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.spigotmc.lobbysystem.api.FileSystem;
import org.spigotmc.lobbysystem.load.LanguageSystem;

import java.io.File;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        File file = new File("plugins/LobbySystem", "config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if(!file.exists()) {
            getLogger().info("config.yml not exist. Return to use default settings and import the Config from internal Database. Please do not restart or reload the Server in the next 30 Seconds!");
            FileSystem.insertData("files/config.yml", "plugins/LobbySystem/config.yml");
        }

        getLogger().info("[]⚌⚌⚌⚌⚌⚌⚌⚌[LobbySystem]⚌⚌⚌⚌⚌⚌⚌⚌[]");
        getLogger().info("");
        getLogger().info("Author: " + getDescription().getAuthors().toString().replace("[", "").replace("]", ""));
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Website: " + getDescription().getWebsite());
        getLogger().info("Commands Loaded: " + getDescription().getCommands().size());
        getLogger().info("");
        getLogger().info("ConfigSystem will be loaded...");
        getLogger().info("LanguageSystem will be loaded...");
        LanguageSystem.init(this, cfg.getString("settings.language"));
        getLogger().info("UnknowSystem will be loaded...");
        getLogger().info("AddonSystem will be loaded...");
        getLogger().info("");
        getLogger().info("");
        getLogger().info("[]⚌⚌⚌⚌⚌⚌⚌⚌[LobbySystem]⚌⚌⚌⚌⚌⚌⚌⚌[]");
    }

    @Override
    public void onDisable() {

    }
}