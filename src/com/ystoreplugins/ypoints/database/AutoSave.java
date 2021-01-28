package com.ystoreplugins.ypoints.database;

import org.bukkit.scheduler.BukkitRunnable;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.database.util.Utils;

public class AutoSave extends BukkitRunnable {

    private final Main main;

    public AutoSave(Main main) {
        this.main = main;
        runTaskTimerAsynchronously(main, 20L * 60 * 30, 20L * 60 * 30);
    }

    @Override
    public void run() {
        Utils.debug(Utils.LogType.DEBUG, "Iniciando auto save");
        long before = System.currentTimeMillis();
        int i = main.mainDataManager.saveCached(false);
        long now = System.currentTimeMillis();
        long total = now - before;
        Utils.debug(Utils.LogType.INFO, "Auto completo, salvo " + i + " objetos em " + total + "ms");
    }

}