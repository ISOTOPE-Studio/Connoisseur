package cc.isotopestudio.Connoisseur.names;

import cc.isotopestudio.Connoisseur.utli.S;

public enum ScrollType {
	A("»ìãç¼ø¶¨¾í"), B("Áé»ê¼ø¶¨¾í"), C("×°±¸¼ø¶¨¾í"), X("ÖØÖý¾íÖá");
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
