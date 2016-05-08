package cc.isotopestudio.Connoisseur.names;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.config.C;
import cc.isotopestudio.Connoisseur.obj.ArmorConnoObj;
import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public enum ArmorType {
	//LIFE("生命", 1, 2, 3, 4, 7, 9, 15, false, "生命值"),

	DODGE("闪避", 0.01, 0.03, 0.05, 0.07, 0.10, 0.12, 0.15, true, "躲避攻击"),

	RESISTANCE("抵抗", 0.03, 0.05, 0.07, 0.10, 0.12, 0.15, 0.17, true, "免疫 70% 的攻击"),

	INVINCIBILITY("无敌", 0.005, 0.01, 0.03, 0.05, 0.07, 0.10, 0.12, true, "3秒无敌"),

	BOUNCE("反甲", 1, 2, 3, 4, 6, 8, 10, false, "10%反弹伤害"),

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
		for (Material m : C.armors)
			if (item == m)
				return true;
		return false;
	}

	public static ArmorType getRandom() {
		int num = MathUtli.random(0, ArmorType.values().length - 1);
		return ArmorType.values()[num];
	}

	public static ArrayList<ArmorType> genType(LevelType lvType) {
		ArrayList<ArmorType> list = new ArrayList<ArmorType>();
		int num = lvType.getAttrNum();
		int count = 0;
		while (count < num) {
			loop: {
				ArmorType type = getRandom();
				for (ArmorType temp : list) {
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

	public static ArmorConnoObj getType(ItemStack item) {
		ArrayList<ArmorType> attriList = new ArrayList<ArmorType>();
		HashMap<ArmorType, Double> parameters = new HashMap<ArmorType, Double>();
		LevelType lvType = null;
		boolean isUnbreakable = false;
		try {
			for (String lore : item.getItemMeta().getLore()) {
				if (lore.contains("品质: ")) {
					for (LevelType type : LevelType.values()) {
						if (lore.contains(type.toString())) {
							lvType = type;
							continue;
						}
					}
				}
				if (lore.contains("无法破坏")) {
					isUnbreakable = true;
					continue;
				}
				for (ArmorType type : values()) {
					if (lore.contains(type.toString())) {
						attriList.add(type);
						lore = ChatColor.stripColor(lore);
						if (type.isPercentile()) {
							parameters.put(type,
									Double.parseDouble(lore.substring(lore.indexOf(": ") + 1, lore.length() - 1))
											/ 100);
						} else {
							parameters.put(type,
									Double.parseDouble(lore.substring(lore.indexOf(": ") + 1, lore.length())));
						}
						continue;
					}
				}
			}
		} catch (Exception e) {
		}
		if (lvType == null || attriList.size() == 0)
			return null;
		return new ArmorConnoObj(lvType, attriList, parameters, isUnbreakable);
	}

	public boolean equals(ArmorType another) {
		return this.name.equals(another.name);
	}

}
