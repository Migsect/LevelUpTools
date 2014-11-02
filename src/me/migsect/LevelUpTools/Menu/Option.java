package me.migsect.LevelUpTools.Menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class Option
{
	protected String display_name = "BLANK";
	protected Material display_mat = Material.GRASS;
	protected short display_durability = 0;
	protected int display_amount = 1;
	protected List<String> display_lore = new ArrayList<String>();
	
	public ItemStack generateDisplayItem()
	{
		ItemStack item = new ItemStack(display_mat, display_amount, display_durability);
		ItemMeta im = item.getItemMeta();
		im.setDisplayName(display_name);
		im.setLore(display_lore);
		item.setItemMeta(im);
		
		return item;
	}
	public void setDisplayName(String str)
	{
		this.display_name = str;
	}
	public void setDisplayMaterial(Material mat)
	{
		this.display_mat = mat;
	}
	public void setDisplayDurability(short dur)
	{
		this.display_durability = dur;
	}
	public void setDisplayAmount(int amnt)
	{
		this.display_amount = amnt;
	}
	public void setDisplayLore(List<String> lore)
	{
		this.display_lore = lore;
	}
	public void addDisplayLore(String lore_line)
	{
		this.display_lore.add(lore_line);
	}
	public void addDisplayLore(List<String> lore_lines)
	{
		this.display_lore.addAll(lore_lines);
	}
	
	abstract public void onClick(Player player);
}
