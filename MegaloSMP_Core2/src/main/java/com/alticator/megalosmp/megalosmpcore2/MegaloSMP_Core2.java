package com.alticator.megalosmp.megalosmpcore2;

import com.sun.java.swing.plaf.windows.TMSchema;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public final class MegaloSMP_Core2 extends JavaPlugin {

    private static MegaloSMP_Core2 instance;
    private static String[] exchangeServiceItems = new String[100];
    private static int[] exchangeServicePrices = new int[100];
    private static Material[] exchangeServiceMaterials = new Material[100];
    @Override
    public void onEnable() {
        // Read properties
        try (InputStream input = Files.newInputStream(Paths.get("plugins/megalosmp.properties"))) {
            Properties prop = new Properties();
            prop.load(input);
            exchangeServiceItems = prop.getProperty("app.exchangeservice.items").split(",");
            String[] exchangeServicePricesString = prop.getProperty("app.exchangeservice.prices").split(",");
            for (int i = 0; i < exchangeServicePricesString.length; i++) {
                exchangeServicePrices[i] = Integer.parseInt(exchangeServicePricesString[i]);
            }
            String[] exchangeServiceMaterialsString = prop.getProperty("app.exchangeservice.materials").split(",");
            for (int i = 0; i < exchangeServiceMaterialsString.length; i++) {
                exchangeServiceMaterials[i] = Material.getMaterial(exchangeServiceMaterialsString[i]);
            }

            Bukkit.getConsoleSender().sendMessage("Found following items: ");
            for (String exchangeServiceItem : exchangeServiceItems) {
                Bukkit.getConsoleSender().sendMessage(exchangeServiceItem);
            }

        } catch (IOException exception) {
            Bukkit.getConsoleSender().sendMessage("Couldn't load MegaloSMP-Core 2 configuration file");
            getServer().getPluginManager().disablePlugin(this);
        }

        // Setup command executors and tab completers
        getServer().getPluginCommand("balance").setExecutor(new Balance());
        getServer().getPluginCommand("exchange").setExecutor(new Exchange(exchangeServiceItems, exchangeServicePrices, exchangeServiceMaterials));
        getServer().getPluginCommand("exchangeprice").setExecutor(new Exchange(exchangeServiceItems, exchangeServicePrices, exchangeServiceMaterials));
        getServer().getPluginCommand("predefexchange").setExecutor(new PredefExchange());
        getServer().getPluginCommand("predefexchangeprice").setExecutor(new PredefExchange());
        getServer().getPluginCommand("foodutilityexchange").setExecutor(new FoodUtilityExchange());
        getServer().getPluginCommand("foodutilityexchangeprice").setExecutor(new FoodUtilityExchange());

        getServer().getPluginCommand("exchange").setTabCompleter(new ExchangeTabCompleter(exchangeServiceItems));
        getServer().getPluginCommand("exchangeprice").setTabCompleter(new ExchangeTabCompleter(exchangeServiceItems));
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
