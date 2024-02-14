package com.alticator.megalosmp.megalosmpcore2;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PredefExchangeTabCompleter implements TabCompleter {
    public String[] packs = {"UltimatePick", "ProPick", "DestroyerPick", "UltimateSword", "KnockbackStick", "UltimateAxe",
            "UltimateShovel", "UltimateHoe", "MaximumSword", "TimeMachine"}; // Must match the list in the PredefExchange class
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<String>();
        if (args.length == 1) {
            for (String i : packs) {
                if (i.toLowerCase().replace("_", "").contains(args[0].toLowerCase().replace("_", ""))) {
                    suggestions.add(i);
                }
            }
        }
        return suggestions;
    }
}
