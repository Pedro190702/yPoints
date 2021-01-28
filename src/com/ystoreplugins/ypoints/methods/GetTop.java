package com.ystoreplugins.ypoints.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.models.PlayerPoints;
import com.ystoreplugins.ypoints.utils.Formatter;
import com.ystoreplugins.ypoints.utils.ItemBuilder;
import com.ystoreplugins.ypoints.utils.Scroller;
import com.ystoreplugins.ypoints.utils.Scroller.ScrollerBuilder;

public class GetTop {
	
	private Scroller inv;
	
	public void topMenu(Main main) {
		ArrayList<ItemStack> items = new ArrayList<>();
		int i = 1;
		
		Map<String, Double> top = getTop();
		for (String s : top.keySet()) {
			double points = top.get(s);
			String formatted = Formatter.letterFormatter(points);
			
			ArrayList<String> lore = new ArrayList<>();
			for (String l : main.config.topLore)
				lore.add(l.replace("{player}", s).replace("{pos}", "" + i).replace("{points}", formatted));
			
			ItemStack item = new ItemBuilder(Material.SKULL_ITEM)
					.setDurability(3)
					.setSkullOwner(s)
					.setName(main.config.topName.replace("{player}", s).replace("{pos}", "" + i))
					.setLore(lore)
					.toItemStack();
			items.add(item);
			i++;
		}
		
		Scroller scroller = new ScrollerBuilder()
				.withItems(items)
				.withName(main.config.topMenuName)
				.withSize(main.config.topSize)
				.withItemsSlots(main.config.topSlots)
				.withArrowsSlots(main.config.topBack, main.config.topNext)
				.build(main);
		
		main.top.inv = scroller;

	}
	
	public void show(Player p, Main main) {
		Scroller pInv = main.top.inv;
		pInv.open(p);
	}
	
	private Map<String, Double> getTop() {
		HashMap<String, Double> playersAndPoints = new HashMap<>();
		for (PlayerPoints p : Main.getPlugin(Main.class).topRunnable.list)
			playersAndPoints.put(p.getName(), p.getPoints());
		
		Map<String, Double> sorted = playersAndPoints
				.entrySet()
				.stream()
				.sorted(Map.Entry.<String, Double>comparingByValue()
				.reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (value1, value2) -> value1, LinkedHashMap::new));
		return sorted;
	}
}
