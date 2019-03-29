package enchantment;

import java.util.ArrayList;
import java.util.Random;

public class EnchMagic {
	static Random rdm = new Random();
	
	public static ArrayList<String> ench(ArrayList<String> lore) {
		int lvl = 1;
		if (rdm.nextInt(100)+1 <= 40) {
			lvl = 2;
		}
		if (rdm.nextInt(100)+1 <= 15) {
			lvl = 3;
		}
		if (rdm.nextInt(100)+1 <= 5) {
			lvl = 3;
		}
		
		lore.add("§7Magic " + EnchConverter.il(lvl));
		return lore;
	}
}
