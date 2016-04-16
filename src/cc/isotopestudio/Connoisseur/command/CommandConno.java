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
				sender.sendMessage(S.toPrefixRed("����Ҫ��Ҳ���ִ��"));
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
		player.sendMessage(S.toPrefixGreen("�����˵�"));
		player.sendMessage(S.toBoldGreen("/" + label + " useA") + S.toGray(" - ") + S.toGold("ʹ��") + ScrollType.A
				+ S.toGold("����װ��!"));
		player.sendMessage(S.toBoldGreen("/" + label + " useB") + S.toGray(" - ") + S.toGold("ʹ��") + ScrollType.B
				+ S.toGold("����װ��!"));
		player.sendMessage(S.toBoldGreen("/" + label + " useC") + S.toGray(" - ") + S.toGold("ʹ��") + ScrollType.C
				+ S.toGold("����װ��!"));
		player.sendMessage(S.toBoldGreen("/" + label + " useX") + S.toGray(" - ") + S.toGold("ʹ��") + ScrollType.X
				+ S.toGold("��ϴװ��!"));
		player.sendMessage(S.toYellow("���ڱ����ڷ��ü�����"));
	}

}
