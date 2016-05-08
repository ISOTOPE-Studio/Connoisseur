package cc.isotopestudio.Connoisseur.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.names.ArmorType;
import cc.isotopestudio.Connoisseur.names.WeaponType;
import cc.isotopestudio.Connoisseur.obj.ArmorConnoObj;
import cc.isotopestudio.Connoisseur.obj.WeaponConnoObj;

public class UnbreakableListener implements Listener {

	@EventHandler
	public void onBreak(PlayerItemDamageEvent event) {
		ItemStack item = event.getItem();
		ArmorConnoObj info1 = ArmorType.getType(item);
		WeaponConnoObj info2 = WeaponType.getType(item);
		if (info1 != null && info1.isUnbreakable() || info2 != null && info2.isUnbreakable()) {
			event.setCancelled(true);
		}
	}

}
