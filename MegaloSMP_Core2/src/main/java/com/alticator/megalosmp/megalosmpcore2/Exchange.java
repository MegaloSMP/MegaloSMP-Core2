package com.alticator.megalosmp.megalosmpcore2;

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

    // Prices in gold ingots
    // Note that some prices hardcoded in the parseMaterial() method.
    private final int logPrice = 1;
    private final int enderPearlPrice = 2;
    private final int diamondPrice = 24;

    private final int concretePrice = 3;

    /////////////////////////

    public void parseMaterial(String arg) {
        Material pay;
        int price = 0;
        switch (arg.toLowerCase().replace("_", "")) {
            case "acacialog":
                pay = Material.ACACIA_LOG;
                price = logPrice;
                break;
            case "birchlog":
                pay = Material.BIRCH_LOG;
                price = logPrice;
                break;
            case "mangrovelog":
                pay = Material.MANGROVE_LOG;
                price = logPrice;
                break;
            case "junglelog":
                pay = Material.JUNGLE_LOG;
                price = logPrice;
                break;
            case "darkoaklog":
                pay = Material.DARK_OAK_LOG;
                price = logPrice;
                break;
            case "oaklog":
                pay = Material.OAK_LOG;
                price = logPrice;
                break;
            case "sprucelog":
                pay = Material.SPRUCE_LOG;
                price = logPrice;
                break;
            case "enderpearl":
                pay = Material.ENDER_PEARL;
                price = enderPearlPrice;
                break;
            case "diamond":
                pay = Material.DIAMOND;
                price = diamondPrice;
                break;
            case "iron":
            case "ironingot":
                pay = Material.IRON_INGOT;
                price = 16;
                break;
            case "cobblestone":
                pay = Material.COBBLESTONE;
                price = 1;
                break;
            case "stone":
                pay = Material.STONE;
                price = 2;
                break;
            case "smoothstone":
                pay = Material.SMOOTH_STONE;
                price = 3;
                break;
            case "stonebricks":
                pay = Material.STONE_BRICKS;
                price = 2;
                break;
            case "whiteconcrete":
                pay = Material.WHITE_CONCRETE;
                price = concretePrice;
                break;
            case "lightblueconcrete":
                pay = Material.LIGHT_BLUE_CONCRETE;
                price = concretePrice;
                break;
            case "cyanconcrete":
                pay = Material.CYAN_CONCRETE;
                price = concretePrice;
                break;
            case "yellowconcrete":
                pay = Material.YELLOW_CONCRETE;
                price = concretePrice;
                break;
            case "redconcrete":
                pay = Material.RED_CONCRETE;
                price = concretePrice;
                break;
            case "grayconcrete":
                pay = Material.GRAY_CONCRETE;
                price = concretePrice;
                break;
            case "asphalt":
            case "cyanterracotta":
                pay = Material.CYAN_TERRACOTTA;
                price = concretePrice;
                break;
            case "netheriteingot":
                pay = Material.NETHERITE_INGOT;
                price = 36;
                break;
            case "coal":
                pay = Material.COAL;
                price = 3;
                break;
            case "sealantern":
                pay = Material.SEA_LANTERN;
                price = 3;
                break;
            case "chest":
                pay = Material.CHEST;
                price = 1;
                break;
            case "enderchest":
                pay = Material.ENDER_CHEST;
                price = 128;
                break;
            case "glass":
                pay = Material.GLASS;
                price = 2;
                break;
            case "whitestainedglass":
                pay = Material.WHITE_STAINED_GLASS;
                price = 2;
                break;
            case "blackstainedglass":
                pay = Material.BLACK_STAINED_GLASS;
                price = 2;
                break;
            case "wool":
                pay = Material.WHITE_WOOL;
                price = 1;
                break;
            case "bed":
                pay = Material.PURPLE_BED;
                price = 8;
                break;
            case "villagerspawnegg":
                pay = Material.VILLAGER_SPAWN_EGG;
                price = 1024;
                break;
            case "emeraldblock":
                pay = Material.EMERALD_BLOCK;
                price = 32;
                break;
            case "stonebrickwall":
                pay = Material.STONE_BRICK_WALL;
                price = 2;
                break;
            case "endrod":
                pay = Material.END_ROD;
                price = 512;
                break;
            case "andesite":
                pay = Material.ANDESITE;
                price = 1;
                break;
            case "diorite":
                pay = Material.DIORITE;
                price = 1;
                break;
            case "beacon":
                pay = Material.BEACON;
                price = 512;
                break;
            default:
                pay = null;

                // When updating, also add new items to ExchangeTabCompleter
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