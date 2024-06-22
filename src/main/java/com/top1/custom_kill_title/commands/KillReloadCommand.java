package com.top1.custom_kill_title.commands;

import com.top1.custom_kill_title.Main;
import com.top1.custom_kill_title.utility.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class KillReloadCommand implements CommandExecutor, TabCompleter {

    private final Main plugin;

    public KillReloadCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("mimicode.killtitle")) {
            MessageUtil.sendError(sender, "Nie posiadasz permisji do tej komendy! &4(mimicode.killtitle)");
            return true;
        }
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            MessageUtil.sendSuccess(sender, "config pluginu CustomKillTtile został pomyślnie przeładowany!");
            return true;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            if ("reload".startsWith(args[0].toLowerCase())) {
                completions.add("reload");
            }
        }
        return completions;
    }
}
