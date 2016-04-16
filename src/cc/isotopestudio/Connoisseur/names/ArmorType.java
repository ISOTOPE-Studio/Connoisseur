package cc.isotopestudio.Connoisseur.names;

import java.util.HashMap;

public enum ArmorType {
	LIFE("生命", 1, 2, 3, 4, 7, 9, 15, "增加护甲生命值"),

	DODGE("闪避", 0.05, 0.8, 0.12, 0.15, 0.19, 0.22, 0.27, "躲避敌人攻击"),

	RESISTENCE("抵抗", 0.07, 0.10, 0.14, 0.17, 0.22, 0.27, 0.35, "免疫敌人70的攻击"),

	INVINCIBILITY("无敌", 0.05, 0.8, 0.12, 0.15, 0.19, 0.22, 0.27, "出现3秒无如何伤害来源的效果"),

	BOUNCE("反甲", 1, 2, 3, 4, 7, 9, 12, "有35反弹之后的伤害"),

	SPEED("速度", 0.05, 0.08, 0.12, 0.15, 0.19, 0.22, 0.27, "增加15的速度");

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
