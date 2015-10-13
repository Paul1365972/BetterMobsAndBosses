package enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.paul1365972.Main;

public class EnchEffectManager implements Listener{
	
	static List<Entity> heavy = new ArrayList<>();
	static Random rdm = new Random();
	
	public static void startEM() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(Main.class), new Runnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getItemInHand() != null) {
						if (p.getItemInHand().hasItemMeta()) {
							if (p.getItemInHand().getItemMeta().getLore() != null) {
								for (String str : p.getItemInHand().getItemMeta().getLore()) {
									if (str.equalsIgnoreCase("§7Haste I")) {
										p.removePotionEffect(PotionEffectType.FAST_DIGGING);
										p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 0));
									}
									if (str.equalsIgnoreCase("§7Haste II")) {
										p.removePotionEffect(PotionEffectType.FAST_DIGGING);
										p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 1));
									}
									if (str.equalsIgnoreCase("§7Haste III")) {
										p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 40, 2));
										p.removePotionEffect(PotionEffectType.FAST_DIGGING);
									}
									
									if (str.equalsIgnoreCase("§7Heavy I")) {
										p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 0));
									}
									if (str.equalsIgnoreCase("§7Heavy II")) {
										p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 1));
									}
									if (str.equalsIgnoreCase("§7Heavy III")) {
										p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2));
									}
									
								}
							}
						}
					}
				}
			}
		}, 20, 20);
	}
	
	@EventHandler
	public void click(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			final Player p = e.getPlayer();
			if (p.getItemInHand() != null) {
				if (p.getItemInHand().hasItemMeta()) {
					if (p.getItemInHand().getItemMeta().getLore() != null) {
						for (String str : p.getItemInHand().getItemMeta().getLore()) {
							if (str.equalsIgnoreCase("§7Endering I")) {
								final EnderPearl tele = p.launchProjectile(EnderPearl.class);
								tele.setShooter(p);
								tele.setVelocity(tele.getVelocity().multiply(6));
								p.setItemInHand(reduceDurability(  p.getItemInHand()  ));
								Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable() {
									public void run() {
										p.damage(2, tele);
										Location loc = tele.getLocation();
										loc.setPitch(p.getLocation().getPitch());
										loc.setYaw(p.getLocation().getYaw());
										p.teleport(loc);
										tele.remove();
									}
								}, 2);
							}
							if (str.equalsIgnoreCase("§7Endering II")) {
								final EnderPearl tele = p.launchProjectile(EnderPearl.class);
								tele.setShooter(p);
								tele.setVelocity(tele.getVelocity().multiply(9));
								p.setItemInHand(reduceDurability(  p.getItemInHand()  ));
								Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable() {
									public void run() {
										p.damage(2, tele);
										Location loc = tele.getLocation();
										loc.setPitch(p.getLocation().getPitch());
										loc.setYaw(p.getLocation().getYaw());
										p.teleport(loc);
										tele.remove();
										
									}
								}, 2);
							}
							if (str.equalsIgnoreCase("§7Endering III")) {
								final EnderPearl tele = p.launchProjectile(EnderPearl.class);
								tele.setShooter(p);
								tele.setVelocity(tele.getVelocity().multiply(12));
								p.setItemInHand(reduceDurability(  p.getItemInHand()  ));
								Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable() {
									public void run() {
										p.damage(2, tele);
										Location loc = tele.getLocation();
										loc.setPitch(p.getLocation().getPitch());
										loc.setYaw(p.getLocation().getYaw());
										p.teleport(loc);
										tele.remove();
									}
								}, 2);
							}
						}
					}
				}
			}
			
		}
	}
	
	public ItemStack reduceDurability(ItemStack is) {
		short dur = 0;
		if (is != null) {
			if (is.isSimilar(new ItemStack(Material.WOOD_SWORD))) {
				dur = 2;
			}
			if (is.isSimilar(new ItemStack(Material.GOLD_SWORD))) {
				dur = 1;
			}
			if (is.isSimilar(new ItemStack(Material.STONE_SWORD))) {
				dur = 4;
			}
			if (is.isSimilar(new ItemStack(Material.IRON_SWORD))) {
				dur = 7;
			}
			if (is.isSimilar(new ItemStack(Material.DIAMOND_SWORD))) {
				dur = 40;
			}
		}
		is.setDurability((short) (is.getDurability() + dur));
		return is;
	}
	
	@EventHandler
	public void dmg(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			//slow fast
			int fast = 0;
			int slow = 0;
			for (PotionEffect pe : ((Player) e.getDamager()).getActivePotionEffects()) {
				if (pe.getType().getName().equalsIgnoreCase("SLOW_DIGGING")) {
					slow = pe.getAmplifier()*10;
				}
				if (pe.getType().getName().equalsIgnoreCase("FAST_DIGGING")) {
					fast = pe.getAmplifier()*10;
				}
			}
			int wait = slow - fast + 5;
			
			if (heavy.contains(e.getDamager())) {
				e.setCancelled(true);
			}else{
				heavy.add(e.getDamager());
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), new Runnable() {
					public void run() {
						heavy.remove(e.getDamager());
					}
				}, wait);
			}
			if (e.isCancelled() == false && e.getEntity() instanceof LivingEntity) {
				if (p.getItemInHand() != null) {
					if (p.getItemInHand().hasItemMeta()) {
						if (p.getItemInHand().getItemMeta().getLore() != null) {
							for (String str : p.getItemInHand().getItemMeta().getLore()) {
								if (str.equalsIgnoreCase("§7Frostric I") && rdm.nextInt(100)+1 <= 25) {
									((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0));
									e.getEntity().setFireTicks(0);
								}
								if (str.equalsIgnoreCase("§7Frostric II") && rdm.nextInt(100)+1 <= 25) {
									((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
									e.getEntity().setFireTicks(0);
								}
								if (str.equalsIgnoreCase("§7Frostric III") && rdm.nextInt(100)+1 <= 25) {
									((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2));
									e.getEntity().setFireTicks(0);
								}
								
								if (str.equalsIgnoreCase("§7Explode I")) {
									for (Entity ent : e.getEntity().getNearbyEntities(3, 3, 3)) {
										p.playSound(e.getEntity().getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
										if (ent != p && ent != e.getEntity() && ent instanceof LivingEntity) {
											((LivingEntity) ent).damage(e.getDamage() / 1.75);
										}
									}
								}
								if (str.equalsIgnoreCase("§7Explode II")) {
									for (Entity ent : e.getEntity().getNearbyEntities(4, 4, 4)) {
										p.playSound(e.getEntity().getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
										if (ent != p && ent != e.getEntity() && ent instanceof LivingEntity) {
											((LivingEntity) ent).damage(e.getDamage() / 1.5);
										}
									}
								}
								if (str.equalsIgnoreCase("§7Magic I")) {
									if (((LivingEntity) e.getEntity()).getHealth() > 0.5) {
										((LivingEntity) e.getEntity()).setHealth(((LivingEntity) e.getEntity()).getHealth() - 0.5);
									}else{
										((LivingEntity) e.getEntity()).setHealth(0);
									}
								}
								if (str.equalsIgnoreCase("§7Magic II")) {
									if (((LivingEntity) e.getEntity()).getHealth() > 1.0) {
										((LivingEntity) e.getEntity()).setHealth(((LivingEntity) e.getEntity()).getHealth() - 1.0);
									}else{
										((LivingEntity) e.getEntity()).setHealth(0);
									}
								}
								if (str.equalsIgnoreCase("§7Magic III")) {
									if (((LivingEntity) e.getEntity()).getHealth() > 1.5) {
										((LivingEntity) e.getEntity()).setHealth(((LivingEntity) e.getEntity()).getHealth() - 1.5);
									}else{
										((LivingEntity) e.getEntity()).setHealth(0);
									}
								}
								if (str.equalsIgnoreCase("§7Magic IV")) {
									if (((LivingEntity) e.getEntity()).getHealth() > 2.0) {
										((LivingEntity) e.getEntity()).setHealth(((LivingEntity) e.getEntity()).getHealth() - 2.0);
									}else{
										((LivingEntity) e.getEntity()).setHealth(0);
									}
								}
								if (str.equalsIgnoreCase("§7Vampire I")) {
									p.setHealth(p.getHealth() + e.getDamage() * 0.15);
								}
								if (str.equalsIgnoreCase("§7Vampire II")) {
									p.setHealth(p.getHealth() + e.getDamage() * 0.25);
								}
							}
						}
					}
				}
			}
		}
		//Counter
		if (e.getEntity() instanceof Player && e.getDamager() instanceof LivingEntity) {
			Player p = (Player) e.getEntity();
			if (e.isCancelled() == false) {
				if (p.getItemInHand() != null) {
					if (p.getItemInHand().hasItemMeta()) {
						if (p.getItemInHand().getItemMeta().getLore() != null) {
							for (String str : p.getItemInHand().getItemMeta().getLore()) {
								if (str.equalsIgnoreCase("§7Counter I") && rdm.nextInt(100)+1 <= 15 && ((Player) e.getEntity()).isBlocking()) {
									e.setCancelled(true);
									((LivingEntity) e.getDamager()).damage(e.getDamage(), e.getDamager());
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1);
								}
								if (str.equalsIgnoreCase("§7Counter II") && rdm.nextInt(100)+1 <= 30 && ((Player) e.getEntity()).isBlocking()) {
									e.setCancelled(true);
									((LivingEntity) e.getDamager()).damage(e.getDamage(), e.getDamager());
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1);
									
								}
								if (str.equalsIgnoreCase("§7Counter III") && rdm.nextInt(100)+1 <= 45 && ((Player) e.getEntity()).isBlocking()) {
									e.setCancelled(true);
									((LivingEntity) e.getDamager()).damage(e.getDamage(), e.getDamager());
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1);
								}
								if (str.equalsIgnoreCase("§7Shield I") && p.isBlocking()) {
									e.setDamage(e.getDamage() / 1.2);
								}
								if (str.equalsIgnoreCase("§7Shield II") && p.isBlocking()) {
									e.setDamage(e.getDamage() / 1.4);
								}
							}
						}
					}
				}
			}
		}
	}
}
