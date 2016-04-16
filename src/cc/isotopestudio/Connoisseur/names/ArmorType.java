package cc.isotopestudio.Connoisseur.names;

import java.util.HashMap;

public enum ArmorType {
	LIFE("����", 1, 2, 3, 4, 7, 9, 15, "���ӻ�������ֵ"),

	DODGE("����", 0.05, 0.8, 0.12, 0.15, 0.19, 0.22, 0.27, "��ܵ��˹���"),

	RESISTENCE("�ֿ�", 0.07, 0.10, 0.14, 0.17, 0.22, 0.27, 0.35, "���ߵ���70�Ĺ���"),

	INVINCIBILITY("�޵�", 0.05, 0.8, 0.12, 0.15, 0.19, 0.22, 0.27, "����3��������˺���Դ��Ч��"),

	BOUNCE("����", 1, 2, 3, 4, 7, 9, 12, "��35����֮����˺�"),

	SPEED("�ٶ�", 0.05, 0.08, 0.12, 0.15, 0.19, 0.22, 0.27, "����15���ٶ�");

	final private String name;
	final private HashMap<LevelType, Double> attriLevMap;
	final private String description;

	ArmorType(String name, double G, double F, double E, double D, double C, double B, double A, String des) {
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
