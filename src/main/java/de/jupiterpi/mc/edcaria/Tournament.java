package de.jupiterpi.mc.edcaria;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tournament {
    private List<Player> players;
    private TournamentConfiguration config = new TournamentConfiguration();

    private static final boolean bypassPlayerCheck = true;
    public static Tournament createTournament() throws CommandFailedException {
        Tournament tournament = new Tournament();
        tournament.players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
        if (tournament.players.size() != 4 && !bypassPlayerCheck) {
            throw new CommandFailedException("Not the right amount of players present (" + tournament.players.size() + ", needed 4). ");
        }
        return tournament;
    }

    public TournamentConfiguration getConfig() {
        return config;
    }

    public void start() {
        World world = Bukkit.getWorld("world");
        int spread = config.spawnSpread;
        int height = config.spawnHeight;
        List<Location> playerStartPoints = Arrays.asList(
                new Location(world, -spread, height,  spread), // A
                new Location(world,  spread, height,  spread), // B
                new Location(world,  spread, height, -spread), // C
                new Location(world, -spread, height, -spread)  // D
        );
        Collections.shuffle(players);
        for (int i = 0; i < players.size() && i < playerStartPoints.size(); i++) {
            Player player = players.get(i);
            player.setGameMode(GameMode.SURVIVAL);
            player.setInvulnerable(true);
            player.sendMessage(ChatColor.GRAY + "[Tournament] " + ChatColor.WHITE + "You will be invulnerable for " + config.spawnInvulnerabilityDuration + " seconds. ");
            player.teleport(playerStartPoints.get(i));
        }
        Bukkit.getScheduler().runTaskLater(EdcariaTournamentPlugin.plugin, () -> {
            for (Player player : EdcariaTournamentPlugin.tournament.players) {
                player.sendMessage(ChatColor.GRAY + "[Tournament] " + ChatColor.WHITE + "Your invulnerability has run out. ");
                player.setInvulnerable(false);
                player.setBedSpawnLocation(player.getLocation(), true);
            }
        }, config.spawnInvulnerabilityDuration * 20);
    }
}
