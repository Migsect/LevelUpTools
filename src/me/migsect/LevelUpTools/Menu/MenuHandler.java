package me.migsect.LevelUpTools.Menu;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MenuHandler
{
	private static HashMap<String, Menu> active_menus = new HashMap<String, Menu>();
	
	public static Menu getNewMenu(Player player)
	{
		Menu new_menu = new Menu(player, 27, "Menu");
		active_menus.put(player.getName(), new_menu);
		return new_menu;
	}
	public static Menu getNewMenu(Player player, int size)
	{
		if(size % 9 != 0) size = size / 9 + 9;
		if(size > 54) size = 54;
		if(size < 9) size = 9;
		Menu new_menu = new Menu(player, size, "Menu");
		active_menus.put(player.getName(), new_menu);
		return new_menu;
	}
	public static Menu getNewMenu(Player player, int size, String name)
	{
		if(size % 9 != 0) size = size / 9 + 9;
		if(size > 54) size = 54;
		if(size < 9) size = 9;
		Menu new_menu = new Menu(player, size, name);
		active_menus.put(player.getName(), new_menu);
		return new_menu;
	}
	
	public static boolean hasMenu(Player player)
	{
		return active_menus.containsKey(player.getName());
	}
	public static Menu getMenu(Player player)
	{
		return active_menus.get(player.getName());
	}
	public static void removeMenu(Player player)
	{
		active_menus.remove(active_menus.get(player));
	}
	public static boolean isPlayersMenu(Player player, Inventory inv)
	{
		return getMenu(player).isInventory(inv);
	}
	public static void onClick(Player player, int slot)
	{
		getMenu(player).slotClick(slot);
	}
	
	
	public static class Menu
	{
		private String player_name;
		private Inventory inventory;
		
		HashMap<Integer, Option> options = new HashMap<Integer, Option>();
		String menu_title = "";
		
		private Menu(Player player, int size, String menu_title)
		{
			player_name = player.getName();
			this.menu_title = menu_title;
			inventory = Bukkit.createInventory(null, size, menu_title);
		}
		
		public void addOption(Option opt)
		{
			int slot = 0;
			for(int i = 0; i < inventory.getSize(); i++)
			{
				if(!options.containsKey(i))
				{
					slot = i;
					break;
				}
			}
			options.put(slot, opt);
		}
		
		public void addOption(Option opt, int slot)
		{
			options.put(slot, opt);
		}
		public void removeOptions()
		{
			options.clear();
		}
		
		public Player getPlayer()
		{
			return Bukkit.getPlayer(player_name);
		}
		private boolean isInventory(Inventory inv)
		{
			return this.inventory.equals(inv);
		}
		public void updateInventory()
		{
			inventory.clear();
			for(int i = 0; i < inventory.getSize(); i++)
			{
				if(options.containsKey(i))
				{
					inventory.setItem(i, options.get(i).generateDisplayItem());
				}
			}
		}
		public void openInventory()
		{
			updateInventory();
			Bukkit.getPlayer(player_name).openInventory(this.inventory);
		}
		private void slotClick(int slot)
		{
			if(!options.containsKey(slot)) return;
			options.get(slot).onClick(getPlayer());
		}
		
		
		
	}
}
