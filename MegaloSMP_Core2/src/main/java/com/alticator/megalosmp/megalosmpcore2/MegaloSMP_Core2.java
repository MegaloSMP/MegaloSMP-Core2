package com.alticator.megalosmp.megalosmpcore2;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class MegaloSMP_Core2 extends JavaPlugin {

    private static MegaloSMP_Core2 instance;
    @Override
    public void onEnable() {
        // Setup command executors and tab completers
        getServer().getPluginCommand("balance").setExecutor(new Balance());
        getServer().getPluginCommand("exchange").setExecutor(new Exchange());
        getServer().getPluginCommand("exchangeprice").setExecutor(new Exchange());
        getServer().getPluginCommand("predefexchange").setExecutor(new PredefExchange());
        getServer().getPluginCommand("predefexchangeprice").setExecutor(new PredefExchange());
        getServer().getPluginCommand("foodutilityexchange").setExecutor(new FoodUtilityExchange());
        getServer().getPluginCommand("foodutilityexchangeprice").setExecutor(new FoodUtilityExchange());

        getServer().getPluginCommand("exchange").setTabCompleter(new ExchangeTabCompleter());
        getServer().getPluginCommand("exchangeprice").setTabCompleter(new ExchangeTabCompleter());
        getServer().getPluginCommand("predefexchange").setTabCompleter(new PredefExchangeTabCompleter());
        getServer().getPluginCommand("foodutilityexchange").setTabCompleter(new FoodUtilityExchangeTabCompleter());
        getServer().getPluginCommand("predefexchangeprice").setTabCompleter(new PredefExchangeTabCompleter());
        getServer().getPluginCommand("foodutilityexchangeprice").setTabCompleter(new FoodUtilityExchangeTabCompleter());

        // Setup event listener
        getServer().getPluginManager().registerEvents(new EventListener(), this);


        // Logs to planks recipe

        ItemStack oakLogs = new ItemStack(Material.OAK_LOG, 2);
        ItemStack spruceLogs = new ItemStack(Material.SPRUCE_LOG, 2);
        ItemStack birchLogs = new ItemStack(Material.BIRCH_LOG, 2);

        NamespacedKey oakKey = new NamespacedKey(this, "oak_logs");
        NamespacedKey spruceKey = new NamespacedKey(this, "spruce_logs");
        NamespacedKey birchKey = new NamespacedKey(this, "birch_logs");

        ShapedRecipe oakRecipe = new ShapedRecipe(oakKey, oakLogs);
        ShapedRecipe spruceRecipe = new ShapedRecipe(spruceKey, spruceLogs);
        ShapedRecipe birchRecipe = new ShapedRecipe(birchKey, birchLogs);

        oakRecipe.shape("AAA", "AAA", "A A");
        spruceRecipe.shape("AAA", "AAA", "A A");
        birchRecipe.shape("AAA", "AAA", "A A");

        oakRecipe.setIngredient('A', Material.OAK_PLANKS);
        spruceRecipe.setIngredient('A', Material.SPRUCE_PLANKS);
        birchRecipe.setIngredient('A', Material.BIRCH_PLANKS);

        getServer().addRecipe(oakRecipe);
        getServer().addRecipe(spruceRecipe);
        getServer().addRecipe(birchRecipe);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MegaloSMP_Core2 getInstance() {
        return instance;
    }

}
