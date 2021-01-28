package com.ystoreplugins.ypoints.api;

import org.bukkit.entity.Player;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.models.PlayerPoints;

public class yPointsAPI {
	
	public static yPointsAPI ypointsapi;
	public static Main main = Main.getPlugin(Main.class);
	
	public PlayerPoints getPlayer(Player p) {
		return main.mainDataManager.USERS.getCached(p.getName());
	}
	
	public PlayerPoints getPlayer(String p) {
		return main.mainDataManager.USERS.getCached(p);
	}
	
	public double getPoints(PlayerPoints p) {
		return p.getPoints();
	}
	
	public void setPoints(PlayerPoints p, double value) {
		p.setPoints(value);
	}
	
	public void removePoints(PlayerPoints p, double value) {
		if (p.getPoints() < value) p.setPoints(0);
		else p.setPoints(p.getPoints() - value);
	}
	
	public void addPoints(PlayerPoints p, double value) {
		p.setPoints(p.getPoints() + value);
	}
}
