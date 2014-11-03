package me.migsect.LevelUpTools.Listeners;

import me.migsect.LevelUpTools.Helper;
import me.migsect.LevelUpTools.Tools.DataManager;
import me.migsect.LevelUpTools.Tools.ItemInfo;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RepairListener implements Listener
{
	@EventHandler
	public void onAnvilRightClick(PlayerInteractEvent event)
	{
		if(event.isCancelled()) return;
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = event.getItem();
		
		if(item == null) return;
		ItemInfo info = new ItemInfo(item);

		// our requirement for table clicking is that the player needs to be RCing the table as well as sneaking.
		if(!Helper.isTool(item.getType())) return;
		if(!player.isSneaking() || !action.equals(Action.RIGHT_CLICK_BLOCK)) return;
		Block block = event.getClickedBlock();
		if(!block.getType().equals(Material.ANVIL)) return;
		
		if(!DataManager.canBeLeveled(Helper.getToolType(item.getType()))) return;
		
		if(info.isLeveled())
		{
			Inventory inventory = player.getInventory();
			double exp_repair_ratio = DataManager.getRepairRatio(Helper.getRawMaterial(item.getType()));
			Material raw_material = Helper.getRawMaterial(item.getType()).getTrueMaterial();
			int material_amount = Helper.getToolType(item.getType()).getMaterialAmount();
			
			if(item.getDurability() == 0) return;
			
			if(!inventory.contains(raw_material)) return;
			
			// decrememnting the item,
			int slot_at = inventory.first(raw_material);
			ItemStack mat_item = inventory.getItem(slot_at);
			if(mat_item.getAmount() == 1) inventory.clear(slot_at); 
			else mat_item.setAmount(mat_item.getAmount() - 1);
			
			// Note some theory on durability:
			//   Durability counts UP
			//   Durability of 0 remaining breaks on next.
			short durability = item.getDurability(); // durability is a count of how many times the items been used
			// short max_durability = item.getType().getMaxDurability(); // When durability surpasses this, it breaks.
			// Repairing the item will not add hard values to the item, it will remove a percentage of that items
			//   damage based on the amount of material.
			//   3 / (mat + 3)
			//     1 item - 75%
			//     2 item - 60%
			//     3 item - 50%
			double repair_percent = 3 / ((double)material_amount + 3);
			// player.sendMessage("Current Dur: " + durability + " - Max Dur: " + max_durability + " - Repair Ratio: " + repair_percent);
			// player.sendMessage("Material Amount: " + material_amount);
			item.setDurability((short)(durability * (1-repair_percent)));
			
			info.addExperience(-(int)(info.getExperience() * exp_repair_ratio));
			
			// Playing the sound:
			player.playSound(block.getLocation(), Sound.ANVIL_USE, 1, 1);
		}
	}
}
