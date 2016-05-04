package cc.isotopestudio.Connoisseur.names;

import java.util.HashMap;

import org.bukkit.Material;

import cc.isotopestudio.Connoisseur.utli.S;

public enum WeaponType {
	CRITICAL("����", 0.08, 0.12, 0.15, 0.17, 0.22, 0.25, 0.30, true, "˫������"),

	VAMPIRIC("��Ѫ", 0.05, 0.08, 0.12, 0.15, 0.20, 0.25, 0.30, true, "�� 2 ������"),

	ADDITIONAL("���ӹ���", 3, 5, 7, 9, 13, 17, 20, false, "���ӹ���"),

	DIRECT("ֱ���˺�", 1, 3, 5, 7, 9, 11, 15, false, "ֱ���˺�"),

	DEADLY("����һ��", 0.002, 0.005, 0.007, 0.009, 0.015, 0.02, 0.03, true, "��ɱ����"),

	FROZEN("�ػ�", 0.08, 0.12, 0.15, 0.17, 0.22, 0.25, 0.30, true, "���� 3 ��");

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
		if (item.equals(Material.DIAMOND_SWORD) || item.equals(Material.DIAMOND_AXE)

				|| item.equals(Material.GOLD_SWORD) || item.equals(Material.GOLD_AXE)

				|| item.equals(Material.IRON_SWORD) || item.equals(Material.IRON_AXE)

				|| item.equals(Material.WOOD_SWORD) || item.equals(Material.WOOD_AXE))
			return true;
		return false;
	}
}
