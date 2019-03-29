package mobs;

import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class Creeper2 {
	public static UUID spawn(Entity e) {
		ItemStack itemdrop = new ItemStack(Material.EXP_BOTTLE);
		Creeper mob = (Creeper) e.getWorld().spawnEntity(e.getLocation(), EntityType.CREEPER);
		mob.getEquipment().setHelmet(itemdrop);
		mob.setCustomName("§4Gravity Creeper");
		mob.setMaxHealth(40.0);
		mob.setHealth(40.0);
		return mob.getUniqueId();
	}
}
