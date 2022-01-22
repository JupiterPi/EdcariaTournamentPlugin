package de.jupiterpi.mc.edcaria;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TournamentCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "Only operators can use this command!");
            return true;
        }

        if (args.length < 1) return false;
        String cmd = args[0];

        try {
            if (cmd.equals("create")) {
                if (EdcariaTournamentPlugin.tournament == null) {
                    EdcariaTournamentPlugin.tournament = Tournament.createTournament();
                    sender.sendMessage("Created a new Tournament.");
                } else {
                    throw new CommandFailedException("There is an active Tournament already");
                }
                return true;
            }
            if (EdcariaTournamentPlugin.tournament == null) {
                throw new CommandFailedException("There is no active Tournament");
            }
            if (cmd.equals("start")) {
                EdcariaTournamentPlugin.tournament.start();
                sender.sendMessage("Started Tournament.");
            }
        } catch (CommandFailedException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }
}
