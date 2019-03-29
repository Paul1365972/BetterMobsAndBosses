package de.paul1365972;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import listeners.MoveListener;
import mobs.Creeper1;
import mobs.Creeper2;
import mobs.Creeper3;
import mobs.Skeleton1;
import mobs.Skeleton2;
import mobs.Skeleton3;
import mobs.Spider1;
import mobs.Spider2;
import mobs.Spider3;
import mobs.Zombie1;
import mobs.Zombie2;
import mobs.Zombie3;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Item;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import bosses.BossEnderzombie;
import bosses.BossHoly;
import command_executer.Command_xpbank;
import enchantment.EnchAnvil;
import enchantment.EnchBlunt;
import enchantment.EnchEffectManager;
import enchantment.EnchTable;

public class Main extends JavaPlugin implements Listener {

	Inventory invxp = Bukkit.createInventory(null, 9, "§bXP-Bank");
	public static HashMap<UUID, Integer> mobid = new HashMap<UUID, Integer>();
	public static HashMap<UUID, Integer> bossid = new HashMap<UUID, Integer>();
	public static List<Entity> bosses = new ArrayList<>();
	
	Boolean spawnable = true;
	Random mobrdm = new Random();
	int task;
	
	int dropch1;
	int drop1;
	int dropmin1;
	int dropmax1;
	int dropch2;
	int drop2;
	int dropmin2;
	int dropmax2;
	int dropch3;
	int drop3;
	int dropmin3;
	int dropmax3;
	int badanimal;
	int seedSoul;
	int cropSoul;
	
	public static Entity holy;
	int nextEnderboss = 0;
	boolean savemode;
	public static boolean spawnable2 = true;
	public static boolean tele;
	public static boolean fireball;
	public static boolean fly;
	public static int schutz = 0;
	boolean effectelib = true;
	public static boolean gravity;
	
	public void onEnable() {
		this.reloadConfig();
		this.getConfig().options().header("Speicher");
		this.getConfig().addDefault("bmab.bettermobs.on", true);
		this.getConfig().addDefault("bmab.morebosses.on", true);
		this.getConfig().addDefault("bmab.xpbank.on", true);
		this.getConfig().addDefault("bmab.bettermobs.spawnratelvl1", 16);
		this.getConfig().addDefault("bmab.bettermobs.spawnratelvl2", 8);
		this.getConfig().addDefault("bmab.bettermobs.spawnratelvl3", 4);
		
		
		this.getConfig().addDefault("bmab.bettermobs.drop_chance_lvl1", 20);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_id_lvl1", 265);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_min_lvl1", 1);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_max_lvl1", 4);
		
