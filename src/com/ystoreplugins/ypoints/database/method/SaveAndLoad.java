package com.ystoreplugins.ypoints.database.method;

import org.bukkit.Bukkit;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.database.MainDataManager;
import com.ystoreplugins.ypoints.database.datasource.HikariDataSource;
import com.ystoreplugins.ypoints.database.datasource.MySQLDataSource;
import com.ystoreplugins.ypoints.database.datasource.SQLLiteDataSource;
import com.ystoreplugins.ypoints.database.exception.DatabaseException;
import com.ystoreplugins.ypoints.database.util.Utils;

public class SaveAndLoad {
	
	public static void saveAll(Main main) {
        try {
            if (main.abstractDataSource == null) return;
            main.mainDataManager.saveCached(false);
            main.abstractDataSource.close();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

	public static boolean prepareDatabase(Main main) {
        try {
            String databaseType = main.getConfig().getString("Database.Tipo");
            if (databaseType.equalsIgnoreCase("MYSQL_FAST")) {
            	main.abstractDataSource = new HikariDataSource(main.getConfig().getString("Database.IP"), main.getConfig().getString("Database.DB"), main.getConfig().getString("Database.User"), main.getConfig().getString("Database.Pass"));
            } else if (databaseType.equalsIgnoreCase("MYSQL")) {
            	main.abstractDataSource = new MySQLDataSource(main.getConfig().getString("Database.IP"), main.getConfig().getString("Database.DB"), main.getConfig().getString("Database.User"), main.getConfig().getString("Database.Pass"));
            } else {
            	main.abstractDataSource = new SQLLiteDataSource();
            }
            main.mainDataManager = new MainDataManager(main.abstractDataSource);
            return true;
        } catch (DatabaseException e) {
            Utils.debug(Utils.LogType.INFO, "erro ao inicializar conexão com banco de dados");
            e.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(main);
        }
        return false;
    }

}
