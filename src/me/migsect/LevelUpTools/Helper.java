package me.migsect.LevelUpTools;

import java.util.ArrayList;
import java.util.List;

import me.migsect.LevelUpTools.Tools.MaterialInfo;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
public class Helper
{
	public static boolean isHoe(Material mat)
	{
		if(mat.equals(Material.WOOD_HOE)) return true;
		if(mat.equals(Material.STONE_HOE)) return true;
		if(mat.equals(Material.IRON_HOE)) return true;
		if(mat.equals(Material.GOLD_HOE)) return true;
		if(mat.equals(Material.DIAMOND_HOE)) return true;
		return false;
	}
	public static boolean isSword(Material mat)
	{
		if(mat.equals(Material.WOOD_SWORD)) return true;
		if(mat.equals(Material.STONE_SWORD)) return true;
		if(mat.equals(Material.IRON_SWORD)) return true;
		if(mat.equals(Material.GOLD_SWORD)) return true;
		if(mat.equals(Material.DIAMOND_SWORD)) return true;
		return false;
	}
	public static boolean isPickaxe(Material mat)
	{
		if(mat.equals(Material.WOOD_PICKAXE)) return true;
		if(mat.equals(Material.STONE_PICKAXE)) return true;
		if(mat.equals(Material.IRON_PICKAXE)) return true;
		if(mat.equals(Material.GOLD_PICKAXE)) return true;
		if(mat.equals(Material.DIAMOND_PICKAXE)) return true;
		return false;
	}
	public static boolean isAxe(Material mat)
	{
		if(mat.equals(Material.WOOD_AXE)) return true;
		if(mat.equals(Material.STONE_AXE)) return true;
		if(mat.equals(Material.IRON_AXE)) return true;
		if(mat.equals(Material.GOLD_AXE)) return true;
		if(mat.equals(Material.DIAMOND_AXE)) return true;
		return false;
	}
	public static boolean isSpade(Material mat)
	{
		if(mat.equals(Material.WOOD_SPADE)) return true;
		if(mat.equals(Material.STONE_SPADE)) return true;
		if(mat.equals(Material.IRON_SPADE)) return true;
		if(mat.equals(Material.GOLD_SPADE)) return true;
		if(mat.equals(Material.DIAMOND_SPADE)) return true;
		return false;
	}
	
	public static ItemType getToolType(Material mat)
	{
		if(isSpade(mat)) return ItemType.SPADE;
		if(isHoe(mat)) return ItemType.HOE;
		if(isAxe(mat)) return ItemType.AXE;
		if(isPickaxe(mat)) return ItemType.PICKAXE;
		if(isSword(mat)) return ItemType.SWORD;
		return null;
	}
	public static boolean isTool(Material mat)
	{
		return getToolType(mat) != null;
	}
	public enum ItemType
	{
		SWORD (2),
		HOE (2),
		AXE (3),
		PICKAXE (3),
		SPADE (1),
		BOW (3),
		FISHING_ROD (3),
		SHEAR (2),
		
		HELMET (5),
		CHESTPLATE (8),
		LEGGINGS (7),
		BOOTS (4);
		
		private int material_amount = 0;
		
		ItemType(int material_amount)
		{
			this.material_amount = material_amount;
		}
		
		public int getMaterialAmount()
		{
			return this.material_amount;
		}
		
