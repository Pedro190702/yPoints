package com.ystoreplugins.ypoints.methods;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

import com.ystoreplugins.ypoints.Main;

public class RegisterCommand {
	
    private CommandExecutor executor;
    private String command;
    private List<String> aliases;
    private PluginCommand pluginCommand;
    
    public RegisterCommand(String command, CommandExecutor executor, List<String> aliases, Main main) {
        	this.executor = executor;
        	this.command = command;
        	this.aliases = aliases;
        	this.pluginCommand = createPluginCommand(main);
        	registerPluginCommand();
    }
    
    private PluginCommand createPluginCommand(Main main) {
        try {
            Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            c.setAccessible(true);
            
            PluginCommand cmd = c.newInstance(command, main);
            cmd.setAliases(aliases);
            cmd.setExecutor(executor);

            return cmd;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    private void registerPluginCommand() {
        if (pluginCommand == null) return;
        try {
            Field f = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            CommandMap commandMap = (CommandMap) f.get(Bukkit.getPluginManager());
            commandMap.register("yPoints", pluginCommand);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
