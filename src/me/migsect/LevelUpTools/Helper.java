package me.migsect.LevelUpTools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
	
	public static ToolType getToolType(Material mat)
	{
		if(isSpade(mat)) return ToolType.SPADE;
		if(isHoe(mat)) return ToolType.HOE;
		if(isAxe(mat)) return ToolType.AXE;
		if(isPickaxe(mat)) return ToolType.PICKAXE;
		if(isSword(mat)) return ToolType.SWORD;
		return null;
	}
	
	public enum ToolType
	{
		SWORD,
		HOE,
		AXE,
		PICKAXE,
		SPADE,
		BOW,
		FISHING_ROD,
		SHEAR;
		
		
		public static ToolType stringToToolType(String str)
		{
			if(str.equalsIgnoreCase("SWORD")) return ToolType.SWORD;
			if(str.equalsIgnoreCase("HOE")) return ToolType.HOE;
			if(str.equalsIgnoreCase("AXE")) return ToolType.AXE;
			if(str.equalsIgnoreCase("PICKAXE")) return ToolType.PICKAXE;
			if(str.equalsIgnoreCase("SPADE")) return ToolType.SPADE;
			if(str.equalsIgnoreCase("BOW")) return ToolType.BOW;
			if(str.equalsIgnoreCase("FISHING_ROD")) return ToolType.FISHING_ROD;
			if(str.equalsIgnoreCase("SHEAR")) return ToolType.SHEAR;
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
	
	public enum RawMaterial
	{
		WOOD (Material.WOOD),
		LEATHER (Material.LEATHER),
		STONE (Material.COBBLESTONE),
		IRON (Material.IRON_INGOT),
		GOLD (Material.GOLD_INGOT),
		DIAMOND (Material.DIAMOND);
		
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
			if(str.equalsIgnoreCase("WOOD")) return RawMaterial.WOOD;
			if(str.equalsIgnoreCase("LEATHER")) return RawMaterial.LEATHER;
			if(str.equalsIgnoreCase("STONE")) return RawMaterial.STONE;
			if(str.equalsIgnoreCase("GOLD")) return RawMaterial.GOLD;
			if(str.equalsIgnoreCase("IRON")) return RawMaterial.IRON;
			if(str.equalsIgnoreCase("DIAMOND")) return RawMaterial.DIAMOND;	
			return null;
		}
	}
	
	// returns a list of item stacks of 1 item of the type determined from the string.
	//  The reason we are return multiple is because some items have multiple variations. (such as a log)
	public static List<ItemStack> getSimpleItemStack(String str)
	{
		List<ItemStack> return_list = new ArrayList<ItemStack>();
		if(str.equals("OAK_LOG"))
		{
			ItemStack item1 = new ItemStack(Material.LOG, 1, (short)0);
			ItemStack item2 = new ItemStack(Material.LOG, 1, (short)4);
			ItemStack item3 = new ItemStack(Material.LOG, 1, (short)8);
			ItemStack item4 = new ItemStack(Material.LOG, 1, (short)12);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
		}
		else if(str.equals("SPRUCE_LOG"))
		{
			ItemStack item1 = new ItemStack(Material.LOG, 1, (short)1);
			ItemStack item2 = new ItemStack(Material.LOG, 1, (short)5);
			ItemStack item3 = new ItemStack(Material.LOG, 1, (short)9);
			ItemStack item4 = new ItemStack(Material.LOG, 1, (short)13);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
		}
		else if(str.equals("BIRCH_LOG"))
		{
			ItemStack item1 = new ItemStack(Material.LOG, 1, (short)2);
			ItemStack item2 = new ItemStack(Material.LOG, 1, (short)6);
			ItemStack item3 = new ItemStack(Material.LOG, 1, (short)10);
			ItemStack item4 = new ItemStack(Material.LOG, 1, (short)14);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
			
		}
		else if(str.equals("JUNGLE_LOG"))
		{
			ItemStack item1 = new ItemStack(Material.LOG, 1, (short)3);
			ItemStack item2 = new ItemStack(Material.LOG, 1, (short)7);
			ItemStack item3 = new ItemStack(Material.LOG, 1, (short)11);
			ItemStack item4 = new ItemStack(Material.LOG, 1, (short)15);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
			
		}
		else if(str.equals("ACACIA_LOG"))
		{
			ItemStack item1 = new ItemStack(Material.LOG_2, 1, (short)0);
			ItemStack item2 = new ItemStack(Material.LOG_2, 1, (short)4);
			ItemStack item3 = new ItemStack(Material.LOG_2, 1, (short)8);
			ItemStack item4 = new ItemStack(Material.LOG_2, 1, (short)12);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
			
		}
		else if(str.equals("DARK_OAK_LOG"))
		{
			ItemStack item1 = new ItemStack(Material.LOG_2, 1, (short)1);
			ItemStack item2 = new ItemStack(Material.LOG_2, 1, (short)5);
			ItemStack item3 = new ItemStack(Material.LOG_2, 1, (short)9);
			ItemStack item4 = new ItemStack(Material.LOG_2, 1, (short)13);
			return_list.add(item1);
			return_list.add(item2);
			return_list.add(item3);
			return_list.add(item4);
			
		}
		else
		{
			Material mat = Material.getMaterial(str);
			if(mat.equals(null)) return return_list;
			ItemStack item = new ItemStack(mat, 1);
			return_list.add(item);
		}
		
		return return_list;
	}
}
