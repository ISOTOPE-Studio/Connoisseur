package cc.isotopestudio.Connoisseur.obj;

import java.util.ArrayList;
import java.util.HashMap;

import cc.isotopestudio.Connoisseur.names.LevelType;
import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.names.WeaponType;
import cc.isotopestudio.Connoisseur.utli.MathUtli;
import cc.isotopestudio.Connoisseur.utli.S;

public class WeaponConnoObj {

	final private LevelType lvType;
	final private ArrayList<WeaponType> attriList;
	final private HashMap<WeaponType, Double> parameters;
	final private String playerName;
	final public static String header = S.toGray("�������������� ") + S.toBoldRed("�������½װ��������") + S.toGray(" ��������������");

	public WeaponConnoObj(ScrollType s, String playerName) {
		lvType = s.getRandomLevelType();
		attriList = WeaponType.genType(lvType);
		parameters = new HashMap<WeaponType, Double>();
		this.playerName = playerName;
		for (WeaponType aType : attriList) {
			double min = aType.getAttri(s.getMin());
			double max = aType.getAttri(lvType);
			parameters.put(aType, MathUtli.random(min, max));
		}
	}

	public WeaponConnoObj(LevelType lvType, ArrayList<WeaponType> attriList, HashMap<WeaponType, Double> parameters) {
		this.lvType = lvType;
		this.attriList = attriList;
		this.parameters = parameters;
		this.playerName = "";
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

	public ArrayList<String> getLore() {
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(header);
		lore.add(S.toGray("Ʒ��: ") + lvType.toString());
		for (WeaponType aType : attriList) {
			if (aType.isPercentile())
				lore.add(aType.toString() + " " + aType.getDescription() + ": "
						+ Math.round(parameters.get(aType) * 100) + "%");
			else
				lore.add(aType.toString() + " " + aType.getDescription() + ": " + Math.round(parameters.get(aType)));
		}
		lore.add(S.toGray("������: ") + S.toBoldBlue(playerName));
		lore.add(S.toItalicYellow("�����;�"));
		lore.add(header);
		return lore;
	}
}
