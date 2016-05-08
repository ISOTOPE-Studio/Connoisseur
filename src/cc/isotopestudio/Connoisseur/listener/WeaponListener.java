package cc.isotopestudio.Connoisseur.listener;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import cc.isotopestudio.Connoisseur.names.LevelType;
import cc.isotopestudio.Connoisseur.names.WeaponType;
import cc.isotopestudio.Connoisseur.obj.WeaponConnoObj;
import cc.isotopestudio.Connoisseur.utli.ParticleEffect;
import cc.isotopestudio.Connoisseur.utli.ParticleEffect.OrdinaryColor;
import cc.isotopestudio.Connoisseur.utli.S;

public class WeaponListener implements Listener {
	private final Plugin plugin;

	public WeaponListener(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onShoot(ProjectileLaunchEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if (!(event.getEntity().getShooter() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity().getShooter();
		ItemStack item = player.getItemInHand();
		WeaponConnoObj info = WeaponType.getType(item);
		if (info == null)
			return;
		plugin.getServer().getPluginManager().registerEvents(new ProjectileListener(event.getEntity(), info), plugin);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.isCancelled()) {
			return;
		}
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
		onAttri(event, player, info, damagee);
	}

	public static void onAttri(EntityDamageByEntityEvent event, Player player, WeaponConnoObj info,
			LivingEntity damagee) {
		if (info.getLevelType().equals(LevelType.A)) {
			Location loc = player.getEyeLocation();
			ParticleEffect.OrdinaryColor c = new ParticleEffect.OrdinaryColor(255, 1, 1);
			for (int i = 0; i < 40; i++) {
				ParticleEffect.REDSTONE.display(c, loc.clone().add((Math.random() * 3 - 1.5), (Math.random() * 1 - 0.5),
						(Math.random() * 3 - 1.5)), 20);
			}
			ParticleEffect.FLAME.display(1, 1, 1, 0.05F, 20, player.getEyeLocation(), 20);
		}
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
				onDeadly(event);
				player.sendMessage(S.toPrefixGreen("ÖÂÃüÒ»»÷!"));
				break;
			}
			case DIRECT: {
				onDirect(damagee, info.getParameters().get(attri));
				break;
			}
			case FROZEN: {
				onFrozen(damagee);
				player.sendMessage(S.toPrefixGreen("Ñ£ÔÎ¶Ô·½!"));
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

	static void onCtitical(EntityDamageByEntityEvent event) {
		System.out.print("--onCtitical--");
		System.out.print(event.getDamage());
		event.setDamage(event.getDamage() * 2);
		System.out.print(event.getDamage());
		System.out.print("--onCtitical--");
	}

	static void onAdditional(EntityDamageByEntityEvent event, double add) {
		System.out.print("--onAdditional--");
		System.out.print(event.getDamage());
		event.setDamage(event.getDamage() + add);
		System.out.print(event.getDamage());
		System.out.print("--onAdditional--");
	}

	static void onDeadly(EntityDamageByEntityEvent event) {
		System.out.print("--onDeadly--");
		System.out.print(event.getDamage());
		event.setDamage(event.getDamage() * 20);
		System.out.print(event.getDamage());
		System.out.print("--onDeadly--");
	}

	static void onDirect(LivingEntity entity, double damage) {
		System.out.print("--onDirect--");
		System.out.print(entity.getHealth());
		double health = entity.getHealth() - damage;
		if (health < 0)
			health = 0;
		entity.setHealth(health);
		System.out.print(entity.getHealth());
		System.out.print("--onDirect--");
	}

	static void onFrozen(LivingEntity entity) {
		entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 50, false));
		System.out.print("--onFrozen--");
	}

	static void onVampiric(Player player, LivingEntity entity) {
		double eHealth = entity.getHealth() - 2;
		if (eHealth < 0)
			eHealth = 0;
		entity.setHealth(eHealth);
		double pHealth = player.getHealth() + 2;
		if (pHealth > player.getMaxHealth())
			pHealth = player.getMaxHealth();
		player.setHealth(pHealth);
		System.out.print("--onVampiric--");
	}

	public class ProjectileListener implements Listener {
		private final WeaponConnoObj info;
		private final Projectile projectile;

		public ProjectileListener(Projectile projectile, WeaponConnoObj info) {
			this.info = info;
			this.projectile = projectile;
		}

		@EventHandler
		public void onHit(EntityDamageByEntityEvent event) {
			if (!(event.getDamager() == projectile)) {
				return;
			}
			WeaponListener.onAttri(event, (Player) projectile.getShooter(), info, (LivingEntity) event.getEntity());
			HandlerList.unregisterAll(this);
		}

	}

}
