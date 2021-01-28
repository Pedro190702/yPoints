package com.ystoreplugins.ypoints.api;

import java.text.DecimalFormat;

import org.bukkit.OfflinePlayer;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.models.PlayerPoints;
import com.ystoreplugins.ypoints.utils.Formatter;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderAPI extends PlaceholderExpansion {
	
	@Override
    public boolean canRegister(){
        return true;
    }

	@Override
	public String getAuthor() {
		return "yChusy";
	}

	@Override
	public String getIdentifier() {
		return "ypoints";
	}

	@Override
	public String getVersion() {
		return Main.getPlugin(Main.class).getDescription().getVersion();
	}
	
	@Override
    public String onRequest(OfflinePlayer player, String identifier) {
		Main main = Main.getPlugin(Main.class);
		if (player == null)
			return "-/-";
		
		if (!main.mainDataManager.USERS.isCached(player.getName()))
			return "-/-";
		
		PlayerPoints p = main.mainDataManager.USERS.getCached(player.getName());
		
		if (p == null)
			return "-/-";
		
        if(identifier.equals("points"))
        	if (p.getPoints() <= 0.0) return "0.00";
        	else return new DecimalFormat("#,###.00").format(p.getPoints());
        
        if(identifier.equals("formatted"))
        	return Formatter.letterFormatter(main.mainDataManager.USERS.getCached(player.getName()).getPoints());
        
        return "-/-";
    }
}
