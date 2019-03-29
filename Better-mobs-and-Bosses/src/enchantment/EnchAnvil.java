package enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchAnvil implements Listener {
	
	static List<String> lore = new ArrayList<>();
	Player p;
	
	@EventHandler
	public void anvil (final InventoryClickEvent e) {
		p = (Player) e.getWhoClicked();
		if (e.getInventory() instanceof AnvilInventory) {
			AnvilInventory anvil = (AnvilInventory) e.getInventory();
			InventoryView view = e.getView();
			int rawSlot = e.getRawSlot();
			if(rawSlot == view.convertSlot(rawSlot)){
				if (rawSlot == 2) {
					lore.clear();
					if (anvil.getContents()[0].hasItemMeta()) {
						lore.addAll(anvil.getContents()[0].getItemMeta().getLore());
					}
					if (anvil.getContents()[1].hasItemMeta()) {
						lore.addAll(anvil.getContents()[1].getItemMeta().getLore());
					}
					
					ItemStack is = e.getCurrentItem();
					if (is != null) {
						ItemMeta im = is.getItemMeta();
						im.setLore(lore);
						ItemMeta newim = enchStack(im);
						e.getCurrentItem().setItemMeta(newim);
					}
				}
			}
		}
	}
	public ItemMeta enchStack(ItemMeta im) {
		HashMap<String, Integer> enchants = new HashMap<String, Integer>();
		ArrayList<String> newlore = new ArrayList<>();
		
		for (String str : im.getLore()) {
			String[] split = str.split(" ");
			if (enchants.containsKey(split[0])) {
				if (enchants.get(split[0]) == EnchConverter.li(split[1])) {
					int newlvl = EnchConverter.li(split[1]) + 1;
					newlvl = EnchConverter.check(split[0], newlvl);
					enchants.put(split[0], newlvl);
				}else{
					int newlvl = Math.max(enchants.get(split[0]), EnchConverter.li(split[1]));
					enchants.put(split[0], newlvl);
				}
			}else{
				enchants.put(split[0], EnchConverter.li(split[1]) );
			}
		}
		
		for (String key : enchants.keySet()) {
			newlore.add(key + " " + EnchConverter.il( enchants.get(key)) );
		}
		EnchConverter.uncombineable(newlore, im.getEnchants());
		im.setLore(newlore);
		return im;
	}
}
