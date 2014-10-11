package me.migsect.LevelUpTools;

import me.migsect.LevelUpTools.Menu.MenuHandler;

import org.bukkit.plugin.java.JavaPlugin;

public class LevelUpTools extends JavaPlugin
{
	
	private MenuHandler menu_handler = new MenuHandler();
	
	
	@Override
	public void onEnable()
	{
		
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	public MenuHandler getMenuHandler(){ return this.menu_handler;}
}
