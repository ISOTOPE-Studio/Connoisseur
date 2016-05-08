package cc.isotopestudio.Connoisseur.obj;

import java.util.ArrayList;
import java.util.HashMap;

import cc.isotopestudio.Connoisseur.names.LevelType;
import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.names.WeaponType;
import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public class WeaponConnoObj {

	final private java.text.DecimalFormat format = new java.text.DecimalFormat("#.00");

	final private LevelType lvType;
	final private ArrayList<WeaponType> attriList;
	final private HashMap<WeaponType, Double> parameters;
	final private String playerName;
	final public static String header = S.toGray("——————— ") + S.toBoldRed("【传奇大陆装备鉴定】") + S.toGray(" ———————");
	final private boolean isUnbreakAble;

	public WeaponConnoObj(ScrollType s, String playerName) {
		lvType = s.getRandomLevelType();
		ArrayList<WeaponType> attriTempList = WeaponType.genType(lvType);
		attriList = new ArrayList<WeaponType>();
		parameters = new HashMap<WeaponType, Double>();
		this.playerName = playerName;
		int count = 0;
		if (Math.random() < 0.3) {
			count++;
			isUnbreakAble = true;
		} else
			isUnbreakAble = false;
		for (WeaponType aType : attriTempList) {
			if (count >= lvType.getAttrNum())
				break;
			double min = aType.getAttri(s.getMin());
			double max = aType.getAttri(lvType);
			attriList.add(aType);
			parameters.put(aType, MathUtli.random(min, max));
			count++;
		}
	}

	public WeaponConnoObj(LevelType lvType, ArrayList<WeaponType> attriList, HashMap<WeaponType, Double> parameters,
			boolean isUnbreakAble) {
		this.lvType = lvType;
		this.attriList = attriList;
		this.parameters = parameters;
		this.playerName = "";
		this.isUnbreakAble = isUnbreakAble;
	}

	public LevelType getLevelType() {
		return lvType;
	}

	public ArrayList<WeaponType> getAttriList() {
		return attriList;
	}

	public HashMap<WeaponType, Double> getParameters() {
		return parameters;
	}

	public String getPlayerName() {
		return playerName;
	}

	public boolean isUnbreakable() {
		return isUnbreakAble;
	}

	public ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(header);
		lore.add(S.toGray("品质: ") + lvType.toString());
		for (WeaponType aType : attriList) {
			if (aType.isPercentile())
				lore.add(aType.toString() + " " + aType.getDescription() + ": "
						+ format.format(parameters.get(aType) * 100) + "%");
			else
				lore.add(aType.toString() + " " + aType.getDescription() + ": " + Math.round(parameters.get(aType)));
		}
		lore.add(S.toGray("鉴定者: ") + S.toBoldBlue(playerName));
		if (isUnbreakAble)
			lore.add(S.toBoldRed("无法破坏"));
		lore.add(header);
		return lore;
	}
}
