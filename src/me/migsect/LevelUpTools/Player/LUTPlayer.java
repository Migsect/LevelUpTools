package me.migsect.LevelUpTools.Player;

import org.bukkit.entity.Player;

public class LUTPlayer {
	
	private Player player;
	
	public LUTPlayer(Player player)
	{
		this.player = player;
	}
	
	public Player getPlayer()
	{
		return player;
	}
}
