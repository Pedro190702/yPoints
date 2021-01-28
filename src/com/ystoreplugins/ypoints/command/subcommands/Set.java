package com.ystoreplugins.ypoints.command.subcommands;

import org.bukkit.command.CommandSender;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.api.yPointsAPI;
import com.ystoreplugins.ypoints.enums.Messages;
import com.ystoreplugins.ypoints.methods.IsNumeric;
import com.ystoreplugins.ypoints.models.PlayerPoints;
import com.ystoreplugins.ypoints.utils.Formatter;

public class Set {
	
	public Set(CommandSender sender, String playerName, String amountString) {
		Main main = Main.getPlugin(Main.class);
		if (!IsNumeric.isNumeric(amountString)) {
			sender.sendMessage(Messages.NOTANUMBER.getValue());
			return;
		}
		
		double value = Double.parseDouble(amountString);
		
		if (value <= 0)
			return;
		
		String formatted = Formatter.letterFormatter(value);
		
		if (!main.mainDataManager.USERS.isCached(playerName)) {
			sender.sendMessage(Messages.PLAYERNOTFOUND.getValue());
			return;
		}
		
		PlayerPoints pPoints = main.mainDataManager.USERS.getCached(playerName);
		
		yPointsAPI.ypointsapi.setPoints(pPoints, value);
		sender.sendMessage(Messages.SET.getValue().replace("{player}", playerName).replace("{points}", formatted));
	}
}
