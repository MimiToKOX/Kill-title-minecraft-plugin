package com.top1.custom_kill_title.events;

import com.top1.custom_kill_title.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillEvent implements Listener {

    private final Main plugin;

    public KillEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        if (killer != null) {
            String killTitle = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kill_title"));
            String killSubtitle = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kill_subtitle"));
            String deadTitle = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("dead_title"));
            String deadSubtitle = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("dead_subtitle"));

            killSubtitle = PlaceholderAPI.setPlaceholders(victim, killSubtitle.replace("{player}", victim.getName()));
            deadSubtitle = PlaceholderAPI.setPlaceholders(killer, deadSubtitle.replace("{killer}", killer.getName()));

            killer.sendTitle(killTitle, killSubtitle, 10, 70, 20);
            victim.sendTitle(deadTitle, deadSubtitle, 10, 70, 20);
        }
    }
}
