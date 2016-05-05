package cc.isotopestudio.Connoisseur.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

public class C {

	public static List<Material> weapons;
	public static List<Material> armors;

	public static void update(Plugin plugin) {
		weapons = new ArrayList<Material>();
		armors = new ArrayList<Material>();
		for (String s : plugin.getConfig().getStringList("weapon.type")) {
			Material m = getMaterial(s);
			if (m == null) {
				plugin.getLogger().severe(s + " 不是物品，无法添加到 武器 列表中");
				continue;
			}
			weapons.add(m);
		}
		for (String s : plugin.getConfig().getStringList("armor.type")) {
			Material m = getMaterial(s);
			if (m == null) {
				plugin.getLogger().severe(s + " 不是物品，无法添加到 护甲 列表中");
				continue;
			}
			armors.add(m);
		}
		plugin.getLogger().info("武器：" + armors + ", 护甲: " + armors);
	}

	@SuppressWarnings("deprecation")
	static Material getMaterial(String s) {
		int itemNumber = -1;
		try {
			itemNumber = Integer.parseInt(s);
		} catch (Exception e) {
		}
		if (itemNumber != -1) {
			return Material.getMaterial(itemNumber);
		} else {
			return Material.getMaterial(s);
		}
	}
}
