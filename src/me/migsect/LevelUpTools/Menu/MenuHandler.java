package me.migsect.LevelUpTools.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MenuHandler
{
	// For quick lookup a Player is used to locate a menu.  A player can only
	//   have one active menu at a time due to this.  We may allow more than one
	//   active menu in the future, but the purpose of this is to 
	public HashMap<Player, Menu> active_menus = new HashMap<Player, Menu>();
	
	// register() will add the menu to the list of active_menus.
	public void register(Menu menu)
	{
		active_menus.put(menu.getPlayer().getPlayer(), menu);
	}
	
	// deregister() will remove the menu from the registery of active_menus
	//   a menu should be deregistered whenever it shouldn't be used anymore.
	//   this is for clean up reasons.
	public void deregister(Menu menu)
	{
		deregister(menu.getPlayer().getPlayer());
	}
	public void deregister(Player player)
	{
		active_menus.remove(player);
	}
	
	// getMenu() returns the menu for a given player that is currently active at the time.
	//   it is good practive to check if the menu is active before trying any of these.
	public Menu getMenu(Player player){return active_menus.get(player);}
	public Menu getMenu(Inventory inv)
	{
		List<Menu> menus = new ArrayList<Menu>();
		menus.addAll(active_menus.values());
		for(int i = 0; i < menus.size(); i++)
		{
			if(menus.get(i).getInventory().equals(inv)) return menus.get(i);
		}
		return null;
	}
	
	// isActive() returns a boolean if a menu is currently active.
	public boolean isActive(Menu menu){return active_menus.containsValue(menu);}
	public boolean isActive(Inventory inv)
	{
		List<Menu> menus = new ArrayList<Menu>();
		menus.addAll(active_menus.values());
		for(int i = 0; i < menus.size(); i++)
		{
			if(menus.get(i).getInventory().equals(inv)) return true;
		}
		return false;
	}
	// hasActiveMenu() returns a boolean if the player has an active menu.
	public boolean hasActiveMenu(Player player){return active_menus.containsKey(player);}
	
}
