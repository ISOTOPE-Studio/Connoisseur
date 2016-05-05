package cc.isotopestudio.Connoisseur.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.names.ArmorType;
import cc.isotopestudio.Connoisseur.obj.ArmorConnoObj;
import cc.isotopestudio.Connoisseur.utli.S;

public class ArmorListener implements Listener {

	@EventHandler
	public void onEquip(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player))
			return;
		Player player = (Player) event.getWhoClicked();
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
		if (!(event.getDamager() instanceof LivingEntity)) {
			return;
		}
		Player player = (Player) event.getEntity();
		ArmorConnoObj info = getArmorAttri(player);
		if (info == null)
			return;
		LivingEntity damagee = (LivingEntity) event.getEntity();
		for (ArmorType attri : info.getAttriList()) {
			if (attri.isPercentile()) {
				if (info.getParameters().get(attri) < Math.random())
					continue;
			}
			switch (attri) {
			case BOUNCE: {
				onBounce(event, damagee);
				player.sendMessage(S.toPrefixGreen("·´µ¯ÉËº¦!"));
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
		player.sendMessage(getArmorAttri(player).toString());
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

	void onBounce(EntityDamageByEntityEvent event, LivingEntity entity) {
		double damage = event.getDamage();
		event.setDamage(damage);
		System.out.print("--onBounce--");
	}

	void setSpeed(Player player, double percent) {
		float speed = getMoveSpeed((float) (1 + percent));
		player.setWalkSpeed(getRealMoveSpeed(speed));
	}

	void setHealth(Player player, double add) {
		player.setMaxHealth(player.getMaxHealth() + add);
	}

	void removeSpeed(Player player) {
		player.setWalkSpeed(0.2f);
	}

	void removeHealth(Player player) {
		player.setMaxHealth(20);
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
