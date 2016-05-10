package cc.isotopestudio.Connoisseur.listener;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import cc.isotopestudio.Connoisseur.names.ArmorType;
import cc.isotopestudio.Connoisseur.obj.ArmorConnoObj;
import cc.isotopestudio.Connoisseur.utli.S;

public class ArmorListener implements Listener {

	private final Plugin plugin;

	public ArmorListener(Plugin plugin) {
		this.plugin = plugin;
	}

	/*
	 * @EventHandler(priority = EventPriority.LOW) public void
	 * onTest(ArmorEquipEvent event) { ArmorConnoObj newItem = null, oldItem =
	 * null; if (event.getNewArmorPiece() != null &&
	 * event.getNewArmorPiece().getType() != Material.AIR) { newItem =
	 * ArmorType.getType(event.getNewArmorPiece()); } if
	 * (event.getOldArmorPiece() != null && event.getOldArmorPiece().getType()
	 * != Material.AIR) { oldItem = ArmorType.getType(event.getOldArmorPiece());
	 * } Player player = event.getPlayer(); new BukkitRunnable() {
	 * 
	 * @Override public void run() { onEquip(player); }
	 * }.runTaskLater(this.plugin, 1); /* if (newItem == null && oldItem ==
	 * null) { return; } else if (newItem == null) { if
	 * (oldItem.getAttriList().contains(ArmorType.LIFE)) { player.sendMessage(
	 * "脱下装备 生命属性：" + oldItem.getParameters().get(ArmorType.LIFE));
	 * addHealth(player, -oldItem.getParameters().get(ArmorType.LIFE)); } } else
	 * if (oldItem == null) { if
	 * (newItem.getAttriList().contains(ArmorType.LIFE)) { addHealth(player,
	 * newItem.getParameters().get(ArmorType.LIFE)); player.sendMessage(
	 * "穿上装备 生命属性：" + newItem.getParameters().get(ArmorType.LIFE)); } } else {
	 * double health = 0; if (oldItem.getAttriList().contains(ArmorType.LIFE)) {
	 * health -= oldItem.getParameters().get(ArmorType.LIFE); } if
	 * (newItem.getAttriList().contains(ArmorType.LIFE)) { health +=
	 * newItem.getParameters().get(ArmorType.LIFE); } if (health != 0) {
	 * player.sendMessage("脱下/穿上装备 生命属性变化：" + health); addHealth(player,
	 * health); } }
	 * 
	 * }
	 */

	@EventHandler
	public void onEquipEvent(PlayerInteractEvent event) {
		if (!(event.getPlayer() instanceof Player))
			return;
		new BukkitRunnable() {
			@Override
			public void run() {
				onEquip(event.getPlayer());
			}
		}.runTaskLater(this.plugin, 1);
	}

	@EventHandler
	public void onEquipEvent(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player))
			return;
		Player player = (Player) event.getWhoClicked();
		new BukkitRunnable() {

			@Override
			public void run() {
				onEquip(player);
			}
		}.runTaskLater(this.plugin, 1);
		new BukkitRunnable() {

			@Override
			public void run() {
				onEquip(player);
			}
		}.runTaskLater(this.plugin, 20);
		new BukkitRunnable() {

			@Override
			public void run() {
				onEquip(player);
			}
		}.runTaskLater(this.plugin, 20 * 5);
	}

	private void onEquip(Player player) {
		ArmorConnoObj info = getArmorAttri(player);
		if (info == null)
			return;
		for (ArmorType attri : info.getAttriList()) {
			switch (attri) {
			case BOUNCE:
				break;
			case DODGE:
				break;
			case INVINCIBILITY:
				break;
			// case LIFE: {
			// break;
			// }
			case RESISTANCE:
				break;
			case SPEED: {
				setSpeed(player, info.getParameters().get(attri));
				break;
			}
			default:
				break;
			}
		}
		if (!info.getAttriList().contains(ArmorType.SPEED)) {
			removeSpeed(player);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();

		ArmorConnoObj info = getArmorAttri(player);
		if (info == null)
			return;
		LivingEntity damagee;
		if (event.getDamager() instanceof Arrow) {
			if (((Arrow) event.getDamager()).getShooter() instanceof LivingEntity)
				damagee = (LivingEntity) ((Arrow) event.getDamager()).getShooter();
			else
				return;
		} else if (event.getDamager() instanceof Fireball) {
			if (((Fireball) event.getDamager()).getShooter() instanceof LivingEntity)
				damagee = (LivingEntity) ((Fireball) event.getDamager()).getShooter();
			else
				return;
		} else if (event.getDamager() instanceof LivingEntity) {
			damagee = (LivingEntity) event.getDamager();
		} else {
			return;
		}
		for (ArmorType attri : info.getAttriList()) {
			if (attri.isPercentile()) {
				if (info.getParameters().get(attri) < Math.random())
					continue;
			}
			switch (attri) {
			case BOUNCE: {
				if (Math.random() <= 0.10) {
					onBounce(damagee, info.getParameters().get(attri));
					player.sendMessage(S.toPrefixGreen("反弹伤害!"));
				}
				break;
			}
			case DODGE: {
				onDodge(event);
				player.sendMessage(S.toPrefixGreen("闪避!"));
				break;
			}
			case INVINCIBILITY: {
				onInvincibility(player);
				player.sendMessage(S.toPrefixGreen("无敌 3 秒!"));
				break;
			}
				// case LIFE: {
				// break;
				// }
			case RESISTANCE: {
				onResistance(event);
				player.sendMessage(S.toPrefixGreen("抵抗!"));
				break;
			}
			case SPEED: {
				break;
			}
			default:
				break;
			}
		}
		UnbreakableListener.onBreak(player);
	}

	public static ArmorConnoObj getArmorAttri(Player player) {
		ArmorConnoObj result = null;
		boolean first = true;
		for (ItemStack item : player.getInventory().getArmorContents()) {
			if (first) {
				result = ArmorType.getType(item);
				if (result != null)
					first = false;
			} else if (ArmorType.getType(item) != null)
				result.addAll(ArmorType.getType(item));
		}
		return result;
	}

	void onDodge(EntityDamageByEntityEvent event) {
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

	void onInvincibility(Player player) {
		player.setNoDamageTicks(3 * 20);
		System.out.print("--onInvincibility--");
	}

	void onBounce(LivingEntity entity, double damage) {
		entity.damage(damage);
		System.out.print("--onBounce--");
	}

	void setSpeed(Player player, double percent) {
		float speed = getMoveSpeed((float) (1 + percent));
		player.setWalkSpeed(getRealMoveSpeed(speed));
	}

	void addHealth(Player player, double add) {
		new BukkitRunnable() {
			@Override
			public void run() {
				player.setMaxHealth(player.getMaxHealth() + add);
				player.sendMessage("调整血量 " + add);
			}
		}.runTaskLater(this.plugin, 5);
	}

	void removeSpeed(Player player) {
		player.setWalkSpeed(0.2f);
	}

	private float getMoveSpeed(float userSpeed) {
		if (userSpeed > 10f) {
			userSpeed = 10f;
		} else if (userSpeed < 0.0001f) {
			userSpeed = 0.0001f;
		}
		return userSpeed;
	}

	private float getRealMoveSpeed(final float userSpeed) {
		final float defaultSpeed = 0.2f;
		float maxSpeed = 1f;
		if (userSpeed < 1f) {
			return defaultSpeed * userSpeed;
		} else {
			float ratio = ((userSpeed - 1) / 9) * (maxSpeed - defaultSpeed);
			return ratio + defaultSpeed;
		}
	}

}
