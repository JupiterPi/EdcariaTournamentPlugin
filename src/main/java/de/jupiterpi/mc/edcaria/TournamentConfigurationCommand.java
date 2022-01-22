package de.jupiterpi.mc.edcaria;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TournamentConfigurationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length < 1) return false;
        String attribute = args[0];
        boolean changeValue = args.length >= 2;
        String value = changeValue ? args[1] : null;

        try {
            if (EdcariaTournamentPlugin.tournament == null) {
                throw new CommandFailedException("There is no active Tournament");
            }

            String readValue = null;
            switch (attribute) {
                case "spawn-spread":
                    if (changeValue) {
                        try {
                            int valueInt = Integer.parseInt(value);
                            EdcariaTournamentPlugin.tournament.getConfig().spawnSpread = valueInt;
                        } catch (NumberFormatException e) {
                            throw new CommandFailedException("Not a number: " + value);
                        }
                    }
                    readValue = Integer.toString(EdcariaTournamentPlugin.tournament.getConfig().spawnSpread);
                    break;
                case "spawn-height":
                    if (changeValue) {
                        try {
                            int valueInt = Integer.parseInt(value);
                            EdcariaTournamentPlugin.tournament.getConfig().spawnHeight = valueInt;
                        } catch (NumberFormatException e) {
                            throw new CommandFailedException("Not a number: " + value);
                        }
                    }
                    readValue = Integer.toString(EdcariaTournamentPlugin.tournament.getConfig().spawnHeight);
                    break;
                case "invulnerability-duration":
                    if (changeValue) {
                        try {
                            int valueInt = Integer.parseInt(value);
                            EdcariaTournamentPlugin.tournament.getConfig().spawnInvulnerabilityDuration = valueInt;
                        } catch (NumberFormatException e) {
                            throw new CommandFailedException("Not a number: " + value);
                        }
                    }
                    readValue = EdcariaTournamentPlugin.tournament.getConfig().spawnInvulnerabilityDuration + " s";
                    break;
            }
            if (readValue == null) {
                throw new CommandFailedException("Could not find attribute: " + attribute);
            } else {
                sender.sendMessage(attribute + ": " + readValue);
            }
        } catch (CommandFailedException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
        }

        return true;
    }
}