		this.getConfig().addDefault("bmab.bettermobs.drop_chance_lvl2", 30);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_id_lvl2", 266);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_min_lvl2", 1);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_max_lvl2", 3);
		
		this.getConfig().addDefault("bmab.bettermobs.drop_chance_lvl3", 40);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_id_lvl3", 264);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_min_lvl3", 1);
		this.getConfig().addDefault("bmab.bettermobs.dropitem_max_lvl3", 2);
		
		this.getConfig().addDefault("bmab.bettermobs.badanimals", 5);
		this.getConfig().addDefault("bmab.bettermobs.seedsoul", 10);
		this.getConfig().addDefault("bmab.bettermobs.cropsoul", 20);
		
		this.getConfig().addDefault("bmab.bettermobs.gravity", true);
		
		this.getConfig().addDefault("bmab.bettermobs.savemode", false);
		
		registerEvents();
		registerCommands();
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		
		dropch1 = this.getConfig().getInt("bmab.bettermobs.drop_chance_lvl1");
		drop1 = this.getConfig().getInt("bmab.bettermobs.dropitem_id_lvl1");
		dropmin1 = this.getConfig().getInt("bmab.bettermobs.dropitem_min_lvl1");
		dropmax1 = this.getConfig().getInt("bmab.bettermobs.dropitem_max_lvl1");
		
		dropch2 = this.getConfig().getInt("bmab.bettermobs.drop_chance_lvl2");
		drop2 = this.getConfig().getInt("bmab.bettermobs.dropitem_id_lvl2");
		dropmin2 = this.getConfig().getInt("bmab.bettermobs.dropitem_min_lvl2");
		dropmax2 = this.getConfig().getInt("bmab.bettermobs.dropitem_max_lvl2");
		
		dropch3 = this.getConfig().getInt("bmab.bettermobs.drop_chance_lvl3");
		drop3 = this.getConfig().getInt("bmab.bettermobs.dropitem_id_lvl3");
		dropmin3 = this.getConfig().getInt("bmab.bettermobs.dropitem_min_lvl3");
		dropmax3 = this.getConfig().getInt("bmab.bettermobs.dropitem_max_lvl3");
		
		badanimal = this.getConfig().getInt("bmab.bettermobs.badanimals");
		seedSoul = this.getConfig().getInt("bmab.bettermobs.seedsoul");
		cropSoul = this.getConfig().getInt("bmab.bettermobs.cropsoul");
		
		savemode = this.getConfig().getBoolean("bmab.bettermobs.savemode");
		
		gravity = this.getConfig().getBoolean("bmab.bettermobs.gravity");
		
		for (World w : Bukkit.getServer().getWorlds()) {
			for (Entity e : w.getEntities()) {
				if (this.getConfig().get("zsave.mobhashmap." + e.getUniqueId().toString()) != null) {
					mobid.put(e.getUniqueId(), (Integer) this.getConfig().get("zsave.mobhashmap." + e.getUniqueId().toString()));
				}
			}
		}
		for (World w : Bukkit.getServer().getWorlds()) {
			for (Entity e : w.getEntities()) {
				if (this.getConfig().get("zsave.bosshashmap." + e.getUniqueId().toString()) != null) {
					bossid.put(e.getUniqueId(), (Integer) this.getConfig().get("zsave.bosshashmap." + e.getUniqueId().toString()));
					if (bossid.get(e.getUniqueId())==1 || bossid.get(e.getUniqueId())==2) {
						bosses.add(e);
					}
					if (bossid.get(e.getUniqueId())==10 || bossid.get(e.getUniqueId())==11) {
						bosses.add(e);
						holy = e;
					}
					if (bossid.get(e.getUniqueId())==12) {
						bosses.add(e);
					}
				}
			}
		}
		taskenderboss();
		EnchEffectManager.startEM();
	}

	public void onDisable() {
		this.reloadConfig();
		this.saveConfig();
		
		this.getConfig().set("zsave.mobhashmap", null);
		this.getConfig().set("zsave.bosshashmap", null);
		
		for (UUID uuid : mobid.keySet()) {
			if (mobid.containsKey(uuid)) {
				this.getConfig().set("zsave.mobhashmap." + uuid.toString(), mobid.get(uuid));
			}
		}
		for (UUID uuid : bossid.keySet()) {
			if (bossid.containsKey(uuid)) {
				this.getConfig().set("zsave.bosshashmap." + uuid.toString(), bossid.get(uuid));
			}
		}
		
		this.saveConfig();
	}
	
	@EventHandler
	public void clickEvent(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getMaterial() != null && e.getItem() != null) {
			if (e.getMaterial().compareTo(Material.ENDER_STONE) == 0) {
				if (e.getItem().hasItemMeta()) {
					ItemMeta im = e.getItem().getItemMeta();
					if (im.getDisplayName() != null) {
						if (im.getDisplayName().equalsIgnoreCase("§5Dimensional Rift")) {
							e.setCancelled(true);
							if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
								if (im.getLore() == null) {
									p.sendMessage("§5Right-Click          = teleport to your Rift Location");
									p.sendMessage("§5Right-Click+Sneak = sets your Rift Location");
									p.sendMessage("§5Left-Click+Sneak  = unsave teleport to your Rift Location");
								}
								if (e.getPlayer().isSneaking()) {
									List<String> lore = new ArrayList<String>();
									im.setLore(null);
									lore.add(String.valueOf(e.getPlayer().getLocation().getBlockX()));
									lore.add(String.valueOf(e.getPlayer().getLocation().getBlockY()));
									lore.add(String.valueOf(e.getPlayer().getLocation().getBlockZ()));
									lore.add(e.getPlayer().getLocation().getWorld().getName());
									im.setLore(lore);
									im.setDisplayName("§5Dimensional Rift");
									ItemStack is = new ItemStack(e.getPlayer().getItemInHand());
									is.setItemMeta(im);
									p.setItemInHand(is);
								}else {
									if (im.getLore() != null) {
										int x = Integer.valueOf(p.getItemInHand().getItemMeta().getLore().get(0));
										int y = Integer.valueOf(p.getItemInHand().getItemMeta().getLore().get(1));
										int z = Integer.valueOf(p.getItemInHand().getItemMeta().getLore().get(2));
										World w = Bukkit.getWorld(p.getItemInHand().getItemMeta().getLore().get(3));
										if (w.getBlockAt(x, y, z).getType().isTransparent() && w.getBlockAt(x, y+1, z).getType().isTransparent()) {
											p.teleport(new Location(w, x, y, z).add(0.5, 0, 0.5));
											p.setFallDistance(p.getFallDistance() + 6);
										}else {
											p.sendMessage("§4Your Rift Location is Blocked! To teleport unsave you have to Left-Click and sneak");
										}
									}
								}
							}else{
								if (p.isSneaking() && im.getLore() != null) {
									int x = Integer.valueOf(p.getItemInHand().getItemMeta().getLore().get(0));
									int y = Integer.valueOf(p.getItemInHand().getItemMeta().getLore().get(1));
									int z = Integer.valueOf(p.getItemInHand().getItemMeta().getLore().get(2));
									World w = Bukkit.getWorld(p.getItemInHand().getItemMeta().getLore().get(3));
									p.teleport(new Location(w, x, y, z).add(0.5, 0, 0.5));
									p.setFallDistance(p.getFallDistance() + 10);
								}
							}
						}
					}
				}
			}
		}
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType().toString().equalsIgnoreCase("CAULDRON") && this.getConfig().getBoolean("bmab.xpbank.on")) {
				reloadXpInventory(p);
				e.setCancelled(true);
			}
			
			if (e.getClickedBlock().getType().compareTo(Material.PUMPKIN)==0 && this.getConfig().getBoolean("lp_mit_kev.morebosses.on")) {
				if (e.getMaterial().compareTo(Material.ENDER_PEARL)==0 && 
					e.getClickedBlock().getWorld().getBlockAt(e.getClickedBlock().getLocation().add(0, -1, 0)).
					getType().compareTo(Material.IRON_BLOCK) == 0) {
					e.setCancelled(true);
					p.getItemInHand().setAmount(p.getItemInHand().getAmount() -1);
					spawnable2 =false;
					Entity boss = BossEnderzombie.spawn(e);
					bossid.put(boss.getUniqueId(), 1);
					System.out.println(boss.getUniqueId());
					e.setCancelled(true);
					Bukkit.getScheduler().runTaskLater(this, new Runnable() {
						public void run() {
							spawnable2 = true;
						}
					}, 2);
					//starttask
					bosses.add(boss);
				}
				if (e.getMaterial().compareTo(Material.BONE)==0 && 
						e.getClickedBlock().getWorld().getBlockAt(e.getClickedBlock().getLocation().add(0, -1, 0)).
						getType().compareTo(Material.WOOL) == 0) {
						e.setCancelled(true);
						p.getItemInHand().setAmount(p.getItemInHand().getAmount() -1);
						spawnable2 =false;
						Entity boss = BossHoly.spawn(e);
						if (boss != null) {
							bossid.put(boss.getUniqueId(), 10);
							System.out.println(boss.getUniqueId());
							e.setCancelled(true);
							Bukkit.getScheduler().runTaskLater(this, new Runnable() {
								public void run() {
									spawnable2 = true;
								}
							}, 2);
							//starttask
							bosses.add(boss);
						}
					}
			}
		}
		if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getItem() != null) {
			if (e.getItem().hasItemMeta()) {
			if (e.getItem().getItemMeta().hasDisplayName()) {
				if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§dLucky Dye")) {
					ItemStack litem = new ItemStack(Material.DIAMOND_PICKAXE);
					ItemMeta lmeta = litem.getItemMeta();
					litem.setItemMeta(lmeta);
					e.getClickedBlock().breakNaturally(litem);
					if (mobrdm.nextInt(3)+1 == 3) {
					if (e.getPlayer().getItemInHand().hasItemMeta()) {
						if ( e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()) {
							if ( e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§dLucky Dye")) {
								ItemStack dye = new ItemStack (( e.getPlayer()).getItemInHand().getType(),  e.getPlayer().getItemInHand().getAmount()-1, (short) 9);
								ItemMeta dyem = e.getPlayer().getItemInHand().getItemMeta();
								dye.setItemMeta(dyem);
								e.getPlayer().setItemInHand(dye);
							}
						}
					}
					}
					
				}
			}
			}
		}
	}

	@EventHandler
	public void spawnMob(EntitySpawnEvent e) {
		if (this.getConfig().getBoolean("lp_mit_kev.bettermobs.on") && spawnable2) {
			//lvl1
			if (mobrdm.nextInt(100)+1 <= 2) {
				if (e.getEntity() instanceof Pig) {
					spawnable = false;
					Pig mob = (Pig) e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.PIG);
					mob.setCustomName("§dLucky Pig");
					mob.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20000000, 3));
					mob.setMaxHealth(40.0);
					mob.setHealth(40.0);
					e.setCancelled(true);
				}
			}
			
			if ((mobrdm.nextInt(100) +1) < this.getConfig().getInt("lp_mit_kev.bettermobs.spawnratelvl1") && spawnable) {
				
				if (e.getEntityType() == EntityType.ZOMBIE) {
					spawnable = false;
					mobid.put(Zombie1.spawn(e.getEntity()), 1);
					e.setCancelled(true);
				}
				if (e.getEntityType() == EntityType.CREEPER) {
					spawnable = false;
					mobid.put(Creeper1.spawn(e.getEntity()), 1);
					e.setCancelled(true);
				}
				if (e.getEntityType() == EntityType.SPIDER) {
					spawnable = false;
					mobid.put(Spider1.spawn(e.getEntity()), 1);
					e.setCancelled(true);
				}
				if (e.getEntityType() == EntityType.SKELETON) {
					spawnable = false;
					mobid.put(Skeleton1.spawn(e.getEntity()), 1);
					e.setCancelled(true);
				}
			}
			//lvl2
			if ((mobrdm.nextInt(100) +1) < this.getConfig().getInt("bmab.bettermobs.spawnratelvl2") &&spawnable) {
				
				if (e.getEntityType() == EntityType.ZOMBIE) {
					spawnable = false;
					mobid.put(Zombie2.spawn(e.getEntity()), 2);
					e.setCancelled(true);
				}
				if (e.getEntityType() == EntityType.SPIDER) {
					spawnable = false;
					mobid.put(Spider2.spawn(e.getEntity()), 2);
					e.setCancelled(true);
				}
				if (e.getEntity() instanceof Creeper) {
					spawnable = false;
					mobid.put(Creeper2.spawn(e.getEntity()), 2);
					e.setCancelled(true);
				}
				if (e.getEntity() instanceof Skeleton) {
					spawnable = false;
					mobid.put(Skeleton2.spawn(e.getEntity()), 2);
					e.setCancelled(true);
				}
			}
			//lvl3
			if ((mobrdm.nextInt(100) +1) < this.getConfig().getInt("bmab.bettermobs.spawnratelvl3") &&spawnable) {
				
				if (e.getEntity() instanceof Zombie) {
					spawnable = false;
					mobid.put(Zombie3.spawn(e.getEntity()), 3);
					e.setCancelled(true);
				}
				
				if (e.getEntityType() == EntityType.SPIDER) {
					spawnable = false;
					mobid.put(Spider3.spawn(e.getEntity()), 3);
					e.setCancelled(true);
				}
				
				
				if (e.getEntity() instanceof Creeper) {
					spawnable = false;
					mobid.put(Creeper3.spawn(e.getEntity()), 3);
					e.setCancelled(true);
				}
				
				if (e.getEntity() instanceof Skeleton) {
					spawnable = false;
					mobid.put(Skeleton3.spawn(e.getEntity()), 3);
					e.setCancelled(true);
				}
				
			}
			if (savemode) {
				Bukkit.getScheduler().runTaskLater(this, new Runnable() {
					public void run() {
						spawnable = true;
					}
				}, 0);
			}else {
				spawnable = true;
			}
		}
	}
	
	@EventHandler
	public void shoot(EntityShootBowEvent e) {
		if (e.getEntity() instanceof Skeleton) {
			if (mobid.containsKey(e.getEntity().getUniqueId())) {
				if (mobid.get(e.getEntity().getUniqueId()) == 3) {
					Location loc = e.getProjectile().getLocation();Vector vec = e.getProjectile().getVelocity();
					e.getProjectile().setVelocity(e.getProjectile().getVelocity().multiply(1.5));
					for (int i = 0; i < 4; i++) {
						Arrow arw = e.getEntity().getWorld().spawnArrow(loc, vec, 1.0F, 12.0F);
						arw.setShooter(e.getEntity());
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void boom(EntityExplodeEvent e) {
		if (e.getEntity() instanceof Creeper) {
			if (mobid.containsKey(e.getEntity().getUniqueId())) {
				if (mobid.get(e.getEntity().getUniqueId()) == 2) {
					List<Entity> ent = e.getEntity().getNearbyEntities(10, 10, 10);
					for (Entity t : ent) {
						t.getWorld().strikeLightning(t.getLocation());
					}
				}
			}
		}
	}
	
	
	@EventHandler(priority=EventPriority.NORMAL)
	public void hit(final EntityDamageByEntityEvent e) {
		if (e.isCancelled() == false) {
			if (bossid.containsKey(e.getEntity().getUniqueId())) {
				if (bossid.get(e.getEntity().getUniqueId()) == 1 || bossid.get(e.getEntity().getUniqueId()) == 2) {
					final Entity ent = e.getEntity();
					Bukkit.getScheduler().runTaskLater(this, new Runnable() {
						public void run() {
							ent.setVelocity(new Vector(0, 0, 0));
						}
					}, 1);
					if (mobrdm.nextInt(3)+1 ==3 && ((Zombie) e.getEntity()).getTarget() != null) {
						e.getEntity().teleport(((Zombie) e.getEntity()).getTarget().getLocation());
					}
					if (mobrdm.nextInt(4)+1 ==4) {
						spawnable2 = false;
						for (int i = 0; i < 3; i++) {
							Zombie zb = (Zombie) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.ZOMBIE);
							zb.setBaby(true);
							zb.setVillager(false);
							ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short) 3);
							SkullMeta sm = (SkullMeta) head.getItemMeta();
							sm.setOwner("MHF_Enderman");
							head.setItemMeta(sm);
							zb.getEquipment().setHelmet(head);
							zb.setVelocity(new Vector(0, 0.5, 0));
						}
						Bukkit.getScheduler().runTaskLater(this, new Runnable() {
							public void run() {
								spawnable2 = true;
							}
						}, 2);
					}
					
				}
				if (schutz > 0) {
					if (bossid.get(e.getEntity().getUniqueId()) == 10 || bossid.get(e.getEntity().getUniqueId()) == 11 || bossid.get(e.getEntity().getUniqueId()) == 12) {
						e.setCancelled(true);
					}
				}
				if (bossid.get(e.getEntity().getUniqueId()) == 10 || bossid.get(e.getEntity().getUniqueId()) == 11) {
					if (mobrdm.nextBoolean()) {
						spawnable2 = false;
						int maxsilver = 0;
						for (Entity ent : e.getEntity().getNearbyEntities(10, 10, 10)) {
							if (ent instanceof Silverfish) {
								maxsilver++;
							}
						}
						if (maxsilver < 20) {
							for (int i = 0; i < 5; i++) {
								Silverfish zb = (Silverfish) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.SILVERFISH);
								zb.setHealth(5);
								zb.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 2000, 1));
								zb.setVelocity(new Vector(0, 0.5, 0));
								mobid.put(zb.getUniqueId(), 10);
							}
						Bukkit.getScheduler().runTaskLater(this, new Runnable() {
							public void run() {
								spawnable2 = true;
							}
						}, 2);
						}
					}
				}
				if (bossid.get(e.getEntity().getUniqueId()) == 12) {
					((Skeleton) e.getEntity().getPassenger()).damage(e.getFinalDamage());
					((Blaze) e.getEntity()).setHealth(200);
				}
			}
			
			if (e.getDamager() instanceof Player) {
				if (((Player) e.getDamager()).getItemInHand().hasItemMeta()) {
					if (((Player) e.getDamager()).getItemInHand().getItemMeta().hasDisplayName()) {
						if (((Player) e.getDamager()).getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§dLucky Dye")) {
							ItemStack dye = new ItemStack (((Player) e.getDamager()).getItemInHand().getType(), ((Player) e.getDamager()).getItemInHand().getAmount()-1, (short) 9);
							ItemMeta dyem = ((Player) e.getDamager()).getItemInHand().getItemMeta();
							dye.setItemMeta(dyem);
							((Player) e.getDamager()).setItemInHand(dye);
						}
					}
				}
			}
			
			if (e.getDamager() instanceof Snowball) {
				e.setDamage(2);
				if (e.getEntity() instanceof Player) {
					final Entity ent = e.getEntity();
					Bukkit.getScheduler().runTaskLater(this, new Runnable() {
						public void run() {
							ent.setVelocity(new Vector(0, 0, 0));
						}
					}, 0);
				}
			}
			
			if (e.getEntity() instanceof Spider) {
				if (mobid.containsKey(e.getEntity().getUniqueId())) {
					if (mobid.get(e.getEntity().getUniqueId()) == 2) {
						if (mobrdm.nextInt(3) == 2) {
						for (int i = -1; i < 2; i++) {
							for (int j = -1; j < 2; j++) {
								if (e.getEntity().getWorld().getBlockAt(e.getEntity().getLocation().getBlockX() + i, e.getEntity().getLocation().getBlockY(), e.getEntity().getLocation().getBlockZ() + j).getType().isTransparent()) {
									e.getEntity().getWorld().getBlockAt(e.getEntity().getLocation().getBlockX() + i, e.getEntity().getLocation().getBlockY(), e.getEntity().getLocation().getBlockZ() + j).setType(Material.WEB);
									final Entity ent = e.getEntity();
									final Location loc = e.getEntity().getLocation();
									Bukkit.getScheduler().runTaskLater(this, new Runnable() {
										public void run() {
											
											for (int i = -1; i < 2; i++) {
												for (int j = -1; j < 2; j++) {
													if (ent.getWorld().getBlockAt(loc.getBlockX() + i, loc.getBlockY(), loc.getBlockZ() + j).getType().toString().equalsIgnoreCase("WEB")) {
														ent.getWorld().getBlockAt(loc.getBlockX() + i, loc.getBlockY(), loc.getBlockZ() + j).setType(Material.AIR);
												}
												}
											}
											}
									}, 60);
								}
							}
						}
						}
					}
				}
			}
			}
			
			if (e.getDamager() instanceof Spider) {
				if (mobid.containsKey(e.getDamager().getUniqueId())) {
					if (mobid.get(e.getDamager().getUniqueId()) == 2) {
						if (mobrdm.nextInt(2) == 1) {
						if (e.getEntity().getWorld().getBlockAt(e.getEntity().getLocation()).getType().isTransparent()) {
							e.getEntity().getWorld().getBlockAt(e.getEntity().getLocation()).setType(Material.WEB);
							
							final Entity ent = e.getEntity();
							final Location loc = e.getEntity().getLocation();
							Bukkit.getScheduler().runTaskLater(this, new Runnable() {
								public void run() {
									ent.getWorld().getBlockAt(loc).setType(Material.AIR);
								}
							}, 60);
						}
						}
					}
				}
			}
			
			if (e.getEntity() instanceof Creeper) {
				if (mobid.containsKey(e.getEntity().getUniqueId())) {
					if (mobid.get(e.getEntity().getUniqueId()) == 1) {
						final Entity ent = e.getEntity();
						Bukkit.getScheduler().runTaskLater(this, new Runnable() {
							public void run() {
								ent.setVelocity(new Vector(0, 0, 0));
							}
						}, 1);
						
					}
				}
			}
			if (e.getDamager() instanceof Zombie) {
				if (mobid.containsKey(e.getDamager().getUniqueId())) {
					if (mobid.get(e.getDamager().getUniqueId()) == 3) {
						final Entity et = e.getEntity();
						Bukkit.getScheduler().runTaskLater(this, new Runnable() {
							public void run() {
								et.setVelocity(new Vector(0, 1.0, 0));
							}
						}, 1);
					}
					if (mobid.get(e.getDamager().getUniqueId()) == 2) {
						if (mobrdm.nextInt(5) == 4 && ((Zombie) e.getDamager()).getEquipment().getItemInHand().isSimilar(new ItemStack(Material.AIR, 1))) {
						if (e.getEntity() instanceof Player) {
							Player p = (Player) e.getEntity();
							ItemStack item = p.getItemInHand();
							ItemMeta itemm = item.getItemMeta();
							itemm.setDisplayName(String.valueOf(p.getItemInHand().getDurability()));
							item.setItemMeta(itemm);
							((Zombie) e.getDamager()).getEquipment().setItemInHand(item);
							
							
							((Zombie) e.getDamager()).getEquipment().setItemInHandDropChance(1.0F);
							ItemStack troll = new ItemStack(Material.ROTTEN_FLESH);
							ItemMeta trollm = troll.getItemMeta();
							trollm.setDisplayName("§dTroll Flesh");
							ArrayList<String> lore =  new ArrayList<String>();
							lore.add("§rYou got this item because a ");
							lore.add("§4Thief Zombie§r has stolen your weapon!");
							trollm.setLore(lore);
							troll.setItemMeta(trollm);
							troll.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
							p.setItemInHand(troll);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void breakBlock(BlockBreakEvent e) {
		if (e.getBlock().getType().compareTo(Material.CROPS) == 0) {
			if (mobrdm.nextInt(100)+1 <= seedSoul) {
				Silverfish sf = (Silverfish) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.SILVERFISH);
				Item item = e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.WHEAT, 64));
				item.setPickupDelay(2000000);
				sf.setPassenger(item);
				sf.setMaxHealth(10);
				sf.setHealth(10);
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2000, 1));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 4));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 3));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2000, 1));
				sf.setCustomName("§2Wheat Soul");
			}
		}
		if (e.getBlock().getType().compareTo(Material.CARROT) == 0) {
			if (mobrdm.nextInt(100)+1 <= seedSoul) {
				Silverfish sf = (Silverfish) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.SILVERFISH);
				Item item = e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.CARROT_ITEM, 64));
				item.setPickupDelay(2000000);
				sf.setPassenger(item);
				sf.setMaxHealth(10);
				sf.setHealth(10);
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2000, 1));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 4));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 2));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2000, 1));
				sf.setCustomName("§2Carrot Soul");
			}
		}
		if (e.getBlock().getType().compareTo(Material.POTATO) == 0) {
			if (mobrdm.nextInt(100)+1 <= seedSoul) {
				Silverfish sf = (Silverfish) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.SILVERFISH);
				Item item = e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.POTATO_ITEM, 64));
				item.setPickupDelay(2000000);
				sf.setPassenger(item);
				sf.setMaxHealth(10);
				sf.setHealth(10);
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2000, 1));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 4));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 2));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2000, 1));
				sf.setCustomName("§2Potato Soul");
			}
		}
		if (e.getBlock().getType().compareTo(Material.MELON_BLOCK) == 0) {
			if (mobrdm.nextInt(100)+1 <= cropSoul) {
				Silverfish sf = (Silverfish) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.SILVERFISH);
				Item item = e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.POTATO_ITEM, 64));
				item.setPickupDelay(2000000);
				sf.setPassenger(item);
				sf.setMaxHealth(10);
				sf.setHealth(10);
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2000, 1));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 4));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 2));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2000, 1));
				sf.setCustomName("§2Melon Soul");
			}
		}
		if (e.getBlock().getType().compareTo(Material.PUMPKIN) == 0) {
			if (mobrdm.nextInt(100)+1 <= cropSoul) {
				Silverfish sf = (Silverfish) e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.SILVERFISH);
				Item item = e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.PUMPKIN, 64));
				item.setPickupDelay(2000000);
				sf.setPassenger(item);
				sf.setMaxHealth(10);
				sf.setHealth(10);
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2000, 1));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2000, 4));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2000, 2));
				sf.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2000, 1));
				sf.setCustomName("§2Pumpkin Soul");
			}
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void dead(EntityDeathEvent e) {
		if (bossid.containsKey(e.getEntity().getUniqueId())) {
			if (bossid.get(e.getEntity().getUniqueId())==2) {
				ItemStack loot = new ItemStack(Material.ENDER_STONE);
				ItemMeta ml = loot.getItemMeta();
				ml.setDisplayName("§5Dimensional Rift");
				loot.setItemMeta(ml);
				e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), loot);
			}
			if (bossid.get(e.getEntity().getUniqueId())==10 || bossid.get(e.getEntity().getUniqueId())==11) {
				holy.remove();
				holy = null;
			}
			bossid.remove(e.getEntity().getUniqueId());
		}
		if (e.getEntity() instanceof Pig) {
			if (e.getEntity().getCustomName() != null) {
				if (e.getEntity().getCustomName().equalsIgnoreCase("§dLucky Pig")) {
					ItemStack litem = new ItemStack(Material.INK_SACK, 10, (short) 9);
					ItemMeta lmeta = litem.getItemMeta();
					lmeta.setDisplayName("§dLucky Dye");
					lmeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
					lmeta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
					lmeta.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
					lmeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 5, true);
					lmeta.addEnchant(Enchantment.DIG_SPEED, 5, true);
					litem.setItemMeta(lmeta);
					e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), litem);
				}
			}
		}
		if (mobid.containsKey(e.getEntity().getUniqueId())) {
			if (mobid.get(e.getEntity().getUniqueId()) == 1) {
				if (mobrdm.nextInt(100) +1 <= dropch1) {
					ItemStack itemdrop = new ItemStack(drop1, mobrdm.nextInt(dropmax1 - dropmin1 + 1) + dropmin1);
					e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), itemdrop);
				}
			}
			if (mobid.get(e.getEntity().getUniqueId()) == 2) {
				if (mobrdm.nextInt(100) +1 <= dropch2) {
					ItemStack itemdrop = new ItemStack(drop2, mobrdm.nextInt(dropmax2 - dropmin2 + 1) + dropmin2);
					e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), itemdrop);
				}
			}
			if (mobid.get(e.getEntity().getUniqueId()) == 3) {
				if (mobrdm.nextInt(100) +1 <= dropch3) {
					ItemStack itemdrop = new ItemStack(drop3, mobrdm.nextInt(dropmax3 - dropmin3 + 1) + dropmin3);
					e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), itemdrop);
				}
			}
		}
		if (e.getEntity() instanceof Silverfish) {
			if (e.getEntity().getPassenger() != null) {
				e.getEntity().getPassenger().remove();
			}
		}
		
		if (e.getEntity() instanceof Cow || e.getEntity() instanceof Pig || e.getEntity() instanceof Sheep || e.getEntity() instanceof Chicken || e.getEntity() instanceof Pig || e.getEntity() instanceof MushroomCow || e.getEntity() instanceof Rabbit || e.getEntity() instanceof Sheep) {
			if (mobrdm.nextInt(100)+1 <= badanimal) {
				e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), (float) 2 , true);
			}
			if (mobrdm.nextInt(100)+1 <= badanimal) {
				if (e.getEntity().getKiller() != null) {
					e.getEntity().getKiller().addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 250, 4));
				}
			}
		}
		
		if (e.getEntity() instanceof Zombie) {
			if (mobid.containsKey(e.getEntity().getUniqueId())){
				if (mobid.get(e.getEntity().getUniqueId()) == 2 && e.getDrops().isEmpty() != true) {
					for (ItemStack items : e.getDrops()) {
						if (items.getType() != Material.LEATHER_BOOTS && items.getType() != Material.LEATHER_LEGGINGS && items.getType() != Material.LEATHER_CHESTPLATE && items.hasItemMeta()) {
							ItemMeta im = items.getItemMeta();
							if (im.hasDisplayName()) {
								items.setDurability(Short.valueOf(im.getDisplayName()));
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e) {
		if (this.getConfig().getBoolean("bmab.xpbank.on")) {
			Player p = (Player) e.getWhoClicked();
			if (e.getClickedInventory() != null) {
				if (e.getClickedInventory().getTitle() == "§bXP-Bank") {
					switch (e.getRawSlot()) {
					case 1:
						if (p.getLevel() >= 1) {
							p.setLevel(p.getLevel() - 1);
							int xp = this.getConfig().getInt(
									"bmab.xpbank." + p.getUniqueId());
							xp++;
							this.getConfig().set(
									"bmab.xpbank." + p.getUniqueId(), xp);
						}
						break;

					case 2:
						if (p.getLevel() >= 5) {
							p.setLevel(p.getLevel() - 5);
							int xp = this.getConfig().getInt(
									"bmab.xpbank." + p.getUniqueId());
							xp = xp + 5;
							this.getConfig().set(
									"bmab.xpbank." + p.getUniqueId(), xp);
						}
						break;

					case 3:
						if (p.getLevel() >= 20) {
							p.setLevel(p.getLevel() - 20);
							int xp = this.getConfig().getInt(
									"bmab.xpbank." + p.getUniqueId());
							xp = xp + 20;
							this.getConfig().set(
									"bmab.xp." + p.getUniqueId(), xp);
						}
						break;

					case 5:
						if (this.getConfig().getInt(
								"bmab.xpbank." + p.getUniqueId()) >= 1) {
							p.setLevel(p.getLevel() + 1);
							int xp = this.getConfig().getInt(
									"bmab.xpbank." + p.getUniqueId());
							xp = xp - 1;
							this.getConfig().set(
									"bmab.xpbank." + p.getUniqueId(), xp);
						}
						break;

					case 6:
						if (this.getConfig().getInt(
								"bmab.xpbank." + p.getUniqueId()) >= 5) {
							p.setLevel(p.getLevel() + 5);
							int xp = this.getConfig().getInt(
									"bmab.xpbank." + p.getUniqueId());
							xp = xp - 5;
							this.getConfig().set(
									"bmab.xpbank." + p.getUniqueId(), xp);
						}
						break;

					case 7:
						if (this.getConfig().getInt(
								"bmab.xpbank." + p.getUniqueId()) >= 20) {
							p.setLevel(p.getLevel() + 20);
							int xp = this.getConfig().getInt(
									"bmab.xpbank." + p.getUniqueId());
							xp = xp - 20;
							this.getConfig().set(
									"bmab.xpbank." + p.getUniqueId(), xp);
						}
						break;

					default:
						break;
					}
					e.setCancelled(true);
					reloadXpInventory(p);
				}
			}
		}
	}
	public void reloadXpInventory(Player XPp) {

		if (this.getConfig().get("bmab.xpbank." + XPp.getUniqueId()) == null) {
			this.getConfig().set("bmab.xpbank." + XPp.getUniqueId(), 0);
			this.saveConfig();
		}

		ItemStack itemxp = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta metaxp = itemxp.getItemMeta();
		metaxp.setDisplayName("1 Level §4↑§r");
		itemxp.setItemMeta(metaxp);
		invxp.setItem(1, itemxp);
		metaxp.setDisplayName("5 Level §4↑§r");
		itemxp.setItemMeta(metaxp);
		invxp.setItem(2, itemxp);
		metaxp.setDisplayName("20 Level §4↑§r");
		itemxp.setItemMeta(metaxp);
		invxp.setItem(3, itemxp);

		ItemStack itemxp1 = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta metaxp1 = itemxp1.getItemMeta();
		metaxp1.setDisplayName("§bDu hast §6"
				+ String.valueOf(this.getConfig().getInt(
						"bmab.xpbank." + XPp.getUniqueId()))
				+ "§b Level!");
		itemxp1.setItemMeta(metaxp1);
		invxp.setItem(4, itemxp1);

		metaxp.setDisplayName("1 Level §2↓§r");
		itemxp.setItemMeta(metaxp);
		invxp.setItem(5, itemxp);
		metaxp.setDisplayName("5 Level §2↓§r");
		itemxp.setItemMeta(metaxp);
		invxp.setItem(6, itemxp);
		metaxp.setDisplayName("20 Level §2↓§r");
		itemxp.setItemMeta(metaxp);
		invxp.setItem(7, itemxp);

		XPp.openInventory(invxp);
	}
	

	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(this, this);
		pm.registerEvents(new MoveListener(), this);
		pm.registerEvents(new ChunkGenerator(), this);
		pm.registerEvents(new EnchTable(), this);
		pm.registerEvents(new EnchBlunt(), this);
		pm.registerEvents(new EnchEffectManager(), this);
		pm.registerEvents(new EnchAnvil(), this);
	}

	private void registerCommands() {
		this.getCommand("xpbank").setExecutor(new Command_xpbank());
	}
	
	public void taskenderboss() {
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				nextEnderboss++;
				for(Entity theboss : bosses) {
					if (theboss.isDead()){
						ExperienceOrb ex = (ExperienceOrb) theboss.getWorld().spawnEntity(theboss.getLocation(), EntityType.EXPERIENCE_ORB);
						ex.setExperience(72);
						bosses.remove(theboss);
						break;
					}
					//EnderBoss
					if (nextEnderboss >= 4) {
						nextEnderboss = 0;
						if (bossid.get(theboss.getUniqueId()) ==1 || bossid.get(theboss.getUniqueId()) ==2) {
							Zombie boss = (Zombie) theboss;
							spawnable2 = true;
							BossEnderzombie.repeat(boss);
						}
					}
					if (bossid.get(theboss.getUniqueId()) == 10 || bossid.get(theboss.getUniqueId()) == 11) {
						Skeleton boss = (Skeleton) theboss;
						boss.setPassenger(holy);
						if (holy.getWorld().getBlockAt(boss.getLocation()).isLiquid()) {
							holy.setVelocity(new Vector(0, 0.3, 0));
						}
						spawnable2 = true;
						BossHoly.repeat(boss);
					}
				}
			}
		}, 30, 5);
	}
	}
