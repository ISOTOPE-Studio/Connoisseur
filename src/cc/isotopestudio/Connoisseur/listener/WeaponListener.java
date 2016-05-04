package cc.isotopestudio.Connoisseur.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class WeaponListener implements Listener {
	
	@EventHandler
	public void onEquip(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();
	}

}
