package cc.isotopestudio.Connoisseur.names;

import java.util.HashMap;

import org.bukkit.Material;

import cc.isotopestudio.Connoisseur.config.C;
import cc.isotopestudio.Connoisseur.utli.S;

public enum WeaponType {
	CRITICAL("±©»÷", 0.08, 0.12, 0.15, 0.17, 0.22, 0.25, 0.30, true, "Ë«±¶¹¥»÷"),

	VAMPIRIC("ÎüÑª", 0.05, 0.08, 0.12, 0.15, 0.20, 0.25, 0.30, true, "Îü 2 µãÉúÃü"),

	ADDITIONAL("¸½¼Ó¹¥»÷", 3, 5, 7, 9, 13, 17, 20, false, "Ôö¼Ó¹¥»÷"),

	DIRECT("Ö±½ÓÉËº¦", 1, 3, 5, 7, 9, 11, 15, false, "Ö±½ÓÉËº¦"),

	DEADLY("ÖÂÃüÒ»»÷", 0.002, 0.005, 0.007, 0.009, 0.015, 0.02, 0.03, true, "ÃëÉ±¶ÔÊÖ"),

	FROZEN("ÖØ»÷", 0.08, 0.12, 0.15, 0.17, 0.22, 0.25, 0.30, true, "½ûïÀ 3 Ãë");

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
		return name;
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
}
