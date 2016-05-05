package cc.isotopestudio.Connoisseur.listener;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import cc.isotopestudio.Connoisseur.names.WeaponType;
import cc.isotopestudio.Connoisseur.obj.WeaponConnoObj;
import cc.isotopestudio.Connoisseur.utli.S;

public class WeaponListener implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getDamager() instanceof Player)) {
			return;
		}
		if (!(event.getEntity() instanceof LivingEntity)) {
			return;
		}
		Player player = (Player) event.getDamager();
		ItemStack item = player.getItemInHand();
		WeaponConnoObj info = WeaponType.getType(item);
		if (info == null)
			return;
		LivingEntity damagee = (LivingEntity) event.getEntity();
		for (WeaponType attri : info.getAttriList()) {
			if (attri.isPercentile()) {
				if (info.getParameters().get(attri) < Math.random())
					continue;
			}
			switch (attri) {
			case CRITICAL: {
				onCtitical(event);
				player.sendMessage(S.toPrefixGreen("±©»÷!"));
				break;
			}
			case ADDITIONAL: {
				onAdditional(event, info.getParameters().get(attri));
				break;
			}
			case DEADLY: {
				onDeadly(damagee);
				player.sendMessage(S.toPrefixGreen("ÖÂÃüÒ»»÷!"));
				break;
			}
			case DIRECT: {
				onDirect(damagee, info.getParameters().get(attri));
				break;
			}
			case FROZEN: {
				onFrozen(damagee);
				player.sendMessage(S.toPrefixGreen("½ûïÀ¶Ô·½!"));
				break;
			}
			case VAMPIRIC: {
				onVampiric(player, damagee);
				player.sendMessage(S.toPrefixGreen("ÎüÑª!"));
				break;
			}
			default:
				break;
			}
		}
	}

	void onCtitical(EntityDamageByEntityEvent event) {
		System.out.print("--onCtitical--");
		System.out.print(event.getDamage());
		event.setDamage(event.getDamage() * 2);
		System.out.print(event.getDamage());
		System.out.print("--onCtitical--");
	}

	void onAdditional(EntityDamageByEntityEvent event, double add) {
		System.out.print("--onAdditional--");
		System.out.print(event.getDamage());
		event.setDamage(event.getDamage() + add);
		System.out.print(event.getDamage());
		System.out.print("--onAdditional--");
	}

	void onDeadly(LivingEntity entity) {
		entity.setHealth(0);
		System.out.print("--onDeadly--");
	}

	void onDirect(LivingEntity entity, double damage) {
		System.out.print("--onDirect--");
		System.out.print(entity.getHealth());
		double health = entity.getHealth() - damage;
		if (health < 0)
			health = 0;
		entity.setHealth(health);
		System.out.print(entity.getHealth());
		System.out.print("--onDirect--");
	}

	void onFrozen(LivingEntity entity) {
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 50, false));
		System.out.print("--onFrozen--");
	}

	void onVampiric(Player player, LivingEntity entity) {
		double eHealth = entity.getHealth() - 2;
		if (eHealth < 0)
			eHealth = 0;
		entity.setHealth(eHealth);
		double pHealth = entity.getHealth() + 2;
		if (pHealth > player.getMaxHealth())
			pHealth = player.getMaxHealth();
		entity.setHealth(pHealth);
		System.out.print("--onVampiric--");
	}

}
