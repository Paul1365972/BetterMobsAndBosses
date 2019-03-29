package command_executer;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;


public class Command_xpbank implements CommandExecutor{
	
	public void  sendPacket201(Player pl, String pn, boolean co, int pi){
		
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("xpbank")) {
				ItemStack handbook = new ItemStack(Material.WRITTEN_BOOK);
				BookMeta handbookmeta = (BookMeta) handbook.getItemMeta();
				handbookmeta.addPage("Hallo §6"+ p.getName() + ",§r\nUm eine §1XP-Bank§r\naufzubauen muss man nur einen §1Kessel§r\nrechts-klicken!\n\n\n\n\n\n\n\n§2Viel Spaß damit!\n§4By Paul1365972");
				handbookmeta.setAuthor("Paul1365972");
				handbookmeta.setDisplayName("XP-Bank Anleitung");
				handbookmeta.setTitle("XP-Bank Anleitung");
				handbook.setItemMeta(handbookmeta);
				p.getInventory().addItem(handbook);
			}
		}else {
			sender.sendMessage("[lp_mit_kev] Bitte führe diesen Befehl als Spieler aus!");
		}
		return true;
	}

}
