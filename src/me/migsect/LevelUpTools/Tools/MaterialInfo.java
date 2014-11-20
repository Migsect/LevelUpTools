package me.migsect.LevelUpTools.Tools;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;

public class MaterialInfo
{
	private short durability = 0;
	private Material material = null;
	
	// CONSTRUCTORS
	public MaterialInfo(Block block)
	{
		material = block.getType();
		BlockState state = block.getState();
		durability = state.getData().toItemStack().getDurability();
	}
	public MaterialInfo(ItemStack item)
	{
		material = item.getType();
		durability = item.getDurability();
	}
	public MaterialInfo(Material mat)
	{
		this.material = mat;
	}
	public MaterialInfo(Material mat, short durability)
	{
		this.material = mat;
		this.durability  = durability;
	}
	public MaterialInfo(String str)
	{
		String[] split = str.split(":");
		this.material = Material.getMaterial(split[0]);
		if(split.length >= 2)
		{
			try
			{
				this.durability = Short.parseShort(split[1]);
			} 
			catch(NumberFormatException e){;}// Do Nothing
		}
	}
	
	// PUBLIC FUNCTION equals
	public boolean equals(MaterialInfo other)
	{
		if(this.material != other.material) return false;
		if(this.durability != other.durability) return false;
		return true;
	}
	public boolean equals(Block block)
	{
		MaterialInfo other = new MaterialInfo(block);
		return this.equals(other);
	}
	public boolean equals(ItemStack item)
	{
		MaterialInfo other = new MaterialInfo(item);
		return this.equals(other);
	}
	public boolean equals(Material mat)
	{
		MaterialInfo other = new MaterialInfo(mat);
		return this.equals(other);
	}
	
	// PUBLIC getters
	public String toString(){return material.toString() + ":" + durability;}
	public short getDurability(){return durability;}
	public Material getMaterial(){return material;}
	
	// STATIC methods
	static public boolean canConvert(String str)
	{
		String[] split = str.split(":");
		Material mat = Material.getMaterial(split[1]);
		if(mat == null) return false;
		if(split.length >= 2)
		{
			try
			{
				Short.parseShort(split[1]);
			} 
			catch(NumberFormatException e){return false;}// Do Nothing
		}
		return true;
	}
}
