package me.migsect.LevelUpTools.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.migsect.LevelUpTools.Helper.RawMaterial;
import me.migsect.LevelUpTools.Helper.ToolType;
import me.migsect.LevelUpTools.Helper;
import me.migsect.LevelUpTools.LevelUpTools;

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
	private static List<ToolType> levelable_types = new ArrayList<ToolType>();
	
	// These values determine price changes for certain enchantments.
	//   The equation for this (basic per) is initial_cost * uni_cost_increase^cur_level * cost_increase^cur_level
	private static double uni_cost_increase = 0;
	private static HashMap<Enchantment, Double> vanilla_cost_increase = new HashMap<Enchantment, Double>();
	
	// Enchantment maxes/safety.
	private static boolean force_safe_ench = false;
	private static HashMap<Enchantment, Integer> vanilla_max_level = new HashMap<Enchantment, Integer>();
	
	// Enchantment base costs:
	private static HashMap<Enchantment, Integer> vanilla_base_cost = new HashMap<Enchantment, Integer>();
	
	// Tool Type Enchantments
	private static HashMap<ToolType, List<Enchantment>> vanilla_tool_enchant = new HashMap<ToolType, List<Enchantment>>();
	
	// Tool Block Gains
	private static HashMap<ToolType, HashMap<ItemStack, Integer>> tool_exp_gains = new HashMap<ToolType, HashMap<ItemStack, Integer>>();
	
	// Do Placed Check
	private static boolean check_if_placed = true;
	
	public DataManager(LevelUpTools plugin)
	{
		DataManager.plugin = plugin;
		parseConfig();
		
		for(ToolType c : ToolType.values())
		{
			vanilla_tool_enchant.put(c, new ArrayList<Enchantment>());
			tool_exp_gains.put(c, new HashMap<ItemStack, Integer>());
		}
	}
	
	public double getEnchantmentCostModifier()
	{
		return uni_cost_increase;
	}
	public double getEnchantmentCostModifier(Enchantment ench)
	{
		return vanilla_cost_increase.get(ench);
	}
	public double getMatExpCostModifier(RawMaterial mat)
	{
		return exp_cost_mod.get(mat);
	}
	public int getInitialLevelCost(RawMaterial mat)
	{
		return init_level_cost.get(mat);
	}
	public double getRepairRatio(RawMaterial mat)
	{
		return repair_ratio.get(mat);
	}
	public List<Enchantment> getToolEnchants(ToolType tool)
	{
		return vanilla_tool_enchant.get(tool);
	}
	public int getEnchantmentBaseCost(Enchantment ench)
	{	
		return vanilla_base_cost.get(ench);
	}
	public int getEnchantmentMaxLevel(Enchantment ench)
	{
		return vanilla_max_level.get(ench);
	}
	public int getExperienceAward(ToolType tool, ItemStack item)
	{
		return tool_exp_gains.get(tool).get(item);
	}
	public boolean doSafeEnchants()
	{
		return force_safe_ench;
	}
	public boolean doCheckIfPlaced()
	{
		return check_if_placed;
	}
	public List<ToolType> getLevelableTypes()
	{
		return levelable_types;
	}
	public boolean canBeLeveled(ToolType type)
	{
		return levelable_types.contains(type);
	}
	public void parseConfig()
	{
		List<String> current_keys = new ArrayList<String>();
		
		// Experience-Cost-Modifiers to exp_cost_mod
		current_keys.addAll(plugin.getConfig().getConfigurationSection("Experience-Cost-Modifiers").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			exp_cost_mod.put(RawMaterial.stringToMat(current_keys.get(i)), plugin.getConfig().getDouble("Experience-Cost-Modifiers." + current_keys.get(i)));
		}
		current_keys.clear();
		
		// Initial-Level-Cost to init_level_cost
		current_keys.addAll(plugin.getConfig().getConfigurationSection("Initial-Level-Cost").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			init_level_cost.put(RawMaterial.stringToMat(current_keys.get(i)), plugin.getConfig().getInt("Initial-Level-Cost." + current_keys.get(i)));
		}
		current_keys.clear();
		
		// Repair-Exp-Cost to repair ratio
		current_keys.addAll(plugin.getConfig().getConfigurationSection("Repair-Exp-Cost").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			repair_ratio.put(RawMaterial.stringToMat(current_keys.get(i)), plugin.getConfig().getDouble("Repair-Exp-Cost." + current_keys.get(i)));
		}
		current_keys.clear();
		
		
		// Cost-Increase to uni_cost_increase
		uni_cost_increase = plugin.getConfig().getDouble("Cost-Increase");
		// All enchantment information
		current_keys.addAll(plugin.getConfig().getConfigurationSection("Vanilla-Enchantments").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			Enchantment ench = Enchantment.getByName(current_keys.get(i));
			if(ench.equals(null))
			{
				plugin.logger.warning("In Config: " + current_keys.get(i) + " is not an enchantment.");
				continue;
			}
			
			vanilla_max_level.put(Enchantment.getByName(current_keys.get(i)), plugin.getConfig().getInt("Vanilla-Enchantments." + current_keys.get(i) + ".Max-Level"));
			vanilla_base_cost.put(Enchantment.getByName(current_keys.get(i)), plugin.getConfig().getInt("Vanilla-Enchantments." + current_keys.get(i) + ".Upgrade-Cost"));
			vanilla_base_cost.put(Enchantment.getByName(current_keys.get(i)), plugin.getConfig().getInt("Vanilla-Enchantments." + current_keys.get(i) + ".Cost-Increase"));
			List<String> acceptable_tools = plugin.getConfig().getStringList("Vanilla-Enchantments." + current_keys.get(i) + ".Tools");
			for(int c = 0 ; c < acceptable_tools.size(); c++)
			{
				vanilla_tool_enchant.get(acceptable_tools.get(c)).add(ench);
			}
		}
		current_keys.clear();
		
		// checked_if_placed sett
		check_if_placed = plugin.getConfig().getBoolean("Do-Check-Placed-Block");
		
		// Block Exp Value sett
		current_keys.addAll(plugin.getConfig().getConfigurationSection("Exp-Awards").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			List<String> item_keys = new ArrayList<String>();
			item_keys.addAll(plugin.getConfig().getConfigurationSection("Exp-Awards." + current_keys.get(i)).getKeys(false));
			for(int j = 0 ; j < item_keys.size(); j++)
			{
				List<ItemStack> items_to_add = Helper.getSimpleItemStack(item_keys.get(i));
				for(ItemStack item : items_to_add)
				{
					tool_exp_gains.get(ToolType.stringToToolType(current_keys.get(i))).put(item, plugin.getConfig().getInt("Exp-Awards." + current_keys.get(i) + "." + item_keys.get(i)));
				}
			}
		}
	}
}
