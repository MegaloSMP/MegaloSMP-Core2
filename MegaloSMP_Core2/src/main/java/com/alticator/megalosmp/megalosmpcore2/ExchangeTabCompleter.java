package com.alticator.megalosmp.megalosmpcore2;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExchangeTabCompleter implements TabCompleter {

    private static String[] options;

    public ExchangeTabCompleter(String[] items) {
        options = items;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<String>();
        if (command.getName().equalsIgnoreCase("exchange")) {
            if (args.length == 1) {
                String[] options = {"buy", "sell"};
                for (String i : options) {
                    if (i.replace("_", "").contains(args[0].toLowerCase().replace("_", ""))) {
                        suggestions.add(i);
                    }
                }
            } else if (args.length == 2) {
                for (String i : options) {
                    if (i.replace("_", "").contains(args[1].toLowerCase().replace("_", ""))) {
                        suggestions.add(i);
                    }
                }
            }
        } else if (command.getName().equalsIgnoreCase("exchangeprice")) {
            if (args.length == 1) {
                for (String i : options) {
                    if (i.replace("_", "").contains(args[0].toLowerCase().replace("_", ""))) {
                        suggestions.add(i);
                    }
                }
            }
        }

        return suggestions;
    }
}
