package mobs;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Zombie3 {
	public static UUID spawn(Entity e) {
		ItemStack itemdrop = new ItemStack(Material.EXP_BOTTLE);
		ItemStack item = new ItemStack(Material.DEAD_BUSH);
		Zombie mob = (Zombie) e.getWorld().spawnEntity(e.getLocation(), EntityType.ZOMBIE);
		mob.getEquipment().setHelmet(itemdrop);
		item.setType(Material.IRON_CHESTPLATE);
		mob.getEquipment().setChestplate(item);
		item.setType(Material.IRON_LEGGINGS);
		mob.getEquipment().setLeggings(item);
		item.setType(Material.IRON_BOOTS);
		mob.getEquipment().setBoots(item);
		mob.getEquipment().setItemInHandDropChance(1.0F);
		mob.setMaxHealth(30.0);
		mob.setHealth(30.0);
		mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 1));
		mob.setCustomName("§6Throw Zombie");
		return mob.getUniqueId();
	}
}
