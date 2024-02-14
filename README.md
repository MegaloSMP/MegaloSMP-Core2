# MegaloSMP-Core 2
### Sequel to the original MegaloSMP-Core
## Installation
1. Download the .jar file from the *Releases* menu
2. Move the file to your server's *plugins* folder
3. Create and configure the *megalosmp.properties* file in the same *plugins* folder
## Properties File
In the properties file you can define the items for the Exchange (the main exchange only). Here is the configuration file to get the same items as in the original MegaloSMP-Core:
```
app.exchangeservice.items     = acacia_log,birch_log,mangrove_log,jungle_log,dark_oak_log,oak_log,spruce_log,ender_pearl,diamond,iron_ingot,asphalt,cobblestone,stone,smooth_stone,stone_bricks,white_concrete,light_blue_concrete,cyan_concrete,yellow_concrete,red_concrete,netherite_ingot,coal,sea_lantern,chest,ender_chest,glass,white_stained_glass,black_stained_glass,purple_bed,gray_concrete,villager_spawn_egg,emerald_block,wool,stone_brick_wall,end_rod,andesite,diorite,beacon
app.exchangeservice.prices    = 1,1,1,1,1,1,1,2,36,16,1,2,3,2,3,3,3,3,3,3,3,128,3,3,1,128,2,2,2,1,8,1024,32,2,512,1,1,512
app.exchangeservice.materials = ACACIA_LOG,BIRCH_LOG,MANGROVE_LOG,JUNGLE_LOG,DARK_OAK_LOG,OAK_LOG,SPRUCE_LOG,ENDER_PEARL,DIAMOND,IRON_INGOT,CYAN_TERRACOTTA,COBBLESTONE,STONE,SMOOTH_STONE,STONE_BRICKS,WHITE_CONCRETE,LIGHT_BLUE_CONCRETE,CYAN_CONCRETE,YELLOW_CONCRETE,RED_CONCRETE,NETHERITE_INGOT,COAL,SEA_LANTERN,CHEST,ENDER_CHEST,GLASS,WHITE_STAINED_GLASS,BLACK_STAINED_GLASS,PURPLE_BED,GRAY_CONCRETE,VILLAGER_SPAWN_EGG,EMERALD_BLOCK,WOOL,STONE_BRICK_WALL,END_ROD,ANDESITE,DIORITE,BEACON
```
This will set acacia_logs to be prices 1 gold each and to correspong to the ACACIA_LOG material. The maximum amount of items is 100.
## Usage
### Command `/exchange` or `/x` (Exchange)
The `/x` command allows you to buy or sell items with gold used as the currency. Gold nuggets are the base unit, ingots are worth 9 and blocks are worth 81. You must have the gold in your inventory when doing exchanges.
#### Example Usage:
- `/x buy ender_pearl 2` or `/x buy enderpearl 2` - Buys 2 ender pearls. (Underscores optional)
- `/x sell oaklog` or `/x sell oak_log 1` Sells one oak log. (Underscores optional, 1 is the default quantity)
### Command `/xprice`
Check the price for an exchange item.
### Command `/s` (PredefExchange)
Buy predefined swords and tools.
### Command `/sprice`
Check the price for a sword/tool available in the PredefExchange.
#### Example Usage:
- `/s UltimateAxe` - Buys the UltimateAxe axe.
### Command `/z` (FoodUtilityExchange)
Buy predefined packs of food or utility items.
- `/z beef` - Buys the beef pack which contains 64 cooked beef for 16 Gold.
### Command `/sprice`
Check the price for a pack available in the FoodUtilityExchange.
## Licence
This project is available under the **MIT Licence**.
