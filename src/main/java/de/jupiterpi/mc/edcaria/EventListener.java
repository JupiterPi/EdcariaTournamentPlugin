package de.jupiterpi.mc.edcaria;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventListener implements Listener {
    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event) {
        if (EdcariaTournamentPlugin.tournament == null || EdcariaTournamentPlugin.tournament.getPhase() != Tournament.Phase.PRE) {
            return;
        }
        for (Player player : EdcariaTournamentPlugin.tournament.getPlayers()) {
            if (player == event.getPlayer()) continue;
            if (event.getTo().distance(player.getLocation()) <= 10) {
                event.setCancelled(true);
            }
        }
    }
}
