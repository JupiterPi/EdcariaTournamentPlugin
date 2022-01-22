package de.jupiterpi.mc.edcaria;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class EdcariaTournamentPlugin extends JavaPlugin {
    public static JavaPlugin plugin = null;
    public static Tournament tournament = null;

    @Override
    public void onEnable() {
        plugin = this;

        getCommand("tournament").setExecutor(new TournamentCommand());
        getCommand("tournament-config").setExecutor(new TournamentConfigurationCommand());

        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[EdcariaTournamentPlugin] Tournament plugin enabled");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[EdcariaTournamentPlugin] Tournament plugin disabled");
        super.onDisable();
    }
}
