package me.migsect.LevelUpTools.Tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import me.migsect.LevelUpTools.Helper.RawMaterial;
import me.migsect.LevelUpTools.Helper.ToolType;
import me.migsect.LevelUpTools.Helper;
import me.migsect.LevelUpTools.LevelUpTools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
// Used to better access information stored in the config file and .dat files.
//   handles reading and writing to files (such as the data file).
public class DataManager
{
  private static LevelUpTools plugin;
	
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
	
	// Enchantment maxes/safety.
	private static boolean force_safe_ench = false;
	
	// Tool Block Gains
	private static HashMap<ToolType, HashMap<String, Integer>> tool_exp_gains = new HashMap<ToolType, HashMap<String, Integer>>();
	
	// Do Placed Check
	private static boolean check_if_placed = true;
	
	// EnchantmentInformation
	private static HashMap<String, EnchantmentInfo> enchantments = new HashMap<String, EnchantmentInfo>();
	// Tool Enchants
	private static HashMap<ToolType, List<EnchantmentInfo>> tool_enchants = new HashMap<ToolType, List<EnchantmentInfo>>();
	
	// formatting
	private static HashMap<Integer, String> level_formatting = new HashMap<Integer, String>();
	
	// BrokenBlock storage area.
	private static List<BlockPlaceData> block_places = new ArrayList<BlockPlaceData>();
	
	// Constructor, only needs to be called once.
	public DataManager(LevelUpTools plugin)
	{
		DataManager.plugin = plugin;
		for(ToolType c : ToolType.values())
		{
			// plugin.logger.info(c.toString() + " placed.");
			tool_enchants.put(c, new ArrayList<EnchantmentInfo>());
			tool_exp_gains.put(c, new HashMap<String, Integer>());
		}
		
		parseConfig();
		loadData();
	}
	public static LevelUpTools getPlugin(){return plugin;}
	
	public static double getEnchantmentCostModifier(){return uni_cost_increase;}
	
	public static double getEnchantmentCostModifier(String raw_name){return enchantments.get(raw_name).getCostIncrease();}
	public static double getEnchantmentCostModifier(Enchantment ench){return getEnchantmentCostModifier(ench.toString());}
	
	public static double getMatExpCostModifier(RawMaterial mat){return exp_cost_mod.get(mat);}
	
	public static int getInitialLevelCost(RawMaterial mat){return init_level_cost.get(mat);}
	
	public static double getRepairRatio(RawMaterial mat){return repair_ratio.get(mat);}
	
	public static List<EnchantmentInfo> getToolEnchants(ToolType tool){return tool_enchants.get(tool);}
	
	public static int getEnchantmentBaseCost(String raw_name){return enchantments.get(raw_name).getBaseCost();}
	public static int getEnchantmentBaseCost(Enchantment ench){return getEnchantmentBaseCost(ench.toString());}
	
	public static int getEnchantmentMaxLevel(String raw_name){return enchantments.get(raw_name).getMaxLevel();}
	public static int getEnchantmentMaxLevel(Enchantment ench){return getEnchantmentMaxLevel(ench.toString());}
	
	public static int getExperienceAward(ToolType tool, MaterialInfo mat_info)
	{
		if(!tool_exp_gains.containsKey(tool)) return 0;
		if(!tool_exp_gains.get(tool).containsKey(mat_info.toString())) return 0;
		return tool_exp_gains.get(tool).get(mat_info.toString());
	}
	public static boolean hasExperienceAward(MaterialInfo mat_info)
	{
		for(ToolType type : tool_exp_gains.keySet())
		{
			if(tool_exp_gains.get(type).containsKey(mat_info.toString())) return true;
		}
		return false;
	}
	public static boolean doSafeEnchants(){return force_safe_ench;}
	public static boolean doCheckIfPlaced(){return check_if_placed;}
	public static List<ToolType> getLevelableTypes(){return levelable_types;}
	public static boolean canBeLeveled(ToolType type){return levelable_types.contains(type);}
	
