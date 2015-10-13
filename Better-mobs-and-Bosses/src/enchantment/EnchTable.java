package enchantment;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchTable implements Listener{
	Random rdm = new Random();
	//add class
	//add max in AnvilConverter
	//add enchant here
	//add enchant permission in plugin.yml
	//add Event
	
	@EventHandler(ignoreCancelled = true)
	public void enchantevent(EnchantItemEvent e) {
		ArrayList<String> lore = new ArrayList<String>();
		if (e.getItem().isSimilar(new ItemStack(Material.IRON_SWORD)) || 
			e.getItem().isSimilar(new ItemStack(Material.DIAMOND_SWORD)) || 
			e.getItem().isSimilar(new ItemStack(Material.GOLD_SWORD)) || 
			e.getItem().isSimilar(new ItemStack(Material.STONE_SWORD)) || 
			e.getItem().isSimilar(new ItemStack(Material.WOOD_SWORD))) {
			
			lore.clear();
			//set Multi
			double multi;
			if (e.getItem().isSimilar(new ItemStack(Material.GOLD_SWORD))) {
				multi = 1.5;
				e.getEnchanter().sendMessage("golden");
			} else {
				multi = 1;
				e.getEnchanter().sendMessage("normal");
			}
			
			if(e.getEnchantsToAdd().containsKey(Enchantment.DAMAGE_ALL)) {
				multi = multi - (e.getEnchantsToAdd().get(Enchantment.DAMAGE_ALL) * 0.15);
			}
			if(e.getEnchantsToAdd().containsKey(Enchantment.KNOCKBACK)) {
				multi = multi + (e.getEnchantsToAdd().get(Enchantment.KNOCKBACK) * 0.2);
			}
			if(e.getEnchantsToAdd().containsKey(Enchantment.FIRE_ASPECT)) {
				multi = multi - (e.getEnchantsToAdd().get(Enchantment.FIRE_ASPECT) * 0.4);
			}
			if(e.getEnchantsToAdd().containsKey(Enchantment.DAMAGE_UNDEAD)) {
				multi = multi + (e.getEnchantsToAdd().get(Enchantment.DAMAGE_UNDEAD) * 0.15);
			}
			if(e.getEnchantsToAdd().containsKey(Enchantment.DAMAGE_ARTHROPODS)) {
				multi = multi + (e.getEnchantsToAdd().get(Enchantment.DAMAGE_ARTHROPODS) * 0.15);
			}
			if(e.getEnchantsToAdd().containsKey(Enchantment.DURABILITY)) {
				multi = multi + (e.getEnchantsToAdd().get(Enchantment.DURABILITY) * 0.1);
			}
			if(e.getEnchantsToAdd().containsKey(Enchantment.LOOT_BONUS_MOBS)) {
				multi = multi - (e.getEnchantsToAdd().get(Enchantment.LOOT_BONUS_MOBS) * 0.1);
			}
			e.getEnchanter().sendMessage("multi: " + multi);
			
			//runBasicEnch
			if (rdm.nextInt(100)+1 <= 20 *multi && e.getEnchanter().hasPermission("bmab.enchant.blunt")) {
				lore = EnchBlunt.ench(lore);
			}
			if (rdm.nextInt(100)+1 <= 20 *multi && e.getEnchanter().hasPermission("bmab.enchant.heavy")) {
				lore = EnchHeavy.ench(lore);
			}
			if (rdm.nextInt(100)+1 <= 20 *multi && e.getEnchanter().hasPermission("bmab.enchant.haste")) {
				lore = EnchHaste.ench(lore);
			}
			//runEnch1
			if (e.getExpLevelCost() >= 10) {
				if (rdm.nextInt(100)+1 <= 10 *multi  && e.getEnchanter().hasPermission("bmab.enchant.frostric")) {
					lore = EnchFrostric.ench(lore);
				}
				if (rdm.nextInt(100)+1 <= 10 *multi && e.getEnchanter().hasPermission("bmab.enchant.explode")) {
					lore = EnchExplode.ench(lore);
				}
				if (rdm.nextInt(100)+1 <= 10 *multi && e.getEnchanter().hasPermission("bmab.enchant.magic")) {
					lore = EnchMagic.ench(lore);
				}
			}
			if (e.getExpLevelCost() >= 15) {
				if (rdm.nextInt(100)+1 <= 10 && e.getEnchanter().hasPermission("bmab.enchant.counter")) {
					lore = EnchCounter.ench(lore);
				}
				if (rdm.nextInt(100)+1 <= 10 && e.getEnchanter().hasPermission("bmab.enchant.shield")) {
					lore = EnchShield.ench(lore);
				}
				if (rdm.nextInt(100)+1 <= 10 && e.getEnchanter().hasPermission("bmab.enchant.endering")) {
					lore = EnchEnder.ench(lore);
				}
			}
			if (e.getExpLevelCost() >= 20) {
				if (rdm.nextInt(100)+1 <= 10 && e.getEnchanter().hasPermission("bmab.enchant.vampire")) {
					lore = EnchVampire.ench(lore);
				}
			}
			e.getEnchanter().sendMessage(lore.toString());
			lore = EnchConverter.uncombineable(lore, e.getEnchantsToAdd());
			//set
			ItemMeta im = e.getItem().getItemMeta();
			im.setLore(lore);
			e.getItem().setItemMeta(im);
		}
	}
}
