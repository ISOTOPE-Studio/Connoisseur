package cc.isotopestudio.Connoisseur.names;

import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public enum LevelType {
	A("����", 5),

	B("����", 4),

	C("��Ͼ", 3),

	D("����", 2),

	E("����", 2),

	F("��ͨ", 1),

	G("�ֲ�", 1);
	
	private String name;
	private int max;

	LevelType(String name, int max) {
		this.name = name;
		this.max = max;
	}

	public String toString() {
		if (this.equals(LevelType.A))
			return S.toBoldPurple(name);
		if (this.equals(LevelType.B))
			return S.toBoldDarkAqua(name);
		if (this.equals(LevelType.C))
			return S.toBoldPurple(name);
		if (this.equals(LevelType.D))
			return S.toBoldGreen(name);
		if (this.equals(LevelType.E))
			return S.toBoldGreen(name);
		if (this.equals(LevelType.F))
			return S.toBoldGreen(name);
		if (this.equals(LevelType.G))
			return S.toBoldGreen(name);
		return null;
	}

	public int getMaxAttr() {
		return max;
	}

	public static LevelType getRandom() {
		int random = MathUtli.random(0, LevelType.values().length - 1);
		return LevelType.values()[random];
	}

	public boolean isHigher(LevelType type) {
		return this.name().charAt(0) < type.name().charAt(0);
	}

	public int getDiff(LevelType type) {
		return type.name().charAt(0) - this.name().charAt(0);
	}
}