	public static EnchantmentInfo getEnchantmentInfo(Enchantment ench){return enchantments.get(ench.toString());}
	public static EnchantmentInfo getEnchantmentInfo(String raw_name){return enchantments.get(raw_name);}
	public static String getRawName(String display_name)
	{
		for(EnchantmentInfo e : enchantments.values())
		{
			if(e.getDisplayName().equals(display_name)) return e.getRawName();
		}
		return null;
	}
	public static String getFormat(int experience)
	{
		int tier = 0;
		for(int value : level_formatting.keySet())
		{
			if(experience > value) tier = value;
		}
		return level_formatting.get(tier);
	}
	
	public static void addBlockPlace(Location loc)
	{
		Date time = new Date();
		block_places.add(new BlockPlaceData(loc, time));
	}
	// If we want to remove a broken block
	public static void removeBlockPlace(Location loc)
	{
		for(int i = 0 ; i < block_places.size(); i++)
		{
			if(loc.getBlockX() == block_places.get(i).getLocation().getBlockX() &&
					loc.getBlockZ() == block_places.get(i).getLocation().getBlockZ() &&
					loc.getBlockY() == block_places.get(i).getLocation().getBlockY() &&
					loc.getWorld().getName().equals(block_places.get(i).getLocation().getWorld().getName())) block_places.remove(i);
		}
	}
	// Will actually do more than one thing.  It will first check the last element of the list
	//   and remove it if it is past its time.  It will then remove it.  This ensures that we don't go above our limits.
	//   if we are using too much data we will have to do a save/load for every check which may be processor costly
	public static boolean isPlaced(Location loc)
	{
		if(!check_if_placed) return false;
		// plugin.logger.info("Testing block at " + loc.toString());
		if(block_places.size() > 0)
		{
			BlockPlaceData data = block_places.get(0);
			Date cur_time = new Date();
			if((cur_time.getTime() - data.getTime().getTime())/1000 > plugin.getConfig().getInt("Check-Duration-Seconds"))
			{
				// plugin.logger.info("Removing Block becuase of time difference:" + (cur_time.getTime() - data.getTime().getTime())/1000);
				block_places.remove(0);
			}
		}
		// we need to do a loop here, which is O(n) but not all that bad.
		for(BlockPlaceData d : block_places)
		{
			//plugin.logger.info(d.getLocation().getBlockX() + " : " + d.getLocation().getBlockY() + " : " + d.getLocation().getBlockZ() + " - " + 
			//		loc.getBlockX() + " : " + loc.getBlockY() + " : " + loc.getBlockZ());
			
			if(loc.getBlockX() == d.getLocation().getBlockX() &&
					loc.getBlockZ() == d.getLocation().getBlockZ() &&
					loc.getBlockY() == d.getLocation().getBlockY() &&
					loc.getWorld().getName().equals(d.getLocation().getWorld().getName())) return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static void loadData()
	{
		try {
			FileInputStream fileIn = new FileInputStream(plugin.getDataFolder() + "/placed_blocks.dat");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      block_places = (List<BlockPlaceData>) in.readObject();
	    in.close();
	    fileIn.close();
	  }
	  catch(Exception e) {}
	}
	public static void saveData()
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(plugin.getDataFolder() + "/placed_blocks.dat");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(block_places);
      out.close();
      fileOut.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		}
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
		
		// Levable-Item-Types to levelable_types
		List<String> levelable_types_str = plugin.getConfig().getStringList("Levable-Item-Types");
		for(String line : levelable_types_str)
		{
			ToolType tool = ToolType.stringToToolType(line);
			if(tool == null)
			{
				plugin.logger.warning("In Config: " + line + " is not an tool type.");
				continue;
			}
			levelable_types.add(tool);
		}
		
		
		// Cost-Increase to uni_cost_increase
		uni_cost_increase = plugin.getConfig().getDouble("Cost-Increase");
		// All enchantment information
		current_keys.addAll(plugin.ench_config.getConfig().getConfigurationSection("Vanilla-Enchantments").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			Enchantment ench = Enchantment.getByName(current_keys.get(i));
			if(ench.equals(null))
			{
				plugin.logger.warning("In Config: " + current_keys.get(i) + " is not a vanilla enchantment.");
				continue;
			}
			EnchantmentInfo e_info = new EnchantmentInfo(ench);
			e_info.setBaseCost(plugin.ench_config.getConfig().getInt("Vanilla-Enchantments." + current_keys.get(i) + ".Upgrade-Cost"));
			e_info.setMaxLevel(plugin.ench_config.getConfig().getInt("Vanilla-Enchantments." + current_keys.get(i) + ".Max-Level"));
			e_info.setCostIncrease(plugin.ench_config.getConfig().getDouble("Vanilla-Enchantments." + current_keys.get(i) + ".Cost-Increase"));
			
			List<String> acceptable_tools = plugin.getConfig().getStringList("Vanilla-Enchantments." + current_keys.get(i) + ".Tools");
			for(int c = 0 ; c < acceptable_tools.size(); c++)
			{
				ToolType tool = ToolType.stringToToolType(acceptable_tools.get(c));
				if(tool.equals(null))
				{
					plugin.logger.warning("In Config: " + acceptable_tools.get(c) + " is not an tool.");
					continue;
				}
				tool_enchants.get(tool).add(e_ench);
			}
			
			// Display stuff
			String display_name = Helper.formatString(plugin.getConfig().getString("Vanilla-Enchantments." + current_keys.get(i) + ".Display-Name"));
			Material display_mat = Material.getMaterial(plugin.getConfig().getString("Vanilla-Enchantments." + current_keys.get(i) + ".Display-Material"));
			if(display_mat == null) display_mat = Material.GRASS;
			short display_dur = (short)plugin.getConfig().getInt("Vanilla-Enchantments." + current_keys.get(i) + ".Display-Durability");
			int display_amount = plugin.getConfig().getInt("Vanilla-Enchantments." + current_keys.get(i) + ".Display-Amount");
			List<String> display_lore = Helper.formatString(plugin.getConfig().getStringList("Vanilla-Enchantments." + current_keys.get(i) + ".Display-Lore"));
			display_names.put(ench, display_name);
			display_materials.put(ench, display_mat);
			display_durabilities.put(ench, display_dur);
			display_amounts.put(ench, display_amount);
			display_lores.put(ench, display_lore);
			
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
				List<MaterialInfo> mat_info_to_add = Helper.getMaterialInfo(item_keys.get(j));
				for(MaterialInfo item : mat_info_to_add)
				{
					tool_exp_gains.get(ToolType.stringToToolType(current_keys.get(i))).put(item.toString(), plugin.getConfig().getInt("Exp-Awards." + current_keys.get(i) + "." + item_keys.get(j)));
					// MaterialInfo test_item = new MaterialInfo(item.getMaterial(), item.getDurability());
					// plugin.logger.info(current_keys.get(i) + " - " + item.toString() + " found: " + plugin.getConfig().getInt("Exp-Awards." + current_keys.get(i) + "." + item_keys.get(j)));
					// plugin.logger.info(" True-Input: " + tool_exp_gains.get(ToolType.stringToToolType(current_keys.get(i))).get(test_item.toString()));
				}
			}
		}
		current_keys.clear();
		
