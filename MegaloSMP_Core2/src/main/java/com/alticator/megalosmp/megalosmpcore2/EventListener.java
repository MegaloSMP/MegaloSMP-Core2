package com.alticator.megalosmp.megalosmpcore2;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.DARK_PURPLE + "Welcome! This server uses MegaloSMP-Core 2.");
        player.sendMessage(ChatColor.BLUE + "Exchange items using gold as currency using /x (main exchange), /s (PredefExchange: swords and tools) and /z (FoodUtilityExchange). The base unit is a gold nugget. A gold ingot is worth 9 and a gold block is worth 81. You must have all the required gold in your inventory to perform exchanges. You can see the total value of the gold in your inventory using /balance or /bal");
    }
}
