package cc.isotopestudio.Connoisseur.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cc.isotopestudio.Connoisseur.names.ArmorType;
import cc.isotopestudio.Connoisseur.names.LevelType;
import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.names.WeaponType;
import cc.isotopestudio.Connoisseur.obj.*;
import cc.isotopestudio.Connoisseur.utli.S;

public class ConnoListener implements Listener {

	HashMap<String, ScrollType> progress = new HashMap<String, ScrollType>();

	@EventHandler
	public void onClickScroll(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		ArmorType.getType(player.getItemInHand());
		if (progress.get(playerName) == null) {
			// click scroll
			ItemStack scroll = event.getItem();
			ScrollType stype = ScrollType.getType(scroll);
			if (stype == null)
				return;
			if (scroll.getAmount() != 1) {
				player.sendMessage(S.toPrefixRed("请只拿着一个鉴定/清洗卷"));
				return;
			}
			progress.put(playerName, stype);
			if (stype == ScrollType.X)
				player.sendMessage(S.toPrefixYellow("现在请右键要清洗的装备(不可撤回)"));
			else
				player.sendMessage(S.toPrefixYellow("现在请右键要鉴定的装备"));
		} else {
			// continue on gear
			ItemStack gear = event.getItem();
			ScrollType type = progress.get(playerName);
			progress.remove(player.getName());

			if (!player.getInventory().contains(ScrollType.getItem(type))) {
				player.sendMessage(S.toPrefixRed("你没有" + type));
				return;
			}
			if (gear == null) {
				player.sendMessage(S.toPrefixRed("鉴定失败(这不是有效物品)"));
				return;
			}
			if (type == ScrollType.X) {
				if (ArmorType.isArmor(gear.getType()) || WeaponType.isWeapon(gear.getType())) {
					if (ArmorType.getType(gear) != null || WeaponType.getType(gear) != null) {
						int start = -1, end = -1;
						ItemMeta meta = gear.getItemMeta();
						List<String> list = meta.getLore();
						for (int i = 0; i < list.size(); i++) {
							if (start == -1 && list.get(i).equals(ArmorConnoObj.header)) {
								start = i;
							} else if (list.get(i).equals(ArmorConnoObj.header)) {
								end = i;
							}
						}
						for (int i = start; i <= end; i++) {
							list.remove(start);
						}
						meta.setLore(list);
						gear.setItemMeta(meta);
						player.setItemInHand(gear);
						int pos = player.getInventory().first(ScrollType.getItem(type));
						player.getInventory().setItem(pos, null);
						player.sendMessage(S.toPrefixGreen("清洗成功!"));
						return;
					}
				}
				player.sendMessage(S.toPrefixRed("清洗失败(这不是有效物品)"));
				return;
			} else if (ArmorType.isArmor(gear.getType())) {
				if (ArmorType.getType(gear) != null) {
					player.sendMessage(S.toPrefixRed("鉴定失败(已经鉴定的物品)"));
					return;
				}
				ArmorConnoObj result = new ArmorConnoObj(type, player.getName());
				int pos = player.getInventory().first(ScrollType.getItem(type));
				player.getInventory().setItem(pos, null);
				ItemMeta meta = gear.getItemMeta();
				List<String> lore = meta.getLore() == null ? new ArrayList<String>() : meta.getLore();
				lore.addAll(result.getLore());
				meta.setLore(lore);
				gear.setItemMeta(meta);
				player.setItemInHand(gear);
				player.sendMessage(S.toPrefixGreen("鉴定成功!"));
				if (result.getLevelType() == LevelType.A) {
					Bukkit.broadcastMessage(S.toPrefixYellow("一道冲天的光闪耀全服，"
							+ player.getName() + "的" + (gear.getItemMeta().hasDisplayName()
									? gear.getItemMeta().getDisplayName() : "装备" + "是一把远古遗失的")
							+ LevelType.A.toString()));
				}
			}

			else if (WeaponType.isWeapon(gear.getType())) {
				if (WeaponType.getType(gear) != null) {
					player.sendMessage(S.toPrefixRed("鉴定失败(已经鉴定的物品)"));
					return;
				}
				WeaponConnoObj result = new WeaponConnoObj(type, player.getName());
				int pos = player.getInventory().first(ScrollType.getItem(type));
				player.getInventory().setItem(pos, null);
				ItemMeta meta = gear.getItemMeta();
				List<String> lore = meta.getLore() == null ? new ArrayList<String>() : meta.getLore();
				lore.addAll(result.getLore());
				meta.setLore(lore);
				gear.setItemMeta(meta);
				player.setItemInHand(gear);
				player.sendMessage(S.toPrefixGreen("鉴定成功!"));
				if (result.getLevelType() == LevelType.A) {
					Bukkit.broadcastMessage(S.toPrefixYellow("一道冲天的光闪耀全服，"
							+ player.getName() + "的" + (gear.getItemMeta().hasDisplayName()
									? gear.getItemMeta().getDisplayName() : "武器" + "是一把远古遗失的")
							+ LevelType.A.toString()));
				}
			}

			else {
				player.sendMessage(S.toPrefixRed("鉴定失败(这不是武器)"));
			}

		}
	}

}
