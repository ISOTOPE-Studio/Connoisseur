package cc.isotopestudio.Connoisseur.names;

import java.util.ArrayList;
import java.util.HashMap;

import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public class ArmorConnoObj {

	final private ScrollType scrollType;
	final private LevelType lvType;
	final private ArrayList<ArmorType> attriList;
	final private HashMap<ArmorType, Double> parameters;
	final private String playerName;

	public ArmorConnoObj(ScrollType s, String playerName) {
		scrollType = s;
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

	public ScrollType getScrollType() {
		return scrollType;
	}

	public LevelType getLevelType() {
		return lvType;
	}

	public ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(S.toGray("---- ") + S.toBoldRed("【传奇大陆装备鉴定】") + S.toGray(" ----"));
		lore.add(S.toGray("品质: ") + lvType.toString());
		for (ArmorType aType : attriList) {
			if (aType.isPercentile())
				lore.add(aType.toString() + " " + aType.getDescription() + ": "
						+ Math.round(parameters.get(aType) * 100) + "%");
			else
				lore.add(aType.toString() + " " + aType.getDescription() + ": " + Math.round(parameters.get(aType)));
		}
		lore.add(S.toGray("鉴定者: ") + S.toBoldBlue(playerName));
		lore.add(S.toGray("---- ") + S.toBoldRed("【传奇大陆装备鉴定】") + S.toGray(" ----"));
		return lore;
	}
}
