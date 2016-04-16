package cc.isotopestudio.Connoisseur.names;

import cc.isotopestudio.Connoisseur.utli.S;

public enum ScrollType {
	A("���������"), B("��������"), C("װ��������"), X("��������");
	private String name;

	ScrollType(String name) {
		this.name = name;
	}

	public String toString() {
		switch (name()) {
		case ("A"):
			return S.toBoldDarkGreen(name);
		case ("B"):
			return S.toBoldDarkAqua(name);
		case ("C"):
			return S.toBoldPurple(name);
		case ("X"):
			return S.toBoldGreen(name);
		default:
			return null;
		}
	}
}
