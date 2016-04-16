package cc.isotopestudio.Connoisseur.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.isotopestudio.Connoisseur.Connoisseur;
import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.utli.S;

public class CommandConno implements CommandExecutor {
	private final Connoisseur plugin;

	public CommandConno(Connoisseur plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("Connoisseur")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(S.toPrefixRed("必须要玩家才能执行"));
				sendHelp(sender, label);
				return true;
			}
			Player player = (Player) sender;
			if (args.length > 0 && !args[0].equalsIgnoreCase("help")) {
				if (args[0].equalsIgnoreCase("useA")) {
					return true;
				}
				if (args[0].equalsIgnoreCase("useB")) {
					return true;
				}
				if (args[0].equalsIgnoreCase("useC")) {
					return true;
				}
				if (args[0].equalsIgnoreCase("useX")) {
					return true;
				}
				sendHelp(player, label);
				return true;
			} else {
				sendHelp(player, label);
				return true;
			}
		}
		return false;
	}

	void sendHelp(CommandSender player, String label) {
		player.sendMessage(S.toPrefixGreen("帮助菜单"));
		player.sendMessage(S.toBoldGreen("/" + label + " useA") + S.toGray(" - ") + S.toGold("使用") + ScrollType.A
				+ S.toGold("鉴定装备!"));
		player.sendMessage(S.toBoldGreen("/" + label + " useB") + S.toGray(" - ") + S.toGold("使用") + ScrollType.B
				+ S.toGold("鉴定装备!"));
		player.sendMessage(S.toBoldGreen("/" + label + " useC") + S.toGray(" - ") + S.toGold("使用") + ScrollType.C
				+ S.toGold("鉴定装备!"));
		player.sendMessage(S.toBoldGreen("/" + label + " useX") + S.toGray(" - ") + S.toGold("使用") + ScrollType.X
				+ S.toGold("清洗装备!"));
		player.sendMessage(S.toYellow("请在背包内放置鉴定卷"));
	}

}
