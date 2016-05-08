package cc.isotopestudio.Connoisseur.names;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.config.C;
import cc.isotopestudio.Connoisseur.obj.WeaponConnoObj;
import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public enum WeaponType {
	CRITICAL("暴击", 0.03, 0.05, 0.07, 0.10, 0.12, 0.15, 0.18, true, "双倍攻击"),

	VAMPIRIC("吸血", 0.03, 0.05, 0.07, 0.10, 0.12, 0.15, 0.18, true, "吸 2 点生命"),

	ADDITIONAL("附加攻击", 3, 5, 7, 9, 13, 17, 20, false, "增加攻击"),

	DIRECT("直接伤害", 1, 2, 3, 4, 6, 8, 10, false, "直接伤害"),

	DEADLY("致命一击", 0.002, 0.005, 0.007, 0.009, 0.015, 0.02, 0.03, true, "秒杀对手"),

	FROZEN("重击", 0.01, 0.03, 0.05, 0.07, 0.10, 0.12, 0.15, true, "禁锢 3 秒");

	final private String name;
	final private HashMap<LevelType, Double> attriLevMap;
	final private String description;
	final private boolean isPercentile;

	WeaponType(String name, double G, double F, double E, double D, double C, double B, double A, boolean isPercentile,
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

	public static boolean isWeapon(Material item) {
		for (Material m : C.weapons)
			if (item == m)
				return true;
		return false;
	}

	public static WeaponType getRandom() {
		int num = MathUtli.random(0, WeaponType.values().length - 1);
		return WeaponType.values()[num];
	}

	public static ArrayList<WeaponType> genType(LevelType lvType) {
		ArrayList<WeaponType> list = new ArrayList<WeaponType>();
		int num = lvType.getAttrNum();
		int count = 0;
		while (count < num) {
			loop: {
				WeaponType type = getRandom();
				for (WeaponType temp : list) {
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

	public static WeaponConnoObj getType(ItemStack item) {
		ArrayList<WeaponType> attriList = new ArrayList<WeaponType>();
		HashMap<WeaponType, Double> parameters = new HashMap<WeaponType, Double>();
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
				for (WeaponType type : values()) {
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
		return new WeaponConnoObj(lvType, attriList, parameters, isUnbreakable);
	}

	public boolean equals(WeaponType another) {
		return this.name.equals(another.name);
	}

}
