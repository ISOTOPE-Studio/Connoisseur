package cc.isotopestudio.Connoisseur.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.isotopestudio.Connoisseur.Connoisseur;
import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.utli.S;

public class CommandCadmin implements CommandExecutor {
	private final Connoisseur plugin;

	public CommandCadmin(Connoisseur plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ConnoisseurAdmin")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(S.toPrefixRed("必须要玩家才能执行"));
				sendHelp(sender, label);
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("conno.admin")) {
				sender.sendMessage(S.toPrefixRed("你没有权限"));
				return true;
			}
			if (args.length > 0 && !args[0].equalsIgnoreCase("help")) {
				if (args[0].equalsIgnoreCase("getA")) {
					player.getInventory().addItem(ScrollType.A.getItem());
					return true;
				}
				if (args[0].equalsIgnoreCase("getB")) {
					player.getInventory().addItem(ScrollType.B.getItem());
					return true;
				}
				if (args[0].equalsIgnoreCase("getC")) {
					player.getInventory().addItem(ScrollType.C.getItem());
					return true;
				}
				if (args[0].equalsIgnoreCase("getX")) {
					player.getInventory().addItem(ScrollType.X.getItem());
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
		player.sendMessage(S.toBoldGreen("/" + label + " getA") + S.toGray(" - ") + S.toGold("获得") + ScrollType.A);
		player.sendMessage(S.toBoldGreen("/" + label + " getB") + S.toGray(" - ") + S.toGold("获得") + ScrollType.B);
		player.sendMessage(S.toBoldGreen("/" + label + " getC") + S.toGray(" - ") + S.toGold("获得") + ScrollType.C);
		player.sendMessage(S.toBoldGreen("/" + label + " getX") + S.toGray(" - ") + S.toGold("获得") + ScrollType.X);
	}

}
