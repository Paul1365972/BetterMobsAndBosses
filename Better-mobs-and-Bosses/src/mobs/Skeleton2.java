package mobs;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.inventory.ItemStack;

public class Skeleton2 {
	public static UUID spawn(Entity e) {
		ItemStack itemdrop = new ItemStack(Material.EXP_BOTTLE);
		Skeleton mob = (Skeleton) e.getWorld().spawnEntity(e.getLocation(),EntityType.SKELETON);
		mob.getEquipment().setHelmet(itemdrop);
		mob.setCustomName("§4Wither Skeleton");
		ItemStack item2 = new ItemStack(Material.BOW);
		item2.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
		item2.addEnchantment(Enchantment.ARROW_FIRE, 1);
		item2.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		mob.getEquipment().setItemInHand(item2);
		mob.setSkeletonType(SkeletonType.WITHER);
		mob.setMaxHealth(40.0);
		mob.setHealth(40.0);
		return mob.getUniqueId();
	}
}
