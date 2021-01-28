package com.ystoreplugins.ypoints;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.ystoreplugins.ypoints.api.PlaceholderAPI;
import com.ystoreplugins.ypoints.api.yPointsAPI;
import com.ystoreplugins.ypoints.command.Points;
import com.ystoreplugins.ypoints.config.Config;
import com.ystoreplugins.ypoints.database.AutoSave;
import com.ystoreplugins.ypoints.database.MainDataManager;
import com.ystoreplugins.ypoints.database.datasource.AbstractDataSource;
import com.ystoreplugins.ypoints.database.method.SaveAndLoad;
import com.ystoreplugins.ypoints.database.method.TopRunnable;
import com.ystoreplugins.ypoints.database.util.Utils;
import com.ystoreplugins.ypoints.listeners.PlayerJoinQuit;
import com.ystoreplugins.ypoints.methods.GetTop;
import com.ystoreplugins.ypoints.methods.RegisterCommand;
import com.ystoreplugins.ypoints.methods.UpdateEnums;
import com.ystoreplugins.ypoints.models.PlayerPoints;
import com.ystoreplugins.ypoints.utils.ConfigUtils;
import com.ystoreplugins.ypoints.utils.Formatter;

public class Main extends JavaPlugin {

	public Config config = new Config();
	public GetTop top = new GetTop();
	public ConfigUtils commands;
	
	public AbstractDataSource abstractDataSource;
    public MainDataManager mainDataManager;
    public TopRunnable topRunnable;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		register();
		Bukkit.getConsoleSender().sendMessage("§7================================");
		Bukkit.getConsoleSender().sendMessage("§7| §6Plugin yPoints enabled!");
		Bukkit.getConsoleSender().sendMessage("§7| §6Made by yChusy.");
		Bukkit.getConsoleSender().sendMessage("§7| §6Version " + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("§7================================");
	}

	@Override
	public void onDisable() {
        SaveAndLoad.saveAll(this);
		Bukkit.getConsoleSender().sendMessage("§7================================");
		Bukkit.getConsoleSender().sendMessage("§7| §6Plugin yPoints disabled!");
		Bukkit.getConsoleSender().sendMessage("§7| §6Made by yChusy.");
		Bukkit.getConsoleSender().sendMessage("§7| §6Version " + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("§7================================");
	}

	private void register() {
		Utils.DEBUGGING = getConfig().getBoolean("Database.Debug");
		if (!SaveAndLoad.prepareDatabase(this)) return;
		PlayerPoints.loadAll(this.mainDataManager.USERS);
		topRunnable = new TopRunnable(this);
		
		loadConfigs();
		config.setup(this);
		Formatter.formats = (String[]) getConfig().getStringList("Formats").toArray(new String[0]);
		yPointsAPI.ypointsapi = new yPointsAPI();
		
		new UpdateEnums(this);
		new PlayerJoinQuit(this);
		
		top.topMenu(this);
		registerCommand();
		
		if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) new PlaceholderAPI().register();
		Bukkit.getScheduler().runTaskTimer(this, () -> top.topMenu(this), 40, 20 * 60 * 3);
		new AutoSave(this);
	}
	
	private void registerCommand() {
		String command = commands.getConfig().getString("MainCommand.Command");
		ArrayList<String> aliases = new ArrayList<>();
		commands.getConfig().getStringList("MainCommand.Aliases").forEach(a -> aliases.add(a));
		new RegisterCommand(command, new Points(this), aliases, this);
	}
	
	private void loadConfigs() {
		ConfigUtils c = new ConfigUtils("plugins/" + getDescription().getName(), "commands.yml", this);
		c.create();
		if (!c.exists()) {
			c.setDefault("commands.yml");
			c.getConfig().options().copyDefaults(true);
			c.saveConfig();
		}
		commands = c;
	}
}