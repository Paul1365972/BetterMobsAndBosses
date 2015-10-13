package de.paul1365972;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

@SuppressWarnings("unused")
public class ChunkGenerator implements Listener{
	/*Random rdm = new Random();
	@EventHandler
	public void genrate(ChunkLoadEvent e) {
		if (e.isNewChunk()) {
			if (rdm.nextInt(1)+1 ==1) {
				for (int k = 128; k > 64; k--) {
					System.out.println(k);
					if (e.getChunk().getBlock(7, k, 7).getType().compareTo(Material.AIR) != 0) {
						System.out.println(k);
						for (int j = 1; j < 15; j++) {
							e.getChunk().getBlock(6, k+j, 6).setType(Material.NETHERRACK);
							e.getChunk().getBlock(6, k+j, 7).setType(Material.NETHERRACK);
							e.getChunk().getBlock(6, k+j, 8).setType(Material.NETHERRACK);
							e.getChunk().getBlock(7, k+j, 6).setType(Material.NETHERRACK);
							e.getChunk().getBlock(7, k+j, 7).setType(Material.NETHERRACK);
							e.getChunk().getBlock(7, k+j, 8).setType(Material.NETHERRACK);
							e.getChunk().getBlock(8, k+j, 6).setType(Material.NETHERRACK);
							e.getChunk().getBlock(8, k+j, 7).setType(Material.NETHERRACK);
							e.getChunk().getBlock(8, k+j, 8).setType(Material.NETHERRACK);
						}
						break;
					}
				}
			}
		}
	}*/
}
