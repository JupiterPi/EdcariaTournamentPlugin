package de.jupiterpi.mc.edcaria;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private List<Player> players;

    public static Tournament createTournament() throws CommandFailedException {
        Tournament tournament = new Tournament();
        tournament.players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
        if (tournament.players.size() != 4) {
            throw new CommandFailedException("Couldn't create Tournament: Not the right amount of players (" + tournament.players.size() + ", needed 4). ");
        }
        return tournament;
    }
}
