package cc.isotopestudio.Connoisseur.names;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import cc.isotopestudio.Connoisseur.config.C;
import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public enum ArmorType {
	LIFE("����", 1, 2, 3, 4, 7, 9, 15, false, "����ֵ"),

	DODGE("����", 0.05, 0.8, 0.12, 0.15, 0.19, 0.22, 0.27, true, "��ܹ���"),

	RESISTENCE("�ֿ�", 0.07, 0.10, 0.14, 0.17, 0.22, 0.27, 0.35, true, "���� 70% �Ĺ���"),

	INVINCIBILITY("�޵�", 0.05, 0.8, 0.12, 0.15, 0.19, 0.22, 0.27, true, "3���޵�"),

	BOUNCE("����", 1, 2, 3, 4, 7, 9, 12, false, "35%�����˺�"),

	SPEED("�ٶ�", 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, true, "�����ٶ�");

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

	public static ArmorConnoObj getType(ItemStack item) {
		ArrayList<ArmorType> attriList = new ArrayList<ArmorType>();
		HashMap<ArmorType, Double> parameters = new HashMap<ArmorType, Double>();
		LevelType lvType = null;
		try {
			for (String lore : item.getItemMeta().getLore()) {
				if (lore.contains("Ʒ��: ")) {
					for (LevelType type : LevelType.values()) {
						if (lore.contains(type.toString())) {
							lvType = type;
							continue;
						}
					}
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
		System.out.println(lvType.toString());
		for (ArmorType type : attriList) {
			System.out.println(type.toString() + ": " + parameters.get(type));
		}
		return new ArmorConnoObj(lvType, attriList, parameters);
	}

	public boolean equals(ArmorType another) {
		return this.name.equals(another.name);
	}

}
