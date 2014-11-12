package me.migsect.LevelUpTools.Tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


/* CLASS ItemEnchantment
 * Purpose:  To easily store information about enchantments/custom-enchantments
 *   
 */
public class ItemEnchantment
{	
	
	// Descriptor information
	private String raw_name = "NULL";
	private boolean is_custom = false;
	
	// Display Information
	private String display_name = "default";
	private Material display_material = Material.GRASS;
	private short display_durability = 0;
	private int display_amount = 1;
	private List<String> display_lore = new ArrayList<String>();
	
	// Function Information
	private List<String> incompat = new ArrayList<String>();
	private HashMap<ItemType, HashMap<String, Integer>> dependencies = new HashMap<ItemType, HashMap<String, Integer>>();
	private int base_cost = 0;
	private int max_level = 0;
	private double cost_increase = 0.0;
	
	public ItemEnchantment(Enchantment ench)
	{
		this.raw_name = ench.toString();
		this.is_custom = false;
	}
	public ItemEnchantment(String ench)
	{
		this.raw_name = ench;
		this.is_custom = true;
	}
	public ItemEnchantment(String raw_name, boolean is_custom)
	{
		this.raw_name = raw_name;
		this.is_custom = is_custom;
	}
	public void setDisplayName(String name){display_name = name;}
	public void setDisplayMaterial(Material mat){display_material = mat;}
	public void setDisplayDurability(short dura){display_durability = dura;}
	public void setDisplayAmount(int amount){display_amount = amount;}
	public void setDisplayLore(List<String> lore){display_lore = lore;}
	
	public void setIncompatabilities(List<String> incompat){this.incompat = incompat;}
	public void setBaseCost(int base){base_cost = base;}
	public void setMaxLevel(int max){max_level = max;}
	public void setCostIncrease(double increase){cost_increase = increase;}
	
	public ItemStack displayItem()
	{
		ItemStack item = new ItemStack(display_material, display_amount, display_durability);
		ItemMeta im = item.getItemMeta();
		im.setLore(display_lore);
		im.setDisplayName(display_name);
		item.setItemMeta(im);
		return item;
	}
	public String getDisplayName(){return display_name;}
	
	public int getBaseCost(){return base_cost;}
	public int getMaxLevel(){return max_level;}
	public double getCostIncrease(){return cost_increase;}
	public List<String> getIncompatable(){return incompat;}
	public boolean isIncompatable(String enchant){return incompat.contains(enchant);} // Note, incompatabilities can be one-way
	
	public boolean isVanillaEnchantment(){return !is_custom;}
	public boolean isCustomEnchantment(){return is_custom;}
	public Enchantment getVanillaEnchantment(){return Enchantment.getByName(raw_name);}
	public String getRawName(){return raw_name;}
	
}