		public static ItemType stringToToolType(String str)
		{
			if(str.equalsIgnoreCase("sword")) return ItemType.SWORD;
			if(str.equalsIgnoreCase("hoe")) return ItemType.HOE;
			if(str.equalsIgnoreCase("axe")) return ItemType.AXE;
			if(str.equalsIgnoreCase("pickaxe")) return ItemType.PICKAXE;
			if(str.equalsIgnoreCase("spade")) return ItemType.SPADE;
			
			if(str.equalsIgnoreCase("bow")) return ItemType.BOW;
			if(str.equalsIgnoreCase("fishing_rod")) return ItemType.FISHING_ROD;
			if(str.equalsIgnoreCase("shear")) return ItemType.SHEAR;
			
			if(str.equalsIgnoreCase("helmet")) return ItemType.HELMET;
			if(str.equalsIgnoreCase("chestplate")) return ItemType.CHESTPLATE;
			if(str.equalsIgnoreCase("leggings")) return ItemType.LEGGINGS;
			if(str.equalsIgnoreCase("boots")) return ItemType.BOOTS;
			return null;
		}
	}
	
	public static RawMaterial getRawMaterial(Material tool)
	{
		if(tool.equals(Material.WOOD_HOE)) return RawMaterial.WOOD;
		if(tool.equals(Material.WOOD_SWORD)) return RawMaterial.WOOD;
		if(tool.equals(Material.WOOD_PICKAXE)) return RawMaterial.WOOD;
		if(tool.equals(Material.WOOD_AXE)) return RawMaterial.WOOD;
		if(tool.equals(Material.WOOD_SPADE)) return RawMaterial.WOOD;

		if(tool.equals(Material.BOW)) return RawMaterial.WOOD;
		if(tool.equals(Material.FISHING_ROD)) return RawMaterial.WOOD;

		if(tool.equals(Material.LEATHER_HELMET)) return RawMaterial.LEATHER;
		if(tool.equals(Material.LEATHER_CHESTPLATE)) return RawMaterial.LEATHER;
		if(tool.equals(Material.LEATHER_LEGGINGS)) return RawMaterial.LEATHER;
		if(tool.equals(Material.LEATHER_BOOTS)) return RawMaterial.LEATHER;
		
		if(tool.equals(Material.STONE_HOE)) return RawMaterial.STONE;
		if(tool.equals(Material.STONE_SWORD)) return RawMaterial.STONE;
		if(tool.equals(Material.STONE_PICKAXE)) return RawMaterial.STONE;
		if(tool.equals(Material.STONE_AXE)) return RawMaterial.STONE;
		if(tool.equals(Material.STONE_SPADE)) return RawMaterial.STONE;
		
		if(tool.equals(Material.IRON_HOE)) return RawMaterial.IRON;
		if(tool.equals(Material.IRON_SWORD)) return RawMaterial.IRON;
		if(tool.equals(Material.IRON_PICKAXE)) return RawMaterial.IRON;
		if(tool.equals(Material.IRON_AXE)) return RawMaterial.IRON;
		if(tool.equals(Material.IRON_SPADE)) return RawMaterial.IRON;

		if(tool.equals(Material.SHEARS)) return RawMaterial.IRON;

		if(tool.equals(Material.IRON_HELMET)) return RawMaterial.IRON;
		if(tool.equals(Material.IRON_CHESTPLATE)) return RawMaterial.IRON;
		if(tool.equals(Material.IRON_LEGGINGS)) return RawMaterial.IRON;
		if(tool.equals(Material.IRON_BOOTS)) return RawMaterial.IRON;
		if(tool.equals(Material.CHAINMAIL_HELMET)) return RawMaterial.IRON;
		if(tool.equals(Material.CHAINMAIL_CHESTPLATE)) return RawMaterial.IRON;
		if(tool.equals(Material.CHAINMAIL_LEGGINGS)) return RawMaterial.IRON;
		if(tool.equals(Material.CHAINMAIL_BOOTS)) return RawMaterial.IRON;
		
		if(tool.equals(Material.GOLD_HOE)) return RawMaterial.GOLD;
		if(tool.equals(Material.GOLD_SWORD)) return RawMaterial.GOLD;
		if(tool.equals(Material.GOLD_PICKAXE)) return RawMaterial.GOLD;
		if(tool.equals(Material.GOLD_AXE)) return RawMaterial.GOLD;
		if(tool.equals(Material.GOLD_SPADE)) return RawMaterial.GOLD;

		if(tool.equals(Material.GOLD_HELMET)) return RawMaterial.GOLD;
		if(tool.equals(Material.GOLD_CHESTPLATE)) return RawMaterial.GOLD;
		if(tool.equals(Material.GOLD_LEGGINGS)) return RawMaterial.GOLD;
		if(tool.equals(Material.GOLD_BOOTS)) return RawMaterial.GOLD;
		
		if(tool.equals(Material.DIAMOND_HOE)) return RawMaterial.DIAMOND;
		if(tool.equals(Material.DIAMOND_SWORD)) return RawMaterial.DIAMOND;
		if(tool.equals(Material.DIAMOND_PICKAXE)) return RawMaterial.DIAMOND;
		if(tool.equals(Material.DIAMOND_AXE)) return RawMaterial.DIAMOND;
		if(tool.equals(Material.DIAMOND_SPADE)) return RawMaterial.DIAMOND;
		
		if(tool.equals(Material.DIAMOND_HELMET)) return RawMaterial.DIAMOND;
		if(tool.equals(Material.DIAMOND_CHESTPLATE)) return RawMaterial.DIAMOND;
		if(tool.equals(Material.DIAMOND_LEGGINGS)) return RawMaterial.DIAMOND;
		if(tool.equals(Material.DIAMOND_BOOTS)) return RawMaterial.DIAMOND;
		
		return null;
	}
	public static String realName(RawMaterial mat, ItemType tool)
	{
		String return_str = "";
		if(mat.equals(RawMaterial.WOOD)) return_str = return_str + "Wood ";
		if(mat.equals(RawMaterial.STONE)) return_str = return_str + "Stone ";
		if(mat.equals(RawMaterial.GOLD)) return_str = return_str + "Gold ";
		if(mat.equals(RawMaterial.IRON)) return_str = return_str + "Iron ";
		if(mat.equals(RawMaterial.DIAMOND)) return_str = return_str + "Diamond ";
		
		if(tool.equals(ItemType.AXE)) return_str = return_str + "Axe";
		if(tool.equals(ItemType.SPADE)) return_str = return_str + "Shovel";
		if(tool.equals(ItemType.PICKAXE)) return_str = return_str + "Pickaxe";
		if(tool.equals(ItemType.SWORD)) return_str = return_str + "Sword";
		if(tool.equals(ItemType.HOE)) return_str = return_str + "Hoe";
		return return_str;
	}
	
