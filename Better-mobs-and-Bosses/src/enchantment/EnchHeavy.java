package enchantment;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.event.Listener;

public class EnchHeavy implements Listener{
	static Random rdm = new Random();
	
	public static ArrayList<String> ench(ArrayList<String> lore) {
		int lvl = 1;
		if (rdm.nextInt(100)+1 <= 30) {
			lvl = 2;
		}
		if (rdm.nextInt(100)+1 <= 15) {
			lvl = 3;
		}
		
		lore.add("§7Heavy " + EnchConverter.il(lvl));
		return lore;
	}
}
