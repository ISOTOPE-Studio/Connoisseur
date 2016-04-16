package cc.isotopestudio.Connoisseur.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.names.ScrollType;

public class ConnoListener implements Listener {

	@EventHandler
	public void onConno(PlayerInteractEvent event) {
		ItemStack scroll = event.getItem();
		Player player = event.getPlayer();
		ScrollType type = ScrollType.getType(scroll);
		if (type == null)
			return;
		player.sendMessage(type.getRandomLevelType().toString());
		if (type.equals(ScrollType.A)) {

		} else if (type.equals(ScrollType.B)) {

		} else if (type.equals(ScrollType.C)) {

		} else if (type.equals(ScrollType.X)) {

		}
	}

}