		// Color Codes
		current_keys.addAll(plugin.getConfig().getConfigurationSection("Experience-Formatting").getKeys(false));
		for(int i = 0 ; i < current_keys.size(); i++)
		{
			int exp_val = 0;
			try
			{
				exp_val = Integer.parseInt(current_keys.get(i));
			}
			catch (NumberFormatException e)
			{
				plugin.logger.warning("In Config: " + current_keys.get(i) + " is not a number.");
				continue;
			}
			// plugin.logger.info("Found: " + current_keys.get(i) + " converted to: " + exp_val + " with inside: " + plugin.getConfig().getString("Experience-Formatting." + current_keys.get(i)));
			level_formatting.put(exp_val, Helper.formatString(plugin.getConfig().getString("Experience-Formatting." + current_keys.get(i))));
		}
	}
	
	public static class BlockPlaceData implements Serializable
	{
		private static final long serialVersionUID = -2785209452443044418L;
		private int x;
		private int y;
		private int z;
		private String world;
		
		private long time;
		
		private BlockPlaceData(Location loc, Date time)
		{
			world = loc.getWorld().getName();
			this.time = time.getTime();
			
			x = loc.getBlockX();
			y = loc.getBlockY();
			z = loc.getBlockZ();
		}
		public Location getLocation()
		{
			return new Location(Bukkit.getWorld(world), x, y, z);
		}
		public Date getTime()
		{
			return new Date(time);
		}
		
	}
}
