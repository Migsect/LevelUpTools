package me.migsect.LevelUpTools.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.migsect.LevelUpTools.Helper;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemInfo {
	// ItemInfo is a data object that is created when an item is parsed.  It stores info such as what enchantments an item has as well as adds helper classes.
	
	private int item_exp = 0;
	private boolean is_leveled = false;
	private HashMap<Enchantment, Integer> vanilla_enchantments = new HashMap<Enchantment, Integer>();
	// private HashMap<String, Integer> lore_enchantments = new HashMap<String, Integer>();
	private ItemStack item;
	
	public ItemInfo(ItemStack item)
	{
		// setting item
		this.item = item;
		
		// Compiling the list of enchantments.
		List<Enchantment> enchantments = new ArrayList<Enchantment>();
		enchantments.addAll(item.getEnchantments().keySet());
		for(int i = 0 ; i < enchantments.size() ; i ++)
		{
			vanilla_enchantments.put(enchantments.get(i), item.getEnchantmentLevel(enchantments.get(i)));
		}
		
		// Setting other flags such as is_levelable and item_exp.
		if(item.hasItemMeta() && item.getItemMeta().hasLore())
		{
			List<String> lore = item.getItemMeta().getLore();
			for(String line : lore)
			{
				if(ChatColor.stripColor(line).startsWith("Experience: "))
				{
					is_leveled = true;
					String[] line_split = ChatColor.stripColor(line).split(" ");
					try
					{
						item_exp = Integer.parseInt(line_split[1]);
					}
					catch (NumberFormatException e){}
					break;
				}
			}
		}
	}
	public ItemStack getItem()
	{
		return item;
	}
	public boolean isLeveled()
	{
		return is_leveled;
	}
	
	// In the future this will have to include custom enchantments
	public int getTotalEnchantmentLevel()
	{
		int sum = 0;
		for(int c : vanilla_enchantments.values())
		{
			sum += c;
		}
		return sum;
	}

	// In the future this will have to include custom enchantments
	public int getNumberOfEnchantments()
	{
		return vanilla_enchantments.keySet().size();
	}
	public int getEnchantmentLevel(Enchantment ench)
	{
		if(!vanilla_enchantments.containsKey(ench)) return 0;
		return vanilla_enchantments.get(ench);
	}
	public int getEnchantmentCost(Enchantment ench)
	{
		int base_cost = DataManager.getEnchantmentBaseCost(ench);
		double uni_mod = DataManager.getEnchantmentCostModifier();
		double ench_mod = DataManager.getEnchantmentCostModifier(ench);
		double tool_mod = DataManager.getMatExpCostModifier(Helper.getRawMaterial(item.getType()));
		
		int uni_mod_pow = this.getNumberOfEnchantments();
		if(uni_mod_pow < 0) uni_mod_pow = 0;
		double return_val = base_cost * tool_mod * Math.pow(uni_mod, uni_mod_pow) * Math.pow(ench_mod, this.getEnchantmentLevel(ench));
		return (int) return_val;
	}
	public int getExperience()
	{
		return item_exp;
	}
	
	// Mutaters, these make life easier on the whole way round!
	public void addExperience(int amount)
	{
		int new_exp = item_exp + amount;
		List<String> new_lore = new ArrayList<String>();
		if(is_leveled)
		{
			new_lore.addAll(item.getItemMeta().getLore());
			for(int i = 0 ; i < new_lore.size(); i++)
			{
				if(ChatColor.stripColor(new_lore.get(i)).startsWith("Experience: "))
				{
					new_lore.set(i, ChatColor.GOLD + "Experience: " + ChatColor.YELLOW + new_exp);
				}
			}
		}
		else
		{
			new_lore.add(ChatColor.GOLD + "Experience: " + ChatColor.YELLOW + new_exp);
			if(item.getItemMeta().hasLore()) new_lore.addAll(item.getItemMeta().getLore());
		}
		ItemMeta im = item.getItemMeta();
		im.setLore(new_lore);
		if(im.hasDisplayName()) im.setDisplayName(ChatColor.BLUE + im.getDisplayName());
		else im.setDisplayName(ChatColor.BLUE + Helper.realName(Helper.getRawMaterial(item.getType()), Helper.getToolType(item.getType())));
		
		item.setItemMeta(im);
	}
	public void upgradeEnchantment(Enchantment ench)
	{
		int ench_lv = item.getEnchantmentLevel(ench);
		if(DataManager.doSafeEnchants()) item.addEnchantment(ench, ench_lv + 1);
		else item.addUnsafeEnchantment(ench, ench_lv + 1); 
	}
}