package cc.isotopestudio.Connoisseur.names;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public enum ArmorType implements AttributionType {
	LIFE("生命", 1, 2, 3, 4, 7, 9, 15, false, "生命值"),

	DODGE("闪避", 0.05, 0.8, 0.12, 0.15, 0.19, 0.22, 0.27, true, "躲避攻击"),

	RESISTENCE("抵抗", 0.07, 0.10, 0.14, 0.17, 0.22, 0.27, 0.35, true, "免疫 70% 的攻击"),

	INVINCIBILITY("无敌", 0.05, 0.8, 0.12, 0.15, 0.19, 0.22, 0.27, true, "3秒无敌"),

	BOUNCE("反甲", 1, 2, 3, 4, 7, 9, 12, false, "35%反弹伤害"),

	SPEED("速度", 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, true, "行走速度");

	final private String name;
	final private HashMap<LevelType, Double> attriLevMap;
	final private String description;
	final private boolean isPercentile;

	ArmorType(String name, double G, double F, double E, double D, double C, double B, double A, boolean isPercentile,
			String des) {
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
		this.isPercentile = isPercentile;
	}

	public String toString() {
		return S.toBoldGold(name);
	}

	public String getDescription() {
		return S.toGreen(description);
	}

	public double getAttri(LevelType type) {
		return attriLevMap.get(type);
	}

	public boolean isPercentile() {
		return isPercentile;
	}

	public static boolean isArmor(Material item) {
		if (item.equals(Material.DIAMOND_HELMET) || item.equals(Material.DIAMOND_CHESTPLATE)
				|| item.equals(Material.DIAMOND_LEGGINGS) || item.equals(Material.DIAMOND_BOOTS)
				|| item.equals(Material.GOLD_HELMET) || item.equals(Material.GOLD_CHESTPLATE)
				|| item.equals(Material.GOLD_LEGGINGS) || item.equals(Material.GOLD_BOOTS)
				|| item.equals(Material.IRON_HELMET) || item.equals(Material.IRON_CHESTPLATE)
				|| item.equals(Material.IRON_LEGGINGS) || item.equals(Material.IRON_BOOTS)
				|| item.equals(Material.LEATHER_HELMET) || item.equals(Material.LEATHER_CHESTPLATE)
				|| item.equals(Material.LEATHER_LEGGINGS) || item.equals(Material.LEATHER_BOOTS)
				|| item.equals(Material.CHAINMAIL_HELMET) || item.equals(Material.CHAINMAIL_CHESTPLATE)
				|| item.equals(Material.CHAINMAIL_LEGGINGS) || item.equals(Material.CHAINMAIL_BOOTS))
			return true;
		return false;
	}

	public static ArmorType getRandom() {
		int num = MathUtli.random(0, ArmorType.values().length - 1);
		return ArmorType.values()[num];
	}

	public static ArrayList<ArmorType> genType(LevelType lvType) {
		ArrayList<ArmorType> list = new ArrayList<ArmorType>();
		int num = MathUtli.random(1, lvType.getMaxAttrNum());
		int count = 0;
		while (count < num) {
			loop: {
				ArmorType type = getRandom();
				for (ArmorType temp : list) {
					System.out.println(temp + " " + type + " " + temp.equals(type));
					if (temp.equals(type)) {
						break loop;
					}
				}
				list.add(type);
				count++;
			}
		}
		return list;
	}

	public static ArrayList<ArmorType> getType(ItemStack item) {
		ArrayList<ArmorType> list = new ArrayList<ArmorType>();
		try {
			for (String lore : item.getItemMeta().getLore())
				for (ArmorType type : values())
					if (lore.contains(type.toString()))
						list.add(type);
		} catch (Exception e) {
		}
		return list;
	}

	public boolean equals(ArmorType another) {
		return this.name.equals(another.name);
	}

}
