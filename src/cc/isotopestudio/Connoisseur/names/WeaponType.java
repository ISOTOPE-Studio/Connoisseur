package cc.isotopestudio.Connoisseur.names;

import java.util.HashMap;

import org.bukkit.Material;

public enum WeaponType {
	CRITICAL("暴击", 0.08, 0.12, 0.15, 0.17, 0.22, 0.25, 0.30, "双倍暴击"),

	VAMPIRIC("吸血", 0.05, 0.08, 0.12, 0.15, 0.20, 0.25, 0.30, "吸2点生命"),

	ADDITIONAL("附加攻击", 3, 5, 7, 9, 13, 17, 20, "增加攻击"),

	DIRECT("直接伤害", 1, 3, 5, 7, 9, 11, 15, "增加攻击"),

	DEADLY("致命一击", 0.002, 0.005, 0.007, 0.009, 0.015, 0.02, 0.03, "秒杀对手"),

	FROZEN("重击", 0.08, 0.12, 0.15, 0.17, 0.22, 0.25, 0.30, "使对手无法移动和攻击3秒");

	final private String name;
	final private HashMap<LevelType, Double> attriLevMap;
	final private String description;

	WeaponType(String name, double G, double F, double E, double D, double C, double B, double A, String des) {
		this.name = name;
		attriLevMap = new HashMap<LevelType, Double>();
		attriLevMap.put(LevelType.A, A);
		attriLevMap.put(LevelType.B, B);
		attriLevMap.put(LevelType.C, C);
		attriLevMap.put(LevelType.D, D);
		attriLevMap.put(LevelType.E, E);
		attriLevMap.put(LevelType.F, F);
		attriLevMap.put(LevelType.G, G);
		this.description = des;
	}

	public String toString() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getAttri(LevelType type) {
		return attriLevMap.get(type);
	}

	public static boolean isWeapon(Material item) {
		if (item.equals(Material.DIAMOND_SWORD) || item.equals(Material.DIAMOND_AXE)

				|| item.equals(Material.GOLD_SWORD) || item.equals(Material.GOLD_AXE)

				|| item.equals(Material.IRON_SWORD) || item.equals(Material.IRON_AXE)

				|| item.equals(Material.WOOD_SWORD) || item.equals(Material.WOOD_AXE))
			return true;
		return false;
	}
}