	public enum RawMaterial
	{
		WOOD (Material.WOOD),
		LEATHER (Material.LEATHER),
		STONE (Material.COBBLESTONE),
		IRON (Material.IRON_INGOT),
		GOLD (Material.GOLD_INGOT),
		DIAMOND (Material.DIAMOND),
		CHAIN (Material.IRON_INGOT);
		
		private final Material true_mat;
		
		RawMaterial(Material true_mat)
		{
			this.true_mat = true_mat;
		}
		public Material getTrueMaterial()
		{
			return true_mat;
		}
		public static RawMaterial stringToMat(String str)
		{
			if(str.equalsIgnoreCase("wood")) return RawMaterial.WOOD;
			if(str.equalsIgnoreCase("leather")) return RawMaterial.LEATHER;
			if(str.equalsIgnoreCase("stone")) return RawMaterial.STONE;
			if(str.equalsIgnoreCase("chain")) return RawMaterial.CHAIN;
			if(str.equalsIgnoreCase("gold")) return RawMaterial.GOLD;
			if(str.equalsIgnoreCase("iron")) return RawMaterial.IRON;
			if(str.equalsIgnoreCase("diamond")) return RawMaterial.DIAMOND;	
			return null;
		}
	}
	
	// returns a list of item stacks of 1 item of the type determined from the string.
	//  The reason we are return multiple is because some items have multiple variations. (such as a log)
	public static List<MaterialInfo> getMaterialInfo(String str)
	{
		List<MaterialInfo> return_list = new ArrayList<MaterialInfo>();
		if(str.equals("OAK_LOG"))
		{
			MaterialInfo item1 = new MaterialInfo(Material.LOG, (short)0);
			MaterialInfo item2 = new MaterialInfo(Material.LOG, (short)4);
			MaterialInfo item3 = new MaterialInfo(Material.LOG,  (short)8);
			MaterialInfo item4 = new MaterialInfo(Material.LOG,  (short)12);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
		}
		else if(str.equals("SPRUCE_LOG"))
		{
			MaterialInfo item1 = new MaterialInfo(Material.LOG,  (short)1);
			MaterialInfo item2 = new MaterialInfo(Material.LOG,  (short)5);
			MaterialInfo item3 = new MaterialInfo(Material.LOG,  (short)9);
			MaterialInfo item4 = new MaterialInfo(Material.LOG,  (short)13);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
		}
		else if(str.equals("BIRCH_LOG"))
		{
			MaterialInfo item1 = new MaterialInfo(Material.LOG, (short)2);
			MaterialInfo item2 = new MaterialInfo(Material.LOG, (short)6);
			MaterialInfo item3 = new MaterialInfo(Material.LOG, (short)10);
			MaterialInfo item4 = new MaterialInfo(Material.LOG, (short)14);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
			
		}
		else if(str.equals("JUNGLE_LOG"))
		{
			MaterialInfo item1 = new MaterialInfo(Material.LOG, (short)3);
			MaterialInfo item2 = new MaterialInfo(Material.LOG, (short)7);
			MaterialInfo item3 = new MaterialInfo(Material.LOG, (short)11);
			MaterialInfo item4 = new MaterialInfo(Material.LOG, (short)15);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
			
		}
		else if(str.equals("ACACIA_LOG"))
		{
			MaterialInfo item1 = new MaterialInfo(Material.LOG_2, (short)0);
			MaterialInfo item2 = new MaterialInfo(Material.LOG_2, (short)4);
			MaterialInfo item3 = new MaterialInfo(Material.LOG_2, (short)8);
			MaterialInfo item4 = new MaterialInfo(Material.LOG_2, (short)12);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
			
		}
		else if(str.equals("DARK_OAK_LOG"))
		{
			MaterialInfo item1 = new MaterialInfo(Material.LOG_2, (short)1);
			MaterialInfo item2 = new MaterialInfo(Material.LOG_2, (short)5);
			MaterialInfo item3 = new MaterialInfo(Material.LOG_2, (short)9);
			MaterialInfo item4 = new MaterialInfo(Material.LOG_2, (short)13);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
			
		}
		else
		{
			Material mat = Material.getMaterial(str);
			if(mat == null) return return_list;
			MaterialInfo item = new MaterialInfo(mat);
			return_list.add(item);
		}
		
		return return_list;
	}
	
