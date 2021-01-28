package com.ystoreplugins.ypoints.command.subcommands;

import org.bukkit.command.CommandSender;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.enums.Messages;
import com.ystoreplugins.ypoints.models.PlayerPoints;
import com.ystoreplugins.ypoints.utils.Formatter;

public class Look {
	
	public Look(CommandSender sender, String playerName) {
		Main main = Main.getPlugin(Main.class);
		if (!main.mainDataManager.USERS.isCached(playerName)) {
			sender.sendMessage(Messages.PLAYERNOTFOUND.getValue());
			return;
		}
		
		PlayerPoints pPoints = main.mainDataManager.USERS.getCached(playerName);
		String formatted = Formatter.letterFormatter(pPoints.getPoints());
		
		sender.sendMessage(Messages.PLAYERPOINTSTARGET.getValue().replace("{player}", playerName).replace("{points}", formatted));
	}
}
