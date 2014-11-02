package me.migsect.LevelUpTools.Listeners;

import me.migsect.LevelUpTools.Menu.MenuHandler;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener
{

	
	@EventHandler
	public void onMenuClick(InventoryClickEvent event)
	{
		if(event.isCancelled()) return;
		Player player = (Player)event.getWhoClicked();
		if(!MenuHandler.hasMenu(player)) return;
		int slot = event.getSlot();
		Inventory inventory = event.getInventory();
		
		if(!MenuHandler.isPlayersMenu(player, inventory)) return;
		MenuHandler.onClick(player, slot);
		event.setCancelled(true);
		
	}
	@EventHandler
	public void onMenuClose(InventoryCloseEvent event)
	{
		Player player = (Player)event.getPlayer();
		if(!MenuHandler.hasMenu(player)) return;
		Inventory inventory = event.getInventory();

		if(!MenuHandler.isPlayersMenu(player, inventory)) return;
		MenuHandler.removeMenu(player);
	}
}
