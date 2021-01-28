package com.ystoreplugins.ypoints.methods;

import com.ystoreplugins.ypoints.Main;
import com.ystoreplugins.ypoints.enums.Messages;
import com.ystoreplugins.ypoints.enums.SubCommands;
import com.ystoreplugins.ypoints.enums.SubCommands.Usage;

public class UpdateEnums {
	
	public UpdateEnums(Main main) {
		for (SubCommands s : SubCommands.values()) {
			s.setValue(main.commands.getConfig().getString(s.getConfig()).replace('&', '§'));
		}
		
		for (Usage s : SubCommands.Usage.values()) {
			s.setValue(main.commands.getConfig().getString(s.getConfig()).replace('&', '§'));
		}
		
		for (Messages s : Messages.values()) {
			s.setValue(main.commands.getConfig().getString(s.getName()).replace('&', '§'));
		}
	}
}
