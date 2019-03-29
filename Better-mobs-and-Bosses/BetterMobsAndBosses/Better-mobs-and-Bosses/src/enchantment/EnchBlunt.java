package enchantment;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnchBlunt implements Listener{
	
	static Random rdm = new Random();
	
	@EventHandler(priority=EventPriority.HIGH)
	public void item(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (((Player) e.getDamager()).getItemInHand() != null) {
				if (((Player) e.getDamager()).getItemInHand().hasItemMeta()) {
					for (String str : ((Player) e.getDamager()).getItemInHand().getItemMeta().getLore()) {
						if (str.equalsIgnoreCase("§7Blunt I")) {
							e.setDamage(e.getDamage()-0.75);
						}
						if (str.equalsIgnoreCase("§7Blunt II")) {
							e.setDamage(e.getDamage()-1.5);
						}
						if (str.equalsIgnoreCase("§7Blunt III")) {
							e.setDamage(e.getDamage()-2.25);
						}
						if (str.equalsIgnoreCase("§7Blunt IV")) {
							e.setDamage(e.getDamage()-3.0);
						}
						if (str.equalsIgnoreCase("§7Blunt V")) {
							e.setDamage(e.getDamage()-3.75);
						}
					}
				}
			}
		}
	}
	public static ArrayList<String> ench(ArrayList<String> lore) {
		int lvl = 1;
		if (rdm.nextInt(100)+1 <= 33) {
			lvl = 2;
		}
		if (rdm.nextInt(100)+1 <= 20) {
			lvl = 3;
		}
		
		lore.add("§7Blunt " + EnchConverter.il(lvl));
		return lore;
	}
}
