package cc.isotopestudio.Connoisseur.obj;

import java.util.ArrayList;
import java.util.HashMap;

import cc.isotopestudio.Connoisseur.names.ArmorType;
import cc.isotopestudio.Connoisseur.names.LevelType;
import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public class ArmorConnoObj {

	final private LevelType lvType;
	final private ArrayList<ArmorType> attriList;
	final private HashMap<ArmorType, Double> parameters;
	final private String playerName;
	final public static String header = S.toGray("——————— ") + S.toBoldRed("【传奇大陆装备鉴定】") + S.toGray(" ———————");

	public ArmorConnoObj(ScrollType s, String playerName) {
		lvType = s.getRandomLevelType();
		attriList = ArmorType.genType(lvType);
		parameters = new HashMap<ArmorType, Double>();
		this.playerName = playerName;
		for (ArmorType aType : attriList) {
			double min = aType.getAttri(s.getMin());
			double max = aType.getAttri(lvType);
			parameters.put(aType, MathUtli.random(min, max));
		}
	}

	public ArmorConnoObj(LevelType lvType, ArrayList<ArmorType> attriList, HashMap<ArmorType, Double> parameters) {
		this.lvType = lvType;
		this.attriList = attriList;
		this.parameters = parameters;
		this.playerName = "";
	}

	public String toString() {
		StringBuilder s = new StringBuilder("Level: " + lvType.toString() + ", Attribution: [");
		for (ArmorType aType : attriList) {
			if (aType.isPercentile())
				s.append(aType.toString() + ": " + Math.round(parameters.get(aType) * 100) + "%");
			else
				s.append(aType.toString() + ": " + Math.round(parameters.get(aType)));
			s.append(" , ");
		}
		return s.append("]").toString();
	}

	public LevelType getLevelType() {
		return lvType;
	}

	public ArrayList<ArmorType> getAttriList() {
		return attriList;
	}

	public HashMap<ArmorType, Double> getParameters() {
		return parameters;
	}

	public String getPlayerName() {
		return playerName;
	}

	public ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(header);
		lore.add(S.toGray("品质: ") + lvType.toString());
		for (ArmorType aType : attriList) {
			if (aType.isPercentile())
				lore.add(aType.toString() + " " + aType.getDescription() + ": "
						+ Math.round(parameters.get(aType) * 100) + "%");
			else
				lore.add(aType.toString() + " " + aType.getDescription() + ": " + Math.round(parameters.get(aType)));
		}
		lore.add(S.toGray("鉴定者: ") + S.toBoldBlue(playerName));
		lore.add(S.toItalicYellow("无限耐久"));
		lore.add(header);
		return lore;
	}

	public void addAll(ArmorConnoObj ano) {
		for (ArmorType aType : ano.getAttriList()) {
			if (this.getAttriList().contains(aType)) {
				this.getParameters().put(aType, this.getParameters().get(aType) + ano.getParameters().get(aType));
			} else {
				this.getAttriList().add(aType);
				this.getParameters().put(aType, ano.getParameters().get(aType));
			}
		}
	}
}