	public static int numeralToInt(String numeral)
	{
		int sum = 0;
		// looping through the string.
		int max = 0;
		for(int c = numeral.length(); c >= 0; c--)
		{
			int cur_num = numeralVal(numeral.charAt(c));
			if(cur_num > max)
			{
				max = cur_num;
				sum += cur_num;
			}
			else if(cur_num < max)
			{
				sum -= cur_num;
			}
			else
			{
				sum += cur_num;
			}
		}
		return sum;
	}
	private static int numeralVal(char numeral)
	{
		switch(numeral)
		{
			case 'I':
				return 1;
			case 'V':
				return 5;
			case 'X':
				return 10;
			case 'L':
				return 50;
			case 'C':
				return 100;
			case 'D':
				return 500;
			case 'M':
				return 1000;
		}
		return 0;
	}
	public static String intToNumeral(int number)
	{
		String numeral = "";
		while(number > 0)
		{
			if(number >= 1000)
			{
				number -= 1000;
				numeral = numeral + "M";
			}
			else if(number >= 900)
			{
				number -= 900;
				numeral = numeral + "CM";
			}
			else if(number >= 500)
			{
				number -= 500;
				numeral = numeral + "D";
			}
			else if(number >= 400)
			{
				number -= 400;
				numeral = numeral + "DC";
			}
			else if(number >= 100)
			{
				number -= 100;
				numeral = numeral + "C";
			}
			else if(number >= 90)
			{
				number -= 90;
				numeral = numeral + "XC";
			}
			else if(number >= 50)
			{
				number -= 50;
				numeral = numeral + "L";
			}
			else if(number >= 40)
			{
				number -= 40;
				numeral = numeral + "XL";
			}
			else if(number >= 10)
			{
				number -= 10;
				numeral = numeral + "X";
			}
			else if(number >= 9)
			{
				number -= 9;
				numeral = numeral + "IX";
			}
			else if(number >= 5)
			{
				number -= 5;
				numeral = numeral + "V";
			}
			else if(number >= 4)
			{
				number -= 4;
				numeral = numeral + "IV";
			}
			else if(number >= 1)
			{
				number -= 1;
				numeral = numeral + "I";
			}
			if(number < 0) return "ERROR";
		}
		return numeral;
	}
	public static boolean isNumeral(String numeral)
	{
		return numeralToInt(numeral) != 0;
	}
	public static boolean isNumeric(String str)
	{
		try
		{
			Integer.parseInt(str);
			return true; // if it errors, then the return will never return
		} 
		catch (NumberFormatException e) {}
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch (NumberFormatException e) {}
		return isNumeral(str);
	}
	
