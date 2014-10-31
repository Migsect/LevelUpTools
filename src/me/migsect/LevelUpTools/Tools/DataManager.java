package me.migsect.LevelUpTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

// Used to better access information stored in the config file and .dat files.
//   handles reading and writing to files (such as the data file).
public class DataManager
{
	static private LevelUpTools plugin;
	
	// This map stores the exp_cost_mod that each material has for its upgrades.
	private static HashMap<RawMaterial, Double> exp_cost_mod = new HashMap<RawMaterial, Double>();
	
	// This map stores the initial cost in levels for each different material type such as when
	//   an item is transformed into a leveling-item at the enchantment table.
	private static HashMap<RawMaterial, Integer> init_level_cost = new HashMap<RawMaterial, Integer>();
	
	// This map stores the percent amount of exp that is lost when one uses the SRC on an anvil.
	//   Each repair only repairs 1-items worth.
	private static HashMap<RawMaterial, Double> repair_ratio = new HashMap<RawMaterial, Double>();
	
	// This list stores all the types of items that can be turned into a leveling tool
	private static List<ToolType> levelable_items = new ArrayList<ToolType>();
	
	// These values determine price changes for certain enchantments.
	//   The equation for this (basic per) is initial_cost * uni_cost_increase^cur_level * cost_increase^cur_level
	private static double uni_cost_increase = 0;
	private static HashMap<Enchantment, Double> vanilla_cost_increase = new HashMap<Enchantment, Double>();
	
	// Enchantment maxes/safety.
	private static boolean force_safe_ench = true;
	private static HashMap<Enchantment, Integer> vanilla_max_level = new HashMap<Enchantment, Integer>();
	
	// Tool Type Enchantments
	private static HashMap<ToolType, List<Enchantment>> vanilla_tool_enchant = new HashMap<ToolType, List<Enchantment>>();
	
	// Tool Block Gains
	private static HashMap<ToolType, HashMap<ItemStack, Integer>> tool_exp_gains = new HashMap<ToolType, HashMap<ItemStack, Integer>>();
	
	public DataManager(LevelUpTools plugin)
	{
		DataManager.plugin = plugin;
		parseConfig();
	}
	
	private void parseConfig()
	{
		// exp_cost_mod setting
		List<String> current_keys = new ArrayList<String>();
		current_keys.addAll(plugin.getConfig().getConfigurationSection("Experience-Cost-Modifiers").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			exp_cost_mod.put(RawMaterial.stringToMat(current_keys.get(i)), plugin.getConfig().getDouble("Experience-Cost-Modifiers." + current_keys.get(i)));
		}
		current_keys.clear();
		current_keys.addAll(plugin.getConfig().getConfigurationSection("Initial-Level-Cost").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			init_level_cost.put(RawMaterial.stringToMat(current_keys.get(i)), plugin.getConfig().getInt("Initial-Level-Cost." + current_keys.get(i)));
		}
	}
}
