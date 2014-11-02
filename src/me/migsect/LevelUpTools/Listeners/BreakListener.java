package me.migsect.LevelUpTools.Listeners;

import me.migsect.LevelUpTools.Helper;
import me.migsect.LevelUpTools.Helper.ToolType;
import me.migsect.LevelUpTools.Tools.DataManager;
import me.migsect.LevelUpTools.Tools.ItemInfo;
import me.migsect.LevelUpTools.Tools.MaterialInfo;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakListener implements Listener
{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(event.isCancelled()) return;
		Block block = event.getBlock();
		Player player = event.getPlayer();
		ItemStack item_in_hand = player.getItemInHand();

		//player.sendMessage("0. Passed check");
		if(!Helper.isTool(item_in_hand.getType())) return;
		ItemInfo info = new ItemInfo(item_in_hand);
		if(!info.isLeveled()) return;
		//player.sendMessage("1. Passed check");
		ToolType type = Helper.getToolType(item_in_hand.getType());
		
		MaterialInfo mat_info = new MaterialInfo(block);
		//player.sendMessage("Info: " + mat_info.toString());
		int exp_award = DataManager.getExperienceAward(type, mat_info);
		info.addExperience(exp_award);
		//player.sendMessage("exp_award: " + exp_award);
	}
}