	public static String formatString(String string)
	{
		String newString = string;
		
		newString = newString.replace("&0", "" + ChatColor.BLACK);
		newString = newString.replace("&1", "" + ChatColor.DARK_BLUE);
		newString = newString.replace("&2", "" + ChatColor.DARK_GREEN);
		newString = newString.replace("&3", "" + ChatColor.DARK_AQUA);
		newString = newString.replace("&4", "" + ChatColor.DARK_RED);
		newString = newString.replace("&5", "" + ChatColor.DARK_PURPLE);
		newString = newString.replace("&6", "" + ChatColor.GOLD);
		newString = newString.replace("&7", "" + ChatColor.GRAY);
		newString = newString.replace("&8", "" + ChatColor.DARK_GRAY);
		newString = newString.replace("&9", "" + ChatColor.BLUE);
		newString = newString.replace("&a", "" + ChatColor.GREEN);
		newString = newString.replace("&b", "" + ChatColor.AQUA);
		newString = newString.replace("&c", "" + ChatColor.RED);
		newString = newString.replace("&d", "" + ChatColor.LIGHT_PURPLE);
		newString = newString.replace("&e", "" + ChatColor.YELLOW);
		newString = newString.replace("&f", "" + ChatColor.WHITE);
		
		newString = newString.replace("&k", "" + ChatColor.MAGIC);
		newString = newString.replace("&l", "" + ChatColor.BOLD);
		newString = newString.replace("&m", "" + ChatColor.STRIKETHROUGH);
		newString = newString.replace("&n", "" + ChatColor.UNDERLINE);
		newString = newString.replace("&o", "" + ChatColor.ITALIC);
		newString = newString.replace("&p", "" + ChatColor.RESET);
		
		
		return newString;
	}
	public static List<String> formatString(List<String> list)
	{
		for(int i = 0 ; i < list.size(); i++)
		{
			list.set(i, formatString(list.get(i)));
		}
		return list;
	}
	
	public static String userFriendlyLocation(Location loc)
	{
		String str = "";
		str = str + "X: " + loc.getBlockX() + " ";
		str = str + "Y: " + loc.getBlockY() + " ";
		str = str + "Z: " + loc.getBlockZ();
		
		return str;
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
