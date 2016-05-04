package cc.isotopestudio.Connoisseur.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cc.isotopestudio.Connoisseur.names.ArmorConnoObj;
import cc.isotopestudio.Connoisseur.names.ArmorType;
import cc.isotopestudio.Connoisseur.names.LevelType;
import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.names.WeaponType;
import cc.isotopestudio.Connoisseur.utli.S;

public class ConnoListener implements Listener {

	HashMap<String, ScrollType> progress = new HashMap<String, ScrollType>();

	@EventHandler
	public void onClickScroll(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		if (progress.get(playerName) == null) {
			// click scroll
			ItemStack scroll = event.getItem();
			ScrollType stype = ScrollType.getType(scroll);
			if (stype == null)
				return;
			if (scroll.getAmount() != 1) {
				player.sendMessage(S.toPrefixRed("��ֻ����һ��������"));
				return;
			}
			progress.put(playerName, stype);
			player.sendMessage(S.toPrefixYellow("�������Ҽ�Ҫ������װ��"));
		} else {
			// continue on gear
			ItemStack gear = event.getItem();
			ScrollType type = progress.get(playerName);
			progress.remove(player.getName());

			if (!player.getInventory().contains(ScrollType.getItem(type))) {
				player.sendMessage(S.toPrefixRed("��û��" + type));
				return;
			}
			if (gear == null)
				return;
			if (ArmorType.isArmor(gear.getType())) {
				ArmorConnoObj result = new ArmorConnoObj(type, player.getName());
				int pos = player.getInventory().first(ScrollType.getItem(type));
				player.getInventory().setItem(pos, null);
				ItemMeta meta = gear.getItemMeta();
				List<String> lore = meta.getLore() == null ? new ArrayList<String>() : meta.getLore();
				lore.addAll(result.getLore());
				meta.setLore(lore);
				gear.setItemMeta(meta);
				player.setItemInHand(gear);
			}

			else if (WeaponType.isWeapon(gear.getType())) {
				/*
				 * ArrayList<WeaponType> attriList = WeaponType.getType(lvType);
				 * player.sendMessage(lvType + ": " + lvType.getMaxAttrNum() +
				 * attriList.toString());
				 */
			}

			else {
				player.sendMessage(S.toPrefixRed("����ʧ��(�ⲻ������)"));
			}

		}
	}

}
