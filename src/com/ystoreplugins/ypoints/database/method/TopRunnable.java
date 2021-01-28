package com.ystoreplugins.ypoints.database.method;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.scheduler.BukkitRunnable;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.models.PlayerPoints;

public class TopRunnable extends BukkitRunnable {
	
	private final Main main;
	public List<PlayerPoints> list = new ArrayList<>();
	
	public TopRunnable(Main main) {
		this.main = main;
		this.runTaskTimerAsynchronously(main, 20L, 12000L);
	}

	@Override
	public void run() {
		main.topRunnable.list = (List<PlayerPoints>) this.main.mainDataManager.USERS.getAll().stream().sorted(Comparator.comparingDouble(PlayerPoints::getPoints).reversed()).limit(7L).collect(Collectors.toList());
	}

}
