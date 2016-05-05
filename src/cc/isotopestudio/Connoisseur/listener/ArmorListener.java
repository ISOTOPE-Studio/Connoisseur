package cc.isotopestudio.Connoisseur.listener;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.names.ArmorType;

public class ArmorListener implements Listener {

	@EventHandler
	public void onEquip(InventoryClickEvent event) {
		ItemStack item = event.getCurrentItem();
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
	}

	public static ArrayList<ArmorType> getArmorAttri(Player player) {

		ArrayList<ArmorType> list = new ArrayList<ArmorType>();
		/*
		 * for (ItemStack item : player.getInventory().getArmorContents()) {
		 * list.addAll(ArmorType.getType(item)); }
		 */
		return list;
	}

	public void onDodge(EntityDamageByEntityEvent event) {
		event.setCancelled(true);
		System.out.print("--onDodge--");
	}

	void onResistance(EntityDamageByEntityEvent event) {
		System.out.print("--onResistance--");
		System.out.print(event.getDamage());
		event.setDamage(event.getDamage() * 0.7);
		System.out.print(event.getDamage());
		System.out.print("--onResistance--");
	}

	public void onInvincibility(Player player) {
		player.setNoDamageTicks(3 * 20);
		System.out.print("--onInvincibility--");
	}

	void onBounce(EntityDamageByEntityEvent event, LivingEntity entity) {
		double damage = event.getDamage();
		event.setDamage(damage);
		System.out.print("--onBounce--");
	}

	void setSpeed(Player player, double percent) {
		player.setWalkSpeed((float) (1 + percent));
	}

	void setHealth(Player player, double add) {
		player.setMaxHealth(player.getMaxHealth() + add);
	}
	
	void removeSpeed(Player player){
		player.setWalkSpeed(1F);
	}
	
	void removeHealth(Player player){
		player.setMaxHealth(20);
	}

}
