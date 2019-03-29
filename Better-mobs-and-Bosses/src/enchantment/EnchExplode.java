package enchantment;

import java.util.ArrayList;
import java.util.Random;

public class EnchExplode {
	static Random rdm = new Random();
	
	public static ArrayList<String> ench(ArrayList<String> lore) {
		int lvl = 1;
		if (rdm.nextInt(100)+1 <= 30) {
			lvl = 2;
		}
		
		lore.add("§7Explode " + EnchConverter.il(lvl));
		return lore;
	}
}
