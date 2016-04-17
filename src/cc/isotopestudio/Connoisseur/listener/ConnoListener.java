package cc.isotopestudio.Connoisseur.listener;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.names.ArmorType;
import cc.isotopestudio.Connoisseur.names.LevelType;
import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.utli.S;

public class ConnoListener implements Listener {

	HashMap<String, ScrollType> progress = new HashMap<String, ScrollType>();

	@EventHandler
	public void onClickScroll(PlayerInteractEvent event) {
		ItemStack scroll = event.getItem();
		Player player = event.getPlayer();
		String playerName = player.getName();
		ScrollType type = ScrollType.getType(scroll);
		if (type == null)
			return;
		if (progress.get(playerName) == null) {
			progress.put(playerName, type);
			player.sendMessage(S.toPrefixYellow("现在请右键要鉴定的装备"));
		}
	}

	@EventHandler
	public void onClickGear(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		if (progress.get(playerName) == null)
			return;
		ItemStack gear = event.getItem();
		ScrollType type = progress.get(playerName);
		LevelType lvType = type.getRandomLevelType();

		if (ArmorType.isArmor(gear.getType())) {
			ArrayList<ArmorType> attriList = ArmorType.getType(lvType);
			player.sendMessage(lvType + ": " + lvType.getMaxAttrNum() + attriList.toString());
		}

		else if (ArmorType.isArmor(gear.getType())) {

		}

		else {
			player.sendMessage(S.toPrefixRed("鉴定失败(这不是武器)"));
		}

		progress.remove(player.getName());
	}
}
