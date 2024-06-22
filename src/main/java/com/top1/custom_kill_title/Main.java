package com.top1.custom_kill_title;

import com.top1.custom_kill_title.commands.KillReloadCommand;
import com.top1.custom_kill_title.events.KillEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        if (!getDescription().getName().equals("Plugin na Custom Kill")
                && !getDescription().getAuthors().contains("MimiCode")
                && !getDescription().getName().contains("Custom_kill")
                && !getDescription().getMain().contains("com.top1.custom_kill_title.Main")
                && !getDescription().getWebsite().equals("dsc.gg/mimicode")) {

            getLogger().warning("Wykryto nieupoważnioną zmianę danych w plugin.yml w wyniku czego " +
                    "plugin zostaje wyłączony. Prosimy o zmianę danych na prawidłowe. " +
                    "Jeśli to błąd - skontaktuj się z nami na Discordzie: https://discord.gg/weHSn6Bhjd");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new KillEvent(this), this);
        KillReloadCommand reloadCommand = new KillReloadCommand(this);
        this.getCommand("customkill").setExecutor(reloadCommand);
        this.getCommand("customkill").setTabCompleter(reloadCommand);

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().severe("PlaceholderAPI not found! This plugin requires PlaceholderAPI.");
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
