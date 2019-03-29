package mobs;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;


public class Skeleton3 {
	public static UUID spawn(Entity e) {
		ItemStack itemdrop = new ItemStack(Material.EXP_BOTTLE);
		ItemStack item = new ItemStack(Material.DEAD_BUSH);
		Skeleton mob = (Skeleton) e.getWorld().spawnEntity(e.getLocation(),EntityType.SKELETON);
		mob.getEquipment().setHelmet(itemdrop);
		mob.setCustomName("§6Multi-Shot Skeleton");
		item.setType(Material.IRON_CHESTPLATE);
		mob.getEquipment().setChestplate(item);
		item.setType(Material.IRON_LEGGINGS);
		mob.getEquipment().setLeggings(item);
		item.setType(Material.IRON_BOOTS);
		mob.getEquipment().setBoots(item);
		ItemStack item2 = new ItemStack(Material.BOW);
		item2.addEnchantment(Enchantment.ARROW_FIRE, 1);
		item2.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		mob.getEquipment().setItemInHand(item2);
		mob.setMaximumNoDamageTicks(60);
		mob.setHealth(15.0);
		return mob.getUniqueId();
	}
}
