package com.alticator.megalosmp.megalosmpcore2;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class FoodUtilityExchange implements CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player && args.length == 1) {
            Player player = (Player) sender;
            ItemStack item;
            int price;

            int dyePrice = 16;

            switch (args[0].toLowerCase().replace("_", "")) {
                case "beef":
                    item = new ItemStack(Material.COOKED_BEEF, 64);
                    price = 16;
                    break;
                case "glow":
                    item = new ItemStack(Material.GLOW_INK_SAC, 64);
                    price = dyePrice;
                    break;
                case "whitedye":
                    item = new ItemStack(Material.WHITE_DYE, 64);
                    price = dyePrice;
                    break;
                case "greendye":
                    item = new ItemStack(Material.GREEN_DYE, 64);
                    price = dyePrice;
                    break;
                case "reddye":
                    item = new ItemStack(Material.RED_DYE, 64);
                    price = dyePrice;
                    break;
                case "yellowdye":
                    item = new ItemStack(Material.YELLOW_DYE, 64);
                    price = dyePrice;
                    break;
                case "cyandye":
                    item = new ItemStack(Material.CYAN_DYE, 64);
                    price = dyePrice;
                    break;
                case "magentadye":
                    item = new ItemStack(Material.MAGENTA_DYE, 64);
                    price = dyePrice;
                    break;
                case "scaffolding":
                    item = new ItemStack(Material.SCAFFOLDING, 64);
                    price = 16;
                    break;
                case "torch":
                    item = new ItemStack(Material.TORCH, 64);
                    price = 16;
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Invalid package name.");
                    return false;

                // When updating, also add the new items to the tab completer
            }

            if (command.getName().equalsIgnoreCase("foodutilityexchange")) {
                if (Util.countPlayerGold(player) >= price) {
                    Util.takeGold(player, price);
                    player.getInventory().addItem(item);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Not enough gold. (You have: " + Util.countPlayerGold(player) + ", needed: " + price + ")");
                }
            } else if (command.getName().equalsIgnoreCase("foodutilityexchangeprice")) {
                player.sendMessage(ChatColor.GREEN + "Price for the [" + args[0] + "] pack: " + ChatColor.YELLOW + "" + price + " Gold");
                return true;
            }

        } else {
            sender.sendMessage("Cannot execute command. Make sure you have exactly one argument and you are running the command as a player (not from the console).");
        }
        return false;
    }
}
