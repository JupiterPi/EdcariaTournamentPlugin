package de.jupiterpi.mc.edcaria;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TournamentCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return false;
        String cmd = args[0];

        try {
            if (cmd.equals("create")) {
                if (EdcariaTournamentPlugin.tournament == null) {
                    EdcariaTournamentPlugin.tournament = Tournament.createTournament();
                } else {
                    throw new CommandFailedException("Could not create Tournament: There is an active Tournament already!");
                }
            }
        } catch (CommandFailedException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }
}
