package com.ystoreplugins.ypoints.models;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ystoreplugins.ypoints.database.Keyable;
import com.ystoreplugins.ypoints.database.datamanager.CachedDataManager;
import com.ystoreplugins.ypoints.database.util.Utils;

public class PlayerPoints implements Keyable<String> {
	
	private String name;
	private double points;
	
	public PlayerPoints(String name, double points) {
		this.name = name;
		this.points = points;
	}
	
	public String getName() {
		return name;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	@Override
	public String getKey() {
		return name;
	} 
	
	public static void loadAll(CachedDataManager<String, PlayerPoints> dao) {
        Utils.measureTime(() -> {
            int i = 0;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (dao.isCached(player.getName())) continue;
                load(player, dao);
                i++;
            }
            return "Carregado " + i + " objetos em {time}";
        });
    }

    public static void load(Player player, CachedDataManager<String, PlayerPoints> dao) {
        if (dao.exists(player.getName())) {
        	PlayerPoints user = dao.find(player.getName());
            dao.cache(user);
        } else {
        	PlayerPoints user = new PlayerPoints(player.getName(), 0);
            dao.cache(user);
        }
    }
}
