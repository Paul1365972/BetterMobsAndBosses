package bosses;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import de.paul1365972.Main;

public class BossEnderzombie {
	static Random mobrdm = new Random();
	public static Entity spawn(PlayerInteractEvent e) {
		e.getClickedBlock().getWorld().getBlockAt(e.getClickedBlock().getLocation()).setType(Material.AIR);
		e.getClickedBlock().getWorld().getBlockAt(e.getClickedBlock().getLocation().add(0, -1, 0)).setType(Material.AIR);
		
		Chicken chick = (Chicken) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5, -1, 0.5), EntityType.CHICKEN);
		
		final Zombie boss = (Zombie) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation().add(0.5, -1, 0.5), EntityType.ZOMBIE);
		boss.setNoDamageTicks(7*20); boss.setMaxHealth(40); boss.setHealth(40); boss.setBaby(false); boss.setVillager(false);
		
		ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short) 3); SkullMeta sm = (SkullMeta) head.getItemMeta(); sm.setOwner("MHF_Enderman"); head.setItemMeta(sm); boss.getEquipment().setHelmet(head);
		boss.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
		boss.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		ItemStack is = new ItemStack(Material.DIAMOND_BOOTS);
		is.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
		boss.getEquipment().setBoots(is);
		boss.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20000000, 0));
		boss.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20000000, 4));
		boss.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 5));
		boss.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20000000, 3));
		
		chick.setPassenger(boss); chick.setVelocity(new Vector(0, 2.0, 0));
		chick.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 0)); chick.setMaxHealth(5); chick.setHealth(5);
		chick.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 400, 0));
		return boss;
	}
	public static void repeat(Zombie boss) {
		int i = 0;
		if (boss.getHealth() != 0 && boss.getEquipment().getItemInHand().isSimilar(new ItemStack(Material.POTION,1,(short) 8268))) {
			boss.setHealth(boss.getHealth() +3);
		}
		boss.getEquipment().setItemInHand(new ItemStack(Material.AIR));
		if (boss.getHealth() < 8&& mobrdm.nextBoolean()) {
			i=1;
			Main.spawnable2 = false;
			for (int j = 0; j < 4; j++) {
				Zombie minion = (Zombie)boss.getWorld().spawnEntity(boss.getLocation(), EntityType.ZOMBIE);
				minion.setBaby(true);
				minion.setVillager(false);
				minion.setHealth(4);
				minion.setTarget(boss.getTarget());
				minion.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20000, 1));
				minion.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000, 2));
			}
			boss.setNoDamageTicks(5);
		}
		if (boss.getWorld().getBlockAt(boss.getLocation()).isLiquid() && boss.getTarget() != null) {
			boss.getWorld().strikeLightning(boss.getTarget().getLocation());
		}
		if (boss.getHealth() < 20 && boss.getHealth() > 15 && Main.bossid.get(boss.getUniqueId()) == 1) {
			boss.setHealth(boss.getHealth() + 5);
			Main.bossid.put(boss.getUniqueId(), 2);
			Main.spawnable2 = false;
			boss.getEquipment().getChestplate().addEnchantment(Enchantment.THORNS, 1);
			for (Player p : boss.getWorld().getPlayers()) {
				p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 2));
				p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 2));
			}
			for (int j = 0; j < 8; j++) {
				Zombie boss2 = (Zombie)boss.getWorld().spawnEntity(boss.getLocation(), EntityType.ZOMBIE);
				boss2.setMaxHealth(1); boss2.setHealth(1); boss2.setBaby(false); boss2.setVillager(false);
				ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short) 3); SkullMeta sm = (SkullMeta) head.getItemMeta(); sm.setOwner("MHF_Enderman"); head.setItemMeta(sm); boss2.getEquipment().setHelmet(head);
				boss2.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
				boss2.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				ItemStack is = new ItemStack(Material.DIAMOND_BOOTS);
				is.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
				boss2.getEquipment().setBoots(is);
				boss2.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20000000, 0));
				boss2.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 5));
				boss2.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20000000, 3));
				boss2.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20000000, 1));
				boss2.getEquipment().setBootsDropChance(0);
				boss2.getEquipment().setLeggingsDropChance(0);
				boss2.getEquipment().setChestplateDropChance(0);
			}
			boss.setNoDamageTicks(40);
		}
		if (i == 1) {
			boss.getEquipment().setItemInHand(new ItemStack(Material.POTION,1,(short) 8268));
			i =0;
			boss.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 30, 10));
		}
		
		if (boss.getTarget() != null) {
			Main.tele = true;
			Main.fireball =true;
			for (Entity ent : boss.getNearbyEntities(15, 5, 15)) {
				if (boss.getTarget() == ent) {
					Main.tele = false;
				}
			}
			
			for (Entity ent : boss.getNearbyEntities(15, 2, 15)) {
				if (boss.getTarget() == ent) {
					Main.fireball = false;
				}
			}
			if (Main.tele) {
				boss.teleport(boss.getTarget().getLocation());
			}
			if (Main.fireball) {
				double x = boss.getLocation().getX() - boss.getTarget().getLocation().getX();
				double y = boss.getLocation().getY() - boss.getTarget().getLocation().getY();
				double z = boss.getLocation().getZ() - boss.getTarget().getLocation().getZ();
				boss.setNoDamageTicks(10);
				boss.launchProjectile(Fireball.class, new Vector(-x, -y, -z).multiply(0.05));
			}
			
		}
	}
}
