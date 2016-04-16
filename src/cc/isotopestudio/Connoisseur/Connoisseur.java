package cc.isotopestudio.Connoisseur;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cc.isotopestudio.Connoisseur.command.CommandCadmin;
import cc.isotopestudio.Connoisseur.command.CommandConno;
import cc.isotopestudio.Connoisseur.listener.ConnoListener;

public class Connoisseur extends JavaPlugin {
	public static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
			.append("����").append("]").append(ChatColor.GREEN).toString();
	public static final String pluginName = "Connoisseur";

	public void createFile(String name) {
		File file;
		file = new File(getDataFolder(), name + ".yml");
		if (!file.exists()) {
			saveDefaultConfig();
		}
	}

	@Override
	public void onEnable() {
		createFile("config");

		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new ConnoListener(), this);

		this.getCommand("Connoisseur").setExecutor(new CommandConno(this));
		this.getCommand("ConnoisseurAdmin").setExecutor(new CommandCadmin(this));

		getLogger().info(pluginName + "�ɹ�����!");
		getLogger().info(pluginName + "��ISOTOPE Studio����!");
		getLogger().info("http://isotopestudio.cc");
	}

	@Override
	public void onDisable() {
		getLogger().info(pluginName + "�ɹ�ж��!");
	}

	public void onReload() {
		this.reloadConfig();
		getLogger().info(pluginName + "�ɹ�����!");
		getLogger().info(pluginName + "��ISOTOPE Studio����!");
	}

}
