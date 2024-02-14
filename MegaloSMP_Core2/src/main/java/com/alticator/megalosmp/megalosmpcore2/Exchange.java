package com.alticator.megalosmp.megalosmpcore2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Exchange implements CommandExecutor {

    Material argMaterial;
    int argMaterialPrice;

    private static String[] exchangeItems;
    private static int[] exchangePrices;
    private static Material[] exchangeMaterials;


    /////////////////////////

    public Exchange(String[] items, int[] prices, Material[] materials) {
        exchangeItems = items;
        exchangePrices = prices;
        exchangeMaterials = materials;
    }

    public void parseMaterial(String arg) {
        Material pay = null;
        int price = 0;
        Bukkit.getConsoleSender().sendMessage("Listing items from Exchange: ");
        for (int i = 0; i < exchangeItems.length; i++) {
            Bukkit.getConsoleSender().sendMessage(exchangeItems[i]);
            if (exchangeItems[i] != null && exchangeItems[i].replace("_", "").replace(" ", "").equalsIgnoreCase(arg.replace("_", "").replace(" ", ""))) {
                pay = exchangeMaterials[i];
                price = exchangePrices[i];
            }
        }
        argMaterial = pay;
        argMaterialPrice = price;

    }

    private boolean buy(Player player, Material item, int quantity, int price) {
        int playerGoldBalance = Util.countPlayerGold(player);
        int transactionGold = Math.round(price * quantity);
        if (playerGoldBalance >= transactionGold) {
            Util.takeGold(player, transactionGold);
            Util.give(player, item, quantity);
            player.sendMessage(ChatColor.GREEN + "Bought " + quantity + " " + item.toString() + "(s) with " + transactionGold + " Gold.");
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You don't have enough gold. (You have: " + playerGoldBalance + ", Required: " + transactionGold + ")");
            return false;
        }
    }

    private boolean sell(Player player, Material item, int quantity, int price) {
        int playerItemQuantity = Util.countPlayerMaterial(player, item);
        if (playerItemQuantity >= quantity) {
            int goldValue = quantity * price;
            Util.take(player, item, quantity);
            Util.takeGold(player, -goldValue);
            player.sendMessage(ChatColor.GREEN + "Sold " + quantity + " " + item.toString() + "(s) for " + goldValue + " Gold.");
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You don't have enough " + item.toString() + ". (You have: " + playerItemQuantity + ", Required: " + quantity + ")");
            return false;
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("exchange") && sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("buy")) {
                    String quantity = "1";
                    int quantityInt;
                    if (args.length >= 3) {
                        quantity = args[2];
                    }
                    try {
                        quantityInt = Integer.parseInt(quantity);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Invalid Quantity (NumberFormatException)");
                        return false;
                    }
                    parseMaterial(args[1]);
                    if (argMaterial != null) {
                        buy(player, argMaterial, quantityInt, argMaterialPrice);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid Material");
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("sell")) {
                    String quantity = "1";
                    int quantityInt;
                    if (args.length >= 3) {
                        quantity = args[2];
                    }
                    try {
                        quantityInt = Integer.parseInt(quantity);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Invalid Quantity (NumberFormatException)");
                        return false;
                    }
                    parseMaterial(args[1]);
                    if (argMaterial != null) {
                        sell(player, argMaterial, quantityInt, argMaterialPrice);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid Material");
                        return false;
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Not enough arguments.");
            }
        } else if (command.getName().equalsIgnoreCase("exchangeprice")) {
            if (args.length >= 1) {
                parseMaterial(args[0]);
                if (argMaterial != null) {
                    int quantity = 1;
                    if (args.length >= 2) {
                        try {
                            quantity = Integer.parseInt(args[1]);
                        } catch (NumberFormatException e) {
                            sender.sendMessage(ChatColor.RED + "Invalid Quantity (NumberFormatException)");
                            return false;
                        }
                    }
                    int price = argMaterialPrice * quantity;
                    sender.sendMessage(ChatColor.GREEN + "Price for " + quantity + " " + argMaterial.toString() + ": " + ChatColor.YELLOW + price + " Gold.");
                    return true;

                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid Material.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Incorrect amount of arguments.");
            }
        } else {
            sender.sendMessage("Only players can use this command: " + command.getName());
        }
        return false;
    }
}