package cc.isotopestudio.Connoisseur.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.isotopestudio.Connoisseur.names.ScrollType;
import cc.isotopestudio.Connoisseur.utli.S;

public class CommandCadmin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("ConnoisseurAdmin")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(S.toPrefixRed("����Ҫ��Ҳ���ִ��"));
				sendHelp(sender, label);
				return true;
			}
			Player player = (Player) sender;
			if (!player.hasPermission("conno.admin")) {
				sender.sendMessage(S.toPrefixRed("��û��Ȩ��"));
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
		player.sendMessage(S.toPrefixGreen("�����˵�"));
		player.sendMessage(S.toBoldGreen("/" + label + " getA") + S.toGray(" - ") + S.toGold("���") + ScrollType.A);
		player.sendMessage(S.toBoldGreen("/" + label + " getB") + S.toGray(" - ") + S.toGold("���") + ScrollType.B);
		player.sendMessage(S.toBoldGreen("/" + label + " getC") + S.toGray(" - ") + S.toGold("���") + ScrollType.C);
		player.sendMessage(S.toBoldGreen("/" + label + " getX") + S.toGray(" - ") + S.toGold("���") + ScrollType.X);
	}

}
