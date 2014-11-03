package me.migsect.LevelUpTools.Listeners;

import java.util.ArrayList;
import java.util.List;

import me.migsect.LevelUpTools.Helper;
import me.migsect.LevelUpTools.Helper.ToolType;
import me.migsect.LevelUpTools.Tools.DataManager;
import me.migsect.LevelUpTools.Tools.ItemInfo;
import me.migsect.LevelUpTools.Tools.MaterialInfo;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class BreakListener implements Listener
{
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(event.isCancelled()) return;
		if(DataManager.isPlaced(event.getBlock().getLocation()))
		{
			DataManager.removeBlockPlace(event.getBlock().getLocation());
			return;
		}
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		ItemStack item_in_hand = player.getItemInHand();
		if(item_in_hand == null) return;

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
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		Block block = event.getBlock();
		if(!DataManager.hasExperienceAward(new MaterialInfo(block))) return;
		DataManager.addBlockPlace(block.getLocation());
	}
	
	// Piston pushing handling.  We need to ipdate the stuff.
	@EventHandler
	public void onPistonBlockPush(BlockPistonExtendEvent event)
	{
		if(event.isCancelled()) return;
		BlockFace direction = event.getDirection();
		List<Block> blocks_to_be_pushed = event.getBlocks();
		
		List<Location> to_remove = new ArrayList<Location>();
		List<Location> to_add = new ArrayList<Location>();
		for(Block b : blocks_to_be_pushed)
		{
			// DataManager.getPlugin().getLogger().info("Block loc: " + Helper.userFriendlyLocation(b.getLocation()));
			if(DataManager.isPlaced(b.getLocation()))
			{
				to_remove.add(b.getLocation().clone());
				Location new_location = null;
				switch (direction)
				{
					case NORTH:
						new_location = new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ() + 1);
						break;
					case SOUTH:
					  new_location = new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ() - 1);
						break;
					case EAST:
						new_location = new Location(b.getWorld(), b.getLocation().getX() + 1, b.getLocation().getY(), b.getLocation().getZ());
						break;
					case WEST:
						new_location = new Location(b.getWorld(), b.getLocation().getX() - 1, b.getLocation().getY(), b.getLocation().getZ());
						break;
					case UP:
						new_location = new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() + 1, b.getLocation().getZ());
						break;
					case DOWN:
						new_location = new Location(b.getWorld(), b.getLocation().getX(), b.getLocation().getY() - 1, b.getLocation().getZ());
						break;
					default:
						break;
				}
				// DataManager.getPlugin().getLogger().info("  Old Location: " + Helper.userFriendlyLocation(b.getLocation()));
				// DataManager.getPlugin().getLogger().info("  New Location: " + Helper.userFriendlyLocation(new_location));
				to_add.add(new_location);
			}
		}
		for(Location l : to_remove)
		{
			DataManager.removeBlockPlace(l);
		}
		for(Location l : to_add)
		{
			DataManager.addBlockPlace(l);
		}
	}
	
	@EventHandler
	public void onPistonBlockPull(BlockPistonRetractEvent event)
	{
		if(event.isCancelled()) return;
		if(!event.isSticky()) return;
		Location loc_old = event.getRetractLocation();
		Location loc_new = null;
		switch(event.getDirection())
		{	
			case NORTH:
				loc_new = new Location(loc_old.getWorld(), loc_old.getX(), loc_old.getY(), loc_old.getZ() - 1);
				break;
			case SOUTH:
				loc_new = new Location(loc_old.getWorld(), loc_old.getX(), loc_old.getY(), loc_old.getZ() + 1);
				break;
			case EAST:
				loc_new = new Location(loc_old.getWorld(), loc_old.getX() + 1, loc_old.getY(), loc_old.getZ());
				break;
			case WEST:
				loc_new = new Location(loc_old.getWorld(), loc_old.getX() - 1, loc_old.getY() , loc_old.getZ());
				break;
			case UP:
				loc_new = new Location(loc_old.getWorld(), loc_old.getX(), loc_old.getY() - 1, loc_old.getZ());
				break;
			case DOWN:
				loc_new = new Location(loc_old.getWorld(), loc_old.getX(), loc_old.getY() + 1, loc_old.getZ());
				break;
			default:
				break;
		}
		// DataManager.getPlugin().getLogger().info("  Old Location: " + Helper.userFriendlyLocation(loc_old));
		// DataManager.getPlugin().getLogger().info("  New Location: " + Helper.userFriendlyLocation(loc_new));
		BukkitRunnable new_task = new CheckAfterRetractTask(loc_old, loc_new);
		new_task.runTaskLater(DataManager.getPlugin(), 3);
	}
	
	public class CheckAfterRetractTask extends BukkitRunnable
	{
		private Location loc_old;
		private Location loc_new;
		
		private CheckAfterRetractTask(Location loc_old, Location loc_new)
		{
			this.loc_old = loc_old;
			this.loc_new = loc_new;
		}
		
		@Override
		public void run()
		{
			// DataManager.getPlugin().getLogger().info("  .Old Location: " + Helper.userFriendlyLocation(loc_old));
			// DataManager.getPlugin().getLogger().info("  .New Location: " + Helper.userFriendlyLocation(loc_new));
			// DataManager.getPlugin().getLogger().info(loc_old.getBlock().getType().equals(Material.AIR) + " : " + DataManager.isPlaced(loc_old));
			if(DataManager.isPlaced(loc_old) && loc_old.getBlock().getType() == Material.AIR)
			{
				// DataManager.getPlugin().getLogger().info("I show if the conditional is true!");
				DataManager.removeBlockPlace(loc_old);
				DataManager.addBlockPlace(loc_new);
			}
		}
		
	}
}
