package enchantment;

import java.util.ArrayList;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;


public class EnchConverter {
	public static String il(int lvl) {
		String str;
		switch (lvl) {
		case 1:
			str = "I";
			break;
		case 2:
			str = "II";
			break;
		case 3:
			str = "III";
			break;
		case 4:
			str = "IV";
			break;
		case 5:
			str = "V";
			break;
		//null
		default:
			str = "BUG";
			break;
		}
		return str;
	}
	public static int li(String lvl) {
		int i = 0;
		if (lvl.equalsIgnoreCase("I")) {
			i = 1;
		}
		if (lvl.equalsIgnoreCase("II")) {
			i = 2;
		}
		if (lvl.equalsIgnoreCase("III")) {
			i = 3;
		}
		if (lvl.equalsIgnoreCase("IV")) {
			i = 4;
		}
		if (lvl.equalsIgnoreCase("V")) {
			i = 5;
		}
		if (lvl.equalsIgnoreCase("BUG")) {
			i = 10;
		}
		return i;
	}
	
	public static int check(String ench, int lvl) {
		if (ench.equalsIgnoreCase("§7Heavy")) {
			lvl = Math.min(lvl, 3);
		}
		if (ench.equalsIgnoreCase("§7Blunt")) {
			lvl = Math.min(lvl, 5);
		}
		if (ench.equalsIgnoreCase("§7Haste")) {
			lvl = Math.min(lvl, 3);
		}
		if (ench.equalsIgnoreCase("§7Frostric")) {
			lvl = Math.min(lvl, 3);
		}
		if (ench.equalsIgnoreCase("§7Counter")) {
			lvl = Math.min(lvl, 3);
		}
		if (ench.equalsIgnoreCase("§7Shield")) {
			lvl = Math.min(lvl, 2);
		}
		if (ench.equalsIgnoreCase("§7Explode")) {
			lvl = Math.min(lvl, 2);
		}
		if (ench.equalsIgnoreCase("§7Magic")) {
			lvl = Math.min(lvl, 4);
		}
		if (ench.equalsIgnoreCase("§7Vampire")) {
			lvl = Math.min(lvl, 2);
		}
		
		return lvl;
	}
	
	
	public static ArrayList<String> uncombineable(ArrayList<String> lore, Map<Enchantment, Integer> ench) {
		for (String str : lore) {
			if (str.startsWith("Counter")) {
				for (String com : lore) {
					if (com.startsWith("Endering")) {
						lore.remove(lore.indexOf(com.startsWith("Endering")));
					}
				}
			}
		}
		return lore;
	}
	
	public static boolean c(String str, String str2) {
		boolean check = false;
		if (str.equalsIgnoreCase(str2)) {
			check = true;
		}
		return check;
		
	}
}
