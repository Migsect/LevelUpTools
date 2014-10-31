package me.migsect.LevelUpTools;

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
		SPADE
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
}
