package me.migsect.LevelUpTools.Menu;

import java.util.List;

import me.migsect.LevelUpTools.Helper;
import me.migsect.LevelUpTools.Menu.MenuHandler.Menu;
import me.migsect.LevelUpTools.Tools.DataManager;
import me.migsect.LevelUpTools.Tools.ItemInfo;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OptionUpgradeEnchantment extends Option 
{
	private Enchantment ench;
	
	public OptionUpgradeEnchantment(Enchantment ench, ItemStack item)
	{
		this.ench = ench;
		
		ItemInfo info = new ItemInfo(item);
		int cur_exp = info.getExperience();
		int upgrd_cost = info.getEnchantmentCost(ench);
		
		if(cur_exp > upgrd_cost) this.display_lore.add(ChatColor.GOLD + "Experience Cost: " + ChatColor.YELLOW + upgrd_cost);
		else this.display_lore.add(ChatColor.GOLD + "Experience Cost: " + ChatColor.RED + upgrd_cost);
	}
	
	@Override
	public void onClick(Player player)
	{
		ItemStack item = player.getItemInHand();
		ItemInfo info = new ItemInfo(item);
		int cur_exp = info.getExperience();
		int upgrd_cost = info.getEnchantmentCost(ench);
		if(cur_exp < upgrd_cost) return;
		info.addExperience(-upgrd_cost);
		info.upgradeEnchantment(ench);
		player.updateInventory();
		info = new ItemInfo(item);
		
		Menu menu = MenuHandler.getMenu(player);
		menu.removeOptions();
		List<Enchantment> enchants = DataManager.getToolEnchants(Helper.getToolType(item.getType()));
		for(Enchantment e : enchants)
		{
			int max_level = DataManager.getEnchantmentMaxLevel(e);
			if(max_level == info.getEnchantmentLevel(e)) continue;
			
			OptionUpgradeEnchantment option = new OptionUpgradeEnchantment(e, item);
			String numeral = Helper.intToNumeral(info.getEnchantmentLevel(e) + 1);
			option.setDisplayName(DataManager.getEnchantmentDisplayName(e) + " " + numeral);
			option.setDisplayMaterial(DataManager.getEnchantmentDisplayMaterial(e));
			option.setDisplayDurability(DataManager.getEnchantmentDisplayDurability(e));
			option.setDisplayAmount(DataManager.getEnchantmentDisplayAmount(e));
			option.addDisplayLore(DataManager.getEnchantmentDisplayLore(e));
			menu.addOption(option);
		}
		menu.updateInventory();

		// Playing the sound:
		player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
	}

}
