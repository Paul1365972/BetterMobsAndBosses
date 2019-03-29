package bosses;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.paul1365972.Main;

public class BossHoly {
	static Random mobrdm = new Random();
	public static Entity spawn(PlayerInteractEvent e) {
		if (Main.holy == null) {
			e.getClickedBlock().getWorld().getBlockAt(e.getClickedBlock().getLocation()).setType(Material.AIR);
			e.getClickedBlock().getWorld().getBlockAt(e.getClickedBlock().getLocation().add(0, -1, 0)).setType(Material.AIR);
			
			Blaze chick = (Blaze) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5, -1, 0.5), EntityType.BLAZE);
			
			final Skeleton boss = (Skeleton) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5, -1, 0.5), EntityType.SKELETON);
			boss.setNoDamageTicks(7*20); boss.setMaxHealth(150); boss.setHealth(150);
			
			ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short) 3); SkullMeta sm = (SkullMeta) head.getItemMeta(); sm.setOwner("MHF_Ghast"); head.setItemMeta(sm); boss.getEquipment().setHelmet(head);
			boss.getEquipment().setLeggings(new ItemStack(Material.AIR));
			ItemStack is = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
			im.setColor(Color.WHITE);
			is.setItemMeta(im);
			boss.getEquipment().setChestplate(new ItemStack(is));
			boss.getEquipment().setBoots(new ItemStack(Material.AIR));
			boss.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20000000, 0));
			boss.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20000000, 0));
			
			chick.setPassenger(boss); chick.setVelocity(new Vector(0, 2.0, 0));
			chick.setMaxHealth(400); chick.setHealth(400);
			chick.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20000000, 0));
			Main.holy = chick;
			Main.bossid.put(chick.getUniqueId(), 12);
			return boss;
		}else{
			e.getPlayer().sendMessage("§4One HolySkeleton is already spanwed at: " + Main.holy.getLocation().getBlockX() +" "+ Main.holy.getLocation().getBlockY() +" "+Main.holy.getLocation().getBlockZ() + " in the World " + Main.holy.getLocation().getWorld().getName());
			return null;
		}
	}
	public static void repeat(Skeleton boss) {
		if (Main.fly == true && mobrdm.nextBoolean()) {
			Main.fly = false;
			if (boss.getTarget() != null) {
				boss.getTarget().getWorld().getBlockAt(boss.getTarget().getLocation().add(0, -1, 0)).setType(Material.WOOL);
				boss.getTarget().getWorld().getBlockAt(boss.getTarget().getLocation().add(1, -1, 0)).setType(Material.WOOL);
				boss.getTarget().getWorld().getBlockAt(boss.getTarget().getLocation().add(-1, -1, 0)).setType(Material.WOOL);
				boss.getTarget().getWorld().getBlockAt(boss.getTarget().getLocation().add(0, -1, 1)).setType(Material.WOOL);
				boss.getTarget().getWorld().getBlockAt(boss.getTarget().getLocation().add(0, -1, -1)).setType(Material.WOOL);
			}
		}
		if (Main.schutz > 0) {
			Main.schutz --;
			//particle
		}
		if (mobrdm.nextInt(20)+1 == 20) {
			if (boss.getTarget() != null) {
				boss.getWorld().strikeLightning(boss.getTarget().getLocation());
			}
		}
		if (mobrdm.nextInt(30)+1 == 30) {
			Main.schutz = 10;
			//particle
		}
		if (boss.getHealth() < 40 && Main.bossid.get(boss.getUniqueId()) == 10) {
			Main.bossid.put(boss.getUniqueId(), 11);
			for (Entity e : boss.getNearbyEntities(30, 30, 30)) {
				for (int i = 0; i < 4; i++) {
					Snowball ps = boss.launchProjectile(Snowball.class, new Vector(boss.getLocation().getX() - e.getLocation().getX(), boss.getLocation().getY() - e.getLocation().getY(), boss.getLocation().getZ() - e.getLocation().getZ()).multiply((mobrdm.nextInt(50)+51)/100*-0.5));
					ps.setBounce(true);
					ps.setShooter(boss);
				}
			}
		}
		if (boss.getTarget() != null) {
			if (boss.getTarget().getLocation().distance(boss.getLocation()) > 15 && mobrdm.nextInt(10)+1 == 10) {
				if (boss.getTarget() instanceof Player) {
					((Player) boss.getTarget()).sendMessage("§4[HolySkeleton] §rCome back or Fly!");
				}
				boss.getTarget().setVelocity(new Vector(0, 1.2, 0));
				Main.fly = true; 
			}
		}
	}
}
