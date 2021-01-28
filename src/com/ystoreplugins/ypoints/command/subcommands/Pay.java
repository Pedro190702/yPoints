package com.ystoreplugins.ypoints.command.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.api.yPointsAPI;
import com.ystoreplugins.ypoints.enums.Messages;
import com.ystoreplugins.ypoints.methods.IsNumeric;
import com.ystoreplugins.ypoints.models.PlayerPoints;
import com.ystoreplugins.ypoints.utils.Formatter;

public class Pay {
	
	@SuppressWarnings("deprecation")
	public Pay(Main main, CommandSender sender, String playerName, String amountString) {
		if (!(sender instanceof Player)) {
			main.config.commandHelp.forEach(ch -> sender.sendMessage(ch));
			return;
		}
		
		if (!IsNumeric.isNumeric(amountString)) {
			sender.sendMessage(Messages.NOTANUMBER.getValue());
			return;
		}
		
		double value = Double.parseDouble(amountString);
		
		if (value <= 0)
			return;
		
		String formatted = Formatter.letterFormatter(value);
		Player t = Bukkit.getPlayer(playerName);
		Player p = (Player) sender;
		
		if (t == null || !main.mainDataManager.USERS.isCached(t.getName())) {
			sender.sendMessage(Messages.PLAYERNOTFOUND.getValue());
			return;
		}
		
		PlayerPoints pPoints = main.mainDataManager.USERS.getCached(p.getName());
		PlayerPoints tPoints = main.mainDataManager.USERS.getCached(t.getName());
		
		if (p == t) {
			p.sendMessage(Messages.PAYYOURSELF.getValue());
			return;
		}
		
		if (pPoints.getPoints() < value)
			return;
		
		yPointsAPI.ypointsapi.addPoints(tPoints, value);
		yPointsAPI.ypointsapi.removePoints(pPoints, value);
		
		p.sendMessage(Messages.PAY.getValue().replace("{player}", t.getName()).replace("{points}", formatted));
		t.sendMessage(Messages.PAYTARGET.getValue().replace("{player}", p.getName()).replace("{points}", formatted));
	}
}
