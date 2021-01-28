package com.ystoreplugins.ypoints.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.utils.SetupItem;

public class Config {
	
	public String topMenuName, topName;
	public int topBack, topNext, topSize;
	
	public ItemStack back, next;
	
	public List<String> commandHelp = new ArrayList<>();
	public List<String> topLore = new ArrayList<>();
	public List<Integer> topSlots = new ArrayList<>();
	
	public Config setup(Main main) {
		Config config = main.config;
		FileConfiguration commandsConfig = main.commands.getConfig();
		
		commandsConfig.getStringList("Command help").forEach(ch -> config.commandHelp.add(ch.replace('&', '§')));
		
		ConfigurationSection arrows = main.getConfig().getConfigurationSection("Arrows");
		ConfigurationSection back = arrows.getConfigurationSection("Back");
		ConfigurationSection next = arrows.getConfigurationSection("Next");
		ConfigurationSection top = main.getConfig().getConfigurationSection("Top");
		ConfigurationSection item = top.getConfigurationSection("Item");
		
		config.topMenuName = top.getString("Name").replace('&', '§');
		config.topBack = top.getInt("Back");
		config.topNext = top.getInt("Next");
		config.topSize = top.getInt("Size");
		top.getIntegerList("Slots").forEach(s -> config.topSlots.add(s));
		
		config.topName = item.getString("Name").replace('&', '§');
		item.getStringList("Lore").forEach(l -> config.topLore.add(l.replace('&', '§')));
		
		config.back = new SetupItem().setupMenuItem(back, true);
		config.next = new SetupItem().setupMenuItem(next, true);
		return this;
	}
}
