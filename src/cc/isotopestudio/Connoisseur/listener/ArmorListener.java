package cc.isotopestudio.Connoisseur.listener;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.names.ArmorType;

public class ArmorListener implements Listener {

	@EventHandler
	public void onEquip(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();
	}

	public static ArrayList<ArmorType> getArmorAttri(Player player) {
		
		ArrayList<ArmorType> list = new ArrayList<ArmorType>();
		/*
		for (ItemStack item : player.getInventory().getArmorContents()) {
			list.addAll(ArmorType.getType(item));
		}
		*/
		return list;
	}

}
