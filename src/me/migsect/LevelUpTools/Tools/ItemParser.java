package me.migsect.LevelUpTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemParser 
{
	
	
	
	public static void addExperience(ItemStack item) // Merely works on the itemstack.
	{
	
	}
	
	public ItemInfo parseItem(ItemStack item)
	{
		ItemInfo new_item_info = new ItemInfo(item);
		return new_item_info;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private class ItemInfo {
		// ItemInfo is a data object that is created when an item is parsed.  It stores info such as what enchantments an item has as well as adds helper classes.
		
		
		private int item_exp = 0;
		private HashMap<Enchantment, Integer> vanilla_enchantments = new HashMap<Enchantment, Integer>();
		private HashMap<String, Integer> lore_enchantments = new HashMap<String, Integer>();
		private ItemStack item;
		
		private ItemInfo(ItemStack item)
		{
			List<Enchantment> enchantments = new ArrayList<Enchantment>();
			enchantments.addAll(item.getEnchantments().keySet());
			for(int i = 0 ; i < enchantments.size() ; i ++)
			{
				vanilla_enchantments.put(enchantments.get(i), item.getEnchantmentLevel(enchantments.get(i)));
			}
		}
	}
}
