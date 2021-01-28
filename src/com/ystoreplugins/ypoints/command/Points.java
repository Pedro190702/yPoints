package com.ystoreplugins.ypoints.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.command.subcommands.Add;
import com.ystoreplugins.ypoints.command.subcommands.Look;
import com.ystoreplugins.ypoints.command.subcommands.Pay;
import com.ystoreplugins.ypoints.command.subcommands.Remove;
import com.ystoreplugins.ypoints.command.subcommands.Set;
import com.ystoreplugins.ypoints.enums.Messages;
import com.ystoreplugins.ypoints.enums.SubCommands;
import com.ystoreplugins.ypoints.methods.GetTop;
import com.ystoreplugins.ypoints.methods.UpdateEnums;
import com.ystoreplugins.ypoints.utils.Formatter;

public class Points implements CommandExecutor {
	
	private final Main main;
	
	public Points(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (!(sender instanceof Player)) {
				main.config.commandHelp.forEach(ch -> sender.sendMessage(ch));
				return true;
			}
			
			if (!verifyPermission(sender, "look"))
				return true;
			
			Player p = (Player) sender;
			String points = Formatter.letterFormatter(main.mainDataManager.USERS.getCached(p.getName()).getPoints());
			p.sendMessage(Messages.PLAYERPOINTS.getValue().replace("{points}", points));
			return true;
		}
		
		if (args.length < 3) {
			if (args[0].equalsIgnoreCase(SubCommands.HELP.getValue())) {
				if (!verifyPermission(sender, "help"))
					return true;
				
				main.config.commandHelp.forEach(ch -> sender.sendMessage(ch));
				return true;
			}
			
			if (args[0].equalsIgnoreCase(SubCommands.PAY.getValue())) {
				if (!verifyPermission(sender, "pay"))
					return true;
				
				sender.sendMessage(SubCommands.Usage.PAY.getValue());
				return true;
			}
			
			if (args[0].equalsIgnoreCase(SubCommands.SET.getValue())) {
				if (!verifyPermission(sender, "set"))
					return true;
				
				sender.sendMessage(SubCommands.Usage.SET.getValue());
				return true;
			}
			
			if (args[0].equalsIgnoreCase(SubCommands.ADD.getValue())) {
				if (!verifyPermission(sender, "add"))
					return true;
				
				sender.sendMessage(SubCommands.Usage.ADD.getValue());
				return true;
			}
			
			if (args[0].equalsIgnoreCase(SubCommands.REMOVE.getValue())) {
				if (!verifyPermission(sender, "remove"))
					return true;
				
				sender.sendMessage(SubCommands.Usage.REMOVE.getValue());
				return true;
			}
			
			if (args[0].equalsIgnoreCase(SubCommands.RELOAD.getValue())) {
				if (!verifyPermission(sender, "reload"))
					return true;
				
				main.reloadConfig();
				main.commands.reloadConfig();
				main.config.setup(main);
				new UpdateEnums(main);
				sender.sendMessage(Messages.RELOAD.getValue());
				return true;
			}
			
			if (args[0].equalsIgnoreCase(SubCommands.TOP.getValue())) {
				if (!verifyPermission(sender, "top"))
					return true;
				
				if (!(sender instanceof Player)) {
					main.config.commandHelp.forEach(ch -> sender.sendMessage(ch));
					return true;
				}
				Player p = (Player) sender;
				new GetTop().show(p, main);
				return true;
			}
			if (!verifyPermission(sender, "look.other"))
				return true;
			
			new Look(sender, args[0]);
		}
		
		if (args[0].equalsIgnoreCase(SubCommands.PAY.getValue())) {
			if (!verifyPermission(sender, "pay"))
				return true;
			
			new Pay(main, sender, args[1], args[2]);
			return true;
		}
		
		if (args[0].equalsIgnoreCase(SubCommands.SET.getValue())) {
			if (!verifyPermission(sender, "set"))
				return true;
			
			new Set(sender, args[1], args[2]);
			return true;
		}
		
		if (args[0].equalsIgnoreCase(SubCommands.ADD.getValue())) {
			if (!verifyPermission(sender, "add"))
				return true;
			
			new Add(sender, args[1], args[2]);
			return true;
		}
		
		if (args[0].equalsIgnoreCase(SubCommands.REMOVE.getValue())) {
			if (!verifyPermission(sender, "remove"))
				return true;
			
			new Remove(sender, args[1], args[2]);
			return true;
		}
		return false;
	}
	
	private boolean verifyPermission(CommandSender sender, String perm) {
		if (!sender.hasPermission("ypoints." + perm)) {
			sender.sendMessage(Messages.PERMISSION.getValue());
			return false;
		}
		return true;
	}
}
