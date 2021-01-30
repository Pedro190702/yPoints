package com.ystoreplugins.ypoints.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.database.util.Utils;
import com.ystoreplugins.ypoints.models.PlayerPoints;

public class PlayerJoinQuit implements Listener {
	
	private final Main main;
	
    public PlayerJoinQuit(Main main) {
    	this.main = main;
    	main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Utils.async(() -> {
            Player player = e.getPlayer();
            PlayerPoints.load(player, main.mainDataManager.USERS);
        });
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (!main.mainDataManager.USERS.isCached(player.getName())) return;
        PlayerPoints user = main.mainDataManager.USERS.getCached(player.getName());
        main.mainDataManager.USERS.insert(user, true);
        main.mainDataManager.USERS.uncache(player.getName());
    }

}