package me.migsect.LevelUpTools.Tools;

import java.util.ArrayList;
import java.util.List;

import me.migsect.LevelUpTools.Tools.ItemConfig.ExpAward.BlockAward;
import me.migsect.LevelUpTools.Tools.ItemConfig.ExpAward.DamageAward;
import me.migsect.LevelUpTools.Tools.ItemConfig.ExpAward.MobAward;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

// PUBLIC CLASS ItemConfig 
//   is used to store all the information gathered from the tool type config files in the item-config folder.

public class ItemConfig
{
	// PRIVATE MEMBER enabled
	//   indicates if the item type is enabled.  Otherwise the item-type won't function.
	private boolean enabled = true;
	public boolean isEnabled(){return enabled;}
	
	// PRIVATE MEMBER item_types:
	private List<MaterialInfo> item_types = new ArrayList<MaterialInfo>();
	// These functions essentially checks the object against our list to see if it is in there.
	//   This will be useful for when we need to see what item type an item is.
	public boolean containsType(MaterialInfo info){return item_types.contains(info);}
	public boolean containsType(Block block){return item_types.contains(new MaterialInfo(block));}
	public boolean containsType(Material mat){return item_types.contains(new MaterialInfo(mat));}
	public boolean containsType(ItemStack item){return item_types.contains(new MaterialInfo(item));}
	
	// PRIVATE MEMBER exp_awards:
	//   The reason why it is storing strings is because exp_awards can be multiple things and as such 
	//   it may be easier to do it this way.  However we may have a new ExpAward class to handle the stuff.
	private List<ExpAward> exp_awards = new ArrayList<ExpAward>();
	// Add ExpAward to the list.  Furthermore will return false if the parsing fails.
	public boolean addExpAward(String str, int amount)
	{
		ExpAward new_award = ExpAward.getExpAward(str, amount);
		if(new_award == null) return false;
		exp_awards.add(new_award);
		return true;
	}
	public boolean hasExpAward(AwardType type)
	{
		for(ExpAward e : exp_awards)
		{
			if(e.getAwardType() == type) return true;
		}
		return false;
	}
	public int getExpAward(MaterialInfo info)
	{
		for(ExpAward e : exp_awards)
		{
			if(e.getAwardType() == AwardType.BLOCK_BREAK && ((BlockAward)e).getTarget().equals(info))
			{
				return e.getAward();
			}
		}
		return 0;
	}
	public int getExpAward(DamageCause cause)
	{
		for(ExpAward e : exp_awards)
		{
			if(e.getAwardType() == AwardType.DAMAGE_TAKEN && ((DamageAward)e).getTarget().equals(cause))
			{
				return e.getAward();
			}
		}
		return 0;
	}
	public int getExpAward(EntityType entity, AwardType type)
	{
		for(ExpAward e : exp_awards)
		{
			if((e.getAwardType() == type || e.getAwardType() == AwardType.MOB) && ((MobAward)e).getTarget().equals(entity))
			{
				return e.getAward();
			}
		}
		return 0;
	}

	// ExpAward is a means of tracking what kind of experience shall be recieved.
	public abstract static class ExpAward
	{
		protected int ExpAmount;
		protected AwardType type;
		
		// The major parser in getting the experience award.
		static public ExpAward getExpAward(String str, int amount)
		{
			// First we will attempt to split the first string.  this will allow us two options:
			//   Firstly we will check if the first element is a material.
			String[] split = str.split(":");
			Material mat = Material.getMaterial(split[0]);
			if(mat != null)
			{
				MaterialInfo mat_info = new MaterialInfo(str); // Material info should be able to parse it from here.
				BlockAward b_award = new BlockAward(mat_info, amount);
				b_award.type = AwardType.BLOCK_BREAK;
				return b_award;
			}
			EntityType type = EntityType.valueOf(split[0]);
			if(type != null)
			{
				MobAward m_award = new MobAward(type, amount);
				if(split.length >= 2)
				{
					if(split[1].equals("HIT")) m_award.type = AwardType.MOB_HIT;
					else if(split[1].equals("KILL")) m_award.type = AwardType.MOB_KILL;
					else m_award.type = AwardType.MOB;
				}
				else
				{
					m_award.type = AwardType.MOB;
				}
				return m_award;
			}
			DamageCause cause = DamageCause.valueOf(split[0]);
			if(cause != null)
			{
				DamageAward d_award = new DamageAward(cause, amount);
				d_award.type = AwardType.DAMAGE_TAKEN;
				return d_award;
			}
			return null; //  Returns null other the string is bad.  May want to add a checker method.
		}
		// Returns the amount of experience.
		public int getAward(){return this.ExpAmount;}
		// Returns the type of award, excellent for checking.
		public AwardType getAwardType(){return this.type;}
		
		
		// SubClasses that are returned.  These represent the different award types.
		static public class MobAward extends ExpAward
		{
			private EntityType target;
			private MobAward(EntityType target, int amount)
			{
				this.ExpAmount = amount;
				this.target = target;
			}
			public EntityType getTarget(){return target;}
		}
		static public class BlockAward extends ExpAward
		{
			private MaterialInfo target;
			private BlockAward(MaterialInfo target, int amount)
			{
				this.ExpAmount = amount;
				this.target = target;
			}
			public MaterialInfo getTarget(){return target;}
		}
		static public class DamageAward extends ExpAward
		{
			private DamageCause target;
			private DamageAward(DamageCause target, int amount)
			{
				this.ExpAmount = amount;
				this.target = target;
			}
			public DamageCause getTarget(){return target;}
		}
		
		
	}
	public enum AwardType
	{
		BLOCK_BREAK,
		MOB,
		MOB_HIT,
		MOB_KILL,
		DAMAGE_TAKEN;
	}
}
