package cc.isotopestudio.Connoisseur.listener;

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
		new BukkitRunnable() {
			@Override
			public void run() {
				onEquip(player);
			}
		}.runTaskLater(this.plugin, 20 * 10);
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
			case LIFE: {
				setHealth(player, info.getParameters().get(attri));
				break;
			}
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
		if (!info.getAttriList().contains(ArmorType.LIFE)) {
			removeHealth(player);
		}
		if (!info.getAttriList().contains(ArmorType.SPEED)) {
			removeSpeed(player);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();

		player.sendMessage(getArmorAttri(player).toString());
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
				if (Math.random() <= 0.20) {
					onBounce(damagee, info.getParameters().get(attri));
					player.sendMessage(S.toPrefixGreen("·´µ¯ÉËº¦!"));
				}
				break;
			}
			case DODGE: {
				onDodge(event);
				player.sendMessage(S.toPrefixGreen("ÉÁ±Ü!"));
				break;
			}
			case INVINCIBILITY: {
				onInvincibility(player);
				player.sendMessage(S.toPrefixGreen("ÎÞµÐ 3 Ãë!"));
				break;
			}
			case LIFE: {
				break;
			}
			case RESISTANCE: {
				onResistance(event);
				player.sendMessage(S.toPrefixGreen("µÖ¿¹!"));
				break;
			}
			case SPEED: {
				break;
			}
			default:
				break;
			}
		}
	}

	static ArmorConnoObj getArmorAttri(Player player) {
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

	void setHealth(Player player, double add) {
		double percent = player.getHealth() / player.getMaxHealth();
		player.setMaxHealth(20 + add);
		new BukkitRunnable() {
			@Override
			public void run() {
				player.setHealth(player.getMaxHealth() * percent);
			}
		}.runTaskLater(this.plugin, 1);
	}

	void removeHealth(Player player) {
		double percent = player.getHealth() / player.getMaxHealth();
		player.setMaxHealth(20);
		new BukkitRunnable() {
			@Override
			public void run() {
				player.setHealth(20 * percent);
			}
		}.runTaskLater(this.plugin, 1);
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
