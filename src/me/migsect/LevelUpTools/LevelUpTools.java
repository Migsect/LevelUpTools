package me.migsect.LevelUpTools;

import java.util.logging.Logger;

import me.migsect.LevelUpTools.Menu.MenuHandler;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class LevelUpTools extends JavaPlugin
{
	
	private MenuHandler menu_handler = new MenuHandler();
	public final Logger logger = Logger.getLogger("Minecraft");
	
	
	@Override
	public void onEnable()
	{
	  // Server Log Message
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info(pdf.getName() + " Version " + pdf.getVersion() + " has been enabled.");

		// Config Handling
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	@Override
	public void onDisable()
	{
		// Server Log Message
		PluginDescriptionFile pdf = this.getDescription();
		this.logger.info(pdf.getName() + " has been disabled");
	}
	
	public MenuHandler getMenuHandler(){ return this.menu_handler;}
}
