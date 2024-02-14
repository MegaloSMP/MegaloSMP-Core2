# MegaloSMP-Core 2
### Sequel to the original MegaloSMP-Core
## Installation
1. Download the .jar file from the *Releases* menu
2. Move the file to your server's *plugins* folder
3. Create and configure the *megalosmp.properties* file in the same *plugins* folder
## Properties File
In the properties file you can define the items for the Exchange (the main exchange only). The following is an example configuration:
```
app.exchangeservice.items = acacia_log,asphalt,diamond
app.exchangeservice.prices = 1,10,100
app.exchangeservice.materials = ACACIA_LOG,CYAN_TERRACOTTA,DIAMOND
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
