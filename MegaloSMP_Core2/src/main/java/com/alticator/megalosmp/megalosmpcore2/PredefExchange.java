package com.alticator.megalosmp.megalosmpcore2;

import org.bukkit.command.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PredefExchange implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player && args.length == 1) {
            Player player = (Player) sender;
            ItemStack item;
            int price;

            switch (args[0].toLowerCase().replace("_", "")) {
                case "propick":
                    item = new ItemStack(Material.IRON_PICKAXE, 1);
                    item.addEnchantment(Enchantment.DIG_SPEED, 5);
                    item.addEnchantment(Enchantment.DURABILITY, 3);;
                    Util.setItemStackName(item, "ProPick");
                    price = 64;
                    break;
                case "ultimatepick":
                    item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                    item.addEnchantment(Enchantment.DIG_SPEED, 5);
                    item.addEnchantment(Enchantment.DURABILITY, 3);
                    Util.setItemStackName(item, "UltimatePick");
                    price = 128;
                    break;
                case "destroyerpick":
                    item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                    item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 20);
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    Util.setItemStackName(item, "DestroyerPick");
                    price = 1024;
                    break;
                case "ultimatesword":
                    item = new ItemStack(Material.NETHERITE_SWORD, 1);
                    item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                    Util.setItemStackName(item, "UltimateSword");
                    price = 1024;
                    break;
                case "knockbackstick":
                    item = new ItemStack(Material.STICK, 1);
                    item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 50);
                    Util.setItemStackName(item, "KnockbackStick");
                    price = 512;
                    break;
                case "ultimateaxe":
                    item = new ItemStack(Material.NETHERITE_AXE, 1);
                    item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    Util.setItemStackName(item, "UltimateAxe");
                    price = 128;
                    break;
                case "ultimateshovel":
                    item = new ItemStack(Material.NETHERITE_SHOVEL, 1);
                    item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    Util.setItemStackName(item, "UltimateShovel");
                    price = 128;
                    break;
                case "ultimatehoe":
                    item = new ItemStack(Material.NETHERITE_HOE, 1);
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    Util.setItemStackName(item, "UltimateHoe");
                    price = 128;
                    break;
                case "maximumsword":
                    item = new ItemStack(Material.NETHERITE_SWORD, 1);
                    item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 255);
                    item.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 10);
                    Util.setItemStackName(item, "MaximumSword");
                    price = 2048;
                    break;
                case "timemachine":
                    item = new ItemStack(Material.CLOCK);
                    Util.setItemStackName(item, "Time Machine");
                    player.getWorld().setTime(1000);
                    price = 64;
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Invalid pack name.");
                    return false;

                // When updating, also add the new items to the tab completer
            }

            if (command.getName().equalsIgnoreCase("predefexchange")) {
                if (Util.countPlayerGold(player) >= price) {
                    Util.takeGold(player, price);
                    player.getInventory().addItem(item);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Not enough gold. (You have: " + Util.countPlayerGold(player) + ", needed: " + price + ")");
                }
            } else if (command.getName().equalsIgnoreCase("predefexchangeprice")) {
                player.sendMessage(ChatColor.GREEN + "Price for the [" + args[0] + "] pack: " + ChatColor.YELLOW + "" + price + " Gold");
                return true;
            }

        } else {
            sender.sendMessage("Cannot execute command. Make sure you have exactly one argument and you are running the command as a player (not from the console).");
        }
        return false;
    }
}
