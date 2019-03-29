package listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Spider;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import de.paul1365972.Main;

public class MoveListener implements Listener{
	public void moveing(PlayerMoveEvent e) {
		List<Entity> ent = e.getPlayer().getNearbyEntities(10, 10, 10);
		for (Entity mob : ent) {
			if (mob instanceof Creeper) {
				if (Main.mobid.containsKey(mob.getUniqueId())) {
				if (Main.mobid.get(mob.getUniqueId()) == 2 && Main.gravity) {
					Location loc2 = e.getPlayer().getLocation();
					Location loc1 = mob.getLocation();
					double c = Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) + Math.pow(loc1.getY() - loc2.getY(), 2) + Math.pow(loc1.getZ() - loc2.getZ(), 2));
					double y = (loc1.getY()-loc2.getY())*0.5/c;
					c = Math.pow(c, 1.1);
					e.getPlayer().setVelocity(new Vector( (loc1.getX()-loc2.getX())*0.5/c , y , (loc1.getZ()-loc2.getZ())*0.2/c ));
					
					if (((Creeper) mob).getHealth() > 1) {
					((Creeper) mob).setHealth(((Creeper) mob).getHealth() - 0.2);
					}else{
						((Creeper) mob).setHealth(0.0);
					}
				}
				}
			}
			if (mob instanceof Skeleton) {
				if (Main.mobid.containsKey(mob.getUniqueId())) {
					if (Main.mobid.get(mob.getUniqueId()) == 3 && Main.gravity) {
						Location loc2 = e.getPlayer().getLocation();
						Location loc1 = mob.getLocation();
						double c = Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) + Math.pow(loc1.getY() - loc2.getY(), 2) + Math.pow(loc1.getZ() - loc2.getZ(), 2));
						c = Math.pow(c, 2.5);
						e.getPlayer().setVelocity(new Vector( -(loc1.getX()-loc2.getX())*0.5/c , -(loc1.getY()-loc2.getY())*0.5/c , -(loc1.getZ()-loc2.getZ())*0.2/c ));
					}
				}
			}
			if (mob instanceof Spider) {
				if (Main.mobid.containsKey(mob.getUniqueId())) {
					if (Main.mobid.get(mob.getUniqueId()) == 3) {
						float x= (float) (e.getPlayer().getLocation().getX() -mob.getLocation().getX());
						float y= (float) (e.getPlayer().getLocation().getY() -mob.getLocation().getY());
						float z= (float) (e.getPlayer().getLocation().getZ() -mob.getLocation().getZ());
						Snowball sb = (Snowball) mob.getWorld().spawnEntity(new Location(mob.getWorld(), mob.getLocation().getX(), mob.getLocation().getY() +1.5, mob.getLocation().getZ()), EntityType.SNOWBALL);
						sb.setVelocity(new Vector(x,y,z).multiply(0.35));
					}
				}
			}
		}
		List<Entity> ent2 = e.getPlayer().getNearbyEntities(5, 5, 5);
		for (final Entity mob : ent2) {
			if (mob instanceof Silverfish) {
				if (Main.mobid.containsKey(mob.getUniqueId())) {
					if (Main.mobid.get(mob.getUniqueId()) == 10) {
						float x= (float) (e.getPlayer().getLocation().getX() -mob.getLocation().getX());
						float y= (float) (e.getPlayer().getLocation().getY() -mob.getLocation().getY());
						float z= (float) (e.getPlayer().getLocation().getZ() -mob.getLocation().getZ());
						Snowball sb = (Snowball) mob.getWorld().spawnEntity(new Location(mob.getWorld(), mob.getLocation().getX(), mob.getLocation().getY() +1.5, mob.getLocation().getZ()), EntityType.SNOWBALL);
						sb.setVelocity(new Vector(x,y,z).multiply(0.35));
					}
				}
			}
			if (mob instanceof Creeper) {
				if (Main.mobid.containsKey(mob.getUniqueId())) {
					if (Main.mobid.get(mob.getUniqueId()) == 3 ) {
						Location loc2 = e.getPlayer().getLocation();
						Location loc1 = mob.getLocation();
						double c = Math.sqrt(Math.pow(loc1.getX() - loc2.getX(), 2) + Math.pow(loc1.getZ() - loc2.getZ(), 2));
						c = Math.pow(c, 0.9);
						mob.setVelocity(new Vector( -(loc1.getX()-loc2.getX())*0.5/c , 1.5 , -(loc1.getZ()-loc2.getZ())*0.2/c ));
						Main.mobid.remove(mob.getUniqueId());
						mob.setCustomName("§6Rocket");
						Bukkit.getScheduler().runTaskLater((Plugin) this, new Runnable() {
							public void run() {
								mob.setCustomName("Grumm");
							}
						}, 25);
						
						Bukkit.getScheduler().runTaskLater((Plugin) this, new Runnable() {
							public void run() {
								mob.getWorld().createExplosion(mob.getLocation(), 2.5F);
								mob.remove();
							}
						}, 40);
					}
				}
			}
		}
	}
}
