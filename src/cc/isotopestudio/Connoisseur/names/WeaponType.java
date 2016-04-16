package cc.isotopestudio.Connoisseur.names;

import java.util.HashMap;

public enum WeaponType {
	CRITICAL("����", 0.08, 0.12, 0.15, 0.17, 0.22, 0.25, 0.30, "˫������"),

	VAMPIRIC("��Ѫ", 0.05, 0.08, 0.12, 0.15, 0.20, 0.25, 0.30, "��2������"),

	ADDITIONAL("���ӹ���", 3, 5, 7, 9, 13, 17, 20, "���ӹ���"),

	DIRECT("ֱ���˺�", 1, 3, 5, 7, 9, 11, 15, "���ӹ���"),

	DEADLY("����һ��", 0.002, 0.005, 0.007, 0.009, 0.015, 0.02, 0.03, "��ɱ����"),

	FROZEN("�ػ�", 0.08, 0.12, 0.15, 0.17, 0.22, 0.25, 0.30, "ʹ�����޷��ƶ��͹���3��");

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

}
