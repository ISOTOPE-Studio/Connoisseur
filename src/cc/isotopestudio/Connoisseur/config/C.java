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
				plugin.getLogger().severe(s + " ������Ʒ���޷���ӵ� ���� �б���");
				continue;
			}
			weapons.add(m);
		}
		for (String s : plugin.getConfig().getStringList("armor.type")) {
			Material m = getMaterial(s);
			if (m == null) {
				plugin.getLogger().severe(s + " ������Ʒ���޷���ӵ� ���� �б���");
				continue;
			}
			armors.add(m);
		}
		plugin.getLogger().info("������" + armors + ", ����: " + armors);
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
