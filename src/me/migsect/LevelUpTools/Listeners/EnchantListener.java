package me.migsect.LevelUpTools.Listeners;

import java.util.List;

import me.migsect.LevelUpTools.Helper;
import me.migsect.LevelUpTools.Menu.MenuHandler;
import me.migsect.LevelUpTools.Menu.MenuHandler.Menu;
import me.migsect.LevelUpTools.Menu.OptionUpgradeEnchantment;
import me.migsect.LevelUpTools.Tools.DataManager;
import me.migsect.LevelUpTools.Tools.ItemInfo;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantListener implements Listener
{
	@EventHandler
	public void onTableRightClick(PlayerInteractEvent event)
	{
		if(event.isCancelled()) return;
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = event.getItem();
		ItemInfo info = new ItemInfo(item);

		// our requirement for table clicking is that the player needs to be RCing the table as well as sneaking.
		if(!Helper.isTool(item.getType())) return;
		if(!player.isSneaking() || !action.equals(Action.RIGHT_CLICK_BLOCK)) return;
		Block block = event.getClickedBlock();
		if(!block.getType().equals(Material.ENCHANTMENT_TABLE)) return;
		
		if(!DataManager.canBeLeveled(Helper.getToolType(item.getType())))
		{
			player.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "You attempt to empower the item, but you are not sure you are able to.");
			return;
		}

		if(info.isLeveled())
		{
			List<Enchantment> enchants = DataManager.getToolEnchants(Helper.getToolType(item.getType()));
			int menu_size = 9 + 9 * (enchants.size() / 9);
			Menu new_menu = MenuHandler.getNewMenu(player, menu_size, ChatColor.DARK_GRAY + "Enchant Item");
			for(Enchantment e : enchants)
			{
				OptionUpgradeEnchantment option = new OptionUpgradeEnchantment(e, item);
				String numeral = Helper.intToNumeral(info.getEnchantmentLevel(e) + 1);
				option.setDisplayName(DataManager.getEnchantmentDisplayName(e) + " " + numeral);
				option.setDisplayMaterial(DataManager.getEnchantmentDisplayMaterial(e));
				option.setDisplayDurability(DataManager.getEnchantmentDisplayDurability(e));
				option.setDisplayAmount(DataManager.getEnchantmentDisplayAmount(e));
				option.addDisplayLore(DataManager.getEnchantmentDisplayLore(e));
				new_menu.addOption(option);
			}
			new_menu.openInventory();
		}
		else
		{
			int level_cost = DataManager.getInitialLevelCost(Helper.getRawMaterial(item.getType()));
			int player_lvl = player.getLevel();
			if(level_cost > player_lvl && !player.getGameMode().equals(GameMode.CREATIVE))
			{
				player.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "You attempt to empower the item, but you do not have the necessary experience to do so.");
				player.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "You require " + ChatColor.GREEN + level_cost + " Levels " + ChatColor.BLUE + "to empower this item.");
				return;
			}
			player.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "You infuse the item with your past knowledge, granting it the ability to learn for itself.");
			info.addExperience(0);
			player.updateInventory();
			if(!player.getGameMode().equals(GameMode.CREATIVE)) player.setLevel(player_lvl - level_cost);
		}
	}
}
