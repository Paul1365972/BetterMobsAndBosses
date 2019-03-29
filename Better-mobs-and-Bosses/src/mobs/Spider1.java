package mobs;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Spider;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Spider1 {
	public static UUID spawn(Entity e) {
		ItemStack itemdrop = new ItemStack(Material.EXP_BOTTLE);
		Spider mob = (Spider) e.getWorld().spawnEntity(e.getLocation(), EntityType.SPIDER);
		mob.getEquipment().setHelmet(itemdrop);
		mob.setCustomName("§cFast Spider");
		mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 2));
		mob.setMaxHealth(20.0);
		mob.setHealth(20.0);
		return mob.getUniqueId();
	}
}
