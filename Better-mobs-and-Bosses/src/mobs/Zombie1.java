package mobs;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Zombie1 {
	public static UUID spawn(Entity e) {
		ItemStack item = new ItemStack(Material.DEAD_BUSH);
		ItemStack itemdrop = new ItemStack(Material.EXP_BOTTLE);
		Zombie mob = (Zombie) e.getWorld().spawnEntity(e.getLocation(), EntityType.ZOMBIE);
		mob.getEquipment().setHelmet(itemdrop);
		item.setType(Material.CHAINMAIL_CHESTPLATE);
		mob.getEquipment().setChestplate(item);
		item.setType(Material.CHAINMAIL_LEGGINGS);
		mob.getEquipment().setLeggings(item);
		item.setType(Material.CHAINMAIL_BOOTS);
		mob.getEquipment().setBoots(item);
		item.setType(Material.STONE_PICKAXE);
		mob.getEquipment().setItemInHand(item);
		mob.setMaxHealth(30.0);
		mob.setHealth(30.0);
		mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 2));
		mob.setCustomName("§cMiner Zombie");
		return mob.getUniqueId();
	}
}
