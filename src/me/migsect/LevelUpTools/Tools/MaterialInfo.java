package me.migsect.LevelUpTools.Tools;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;

public class MaterialInfo
{
	private short durability = 0;
	private Material material = null;
	
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
	public String toString()
	{
		return material.toString() + ":" + durability;
	}
	public short getDurability()
	{
		return durability;
	}
	public Material getMaterial()
	{
		return material;
	}
}
