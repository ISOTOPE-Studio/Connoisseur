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
			.append("鉴定").append("]").append(ChatColor.GREEN).toString();
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

		getLogger().info(pluginName + "成功加载!");
		getLogger().info(pluginName + "由ISOTOPE Studio制作!");
		getLogger().info("http://isotopestudio.cc");
	}

	@Override
	public void onDisable() {
		getLogger().info(pluginName + "成功卸载!");
	}

	public void onReload() {
		this.reloadConfig();
		getLogger().info(pluginName + "成功重载!");
		getLogger().info(pluginName + "由ISOTOPE Studio制作!");
	}

}
