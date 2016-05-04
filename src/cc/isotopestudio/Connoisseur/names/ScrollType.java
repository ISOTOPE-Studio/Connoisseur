package cc.isotopestudio.Connoisseur.names;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cc.isotopestudio.Connoisseur.utli.S;

public enum ScrollType {
	A("混沌鉴定卷", Material.PAPER, LevelType.E, LevelType.A),

	B("灵魂鉴定卷", Material.PAPER, LevelType.F, LevelType.C),

	C("装备鉴定卷", Material.PAPER, LevelType.G, LevelType.D),

	X("重铸卷轴", Material.BOOK);

	final private String name;
	final private ItemStack item;
	final private LevelType min;
	final private LevelType max;

	ScrollType(String name, Material id, LevelType min, LevelType max) {
		this.name = name;
		item = new ItemStack(id);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(toString());
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(S.toAqua("使用 /conno use" + this.name() + " 鉴定装备"));
		lore.add(S.toGray("最低鉴定等级: ") + min);
		lore.add(S.toGray("最高鉴定等级: ") + max);
		meta.setLore(lore);
		item.setItemMeta(meta);
		this.min = min;
		this.max = max;
	}

	ScrollType(String name, Material id) {
		this.name = name;
		item = new ItemStack(id);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(toString());
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(S.toAqua("使用/conno use" + this.name() + "清洗装备"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		this.min = LevelType.G;
		this.max = LevelType.A;
	}

	public String toString() {
		switch (name()) {
		case ("A"):
			return S.toBoldPurple(name);
		case ("B"):
			return S.toBoldDarkAqua(name);
		case ("C"):
			return S.toBoldDarkGreen(name);
		case ("X"):
			return S.toBoldGreen(name);
		default:
			return null;
		}
	}

	public ItemStack getItem() {
		return item;
	}

	public LevelType getMin() {
		return min;
	}

	public LevelType getMax() {
		return max;
	}

	public LevelType getRandomLevelType() {
		LevelType type = LevelType.getRandom();
		while (!(type.getDiff(min) >= 0 && type.getDiff(max) <= 0)) {
			type = LevelType.getRandom();
		}
		return type;
	}

	public static ScrollType getType(ItemStack item) {
		try {
			ItemMeta meta = item.getItemMeta();
			for (ScrollType type : ScrollType.values()) {
				if (item.getType().equals(type.item.getType()) && meta.getDisplayName().equals(type.toString())
						&& meta.getLore().get(0).contains("装备"))
					return type;
			}
		} catch (Exception e) {
		}
		return null;
	}
	
	public static ItemStack getItem(ScrollType type){
		switch (type) {
		case A:
			return A.getItem();
		case B:
			return B.getItem();
		case C:
			return C.getItem();
		case X:
			return X.getItem();
		default:
			return null;
		}
	}

}
