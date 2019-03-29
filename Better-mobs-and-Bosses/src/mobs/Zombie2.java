package mobs;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Zombie2 {
	public static UUID spawn(Entity e) {
		ItemStack itemdrop = new ItemStack(Material.EXP_BOTTLE);
		ItemStack item = new ItemStack(Material.DEAD_BUSH);
		Zombie mob = (Zombie) e.getWorld().spawnEntity(e.getLocation(), EntityType.ZOMBIE);
		mob.getEquipment().setHelmet(itemdrop);
		item.setType(Material.LEATHER_CHESTPLATE);
		mob.getEquipment().setChestplate(item);
		item.setType(Material.LEATHER_LEGGINGS);
		mob.getEquipment().setLeggings(item);
		item.setType(Material.LEATHER_BOOTS);
		mob.getEquipment().setBoots(item);
		mob.getEquipment().setItemInHandDropChance(1.0F);
		mob.setMaxHealth(30.0);
		mob.setHealth(30.0);
		mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 2));
		mob.setCustomName("§4Thief Zombie");
		return mob.getUniqueId();
	}
}
