package cc.isotopestudio.Connoisseur.listener;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.names.ArmorType;
import cc.isotopestudio.Connoisseur.names.WeaponType;
import cc.isotopestudio.Connoisseur.obj.ArmorConnoObj;
import cc.isotopestudio.Connoisseur.obj.WeaponConnoObj;

public class UnbreakableListener {

	public static void onBreak(Player player) {
		ItemStack item = player.getItemInHand();
		if (isUnbreakable(item)) {
			item.setDurability((short) 0);
			player.setItemInHand(item);
		}
		ItemStack[] items = player.getInventory().getArmorContents();
		for (int i = 0; i < 4; i++) {
			if (isUnbreakable(items[i])) {
				items[i].setDurability((short) 0);
			}
		}
		player.getInventory().setArmorContents(items);
	}

	private static boolean isUnbreakable(ItemStack item) {
		if (item == null)
			return false;
		ArmorConnoObj info1 = ArmorType.getType(item);
		WeaponConnoObj info2 = WeaponType.getType(item);
		if (info1 != null && info1.isUnbreakable() || info2 != null && info2.isUnbreakable())
			return true;
		return false;
	}

}
