package com.ystoreplugins.ypoints.enums;

public enum SubCommands {

	HELP("SubCommands.Help", ""),
	PAY("SubCommands.Pay", ""),
	SET("SubCommands.Set", ""),
	ADD("SubCommands.Add", ""),
	REMOVE("SubCommands.Remove", ""),
	RELOAD("SubCommands.Reload", ""),
	TOP("SubCommands.Top", "");

	private String config;
	private String value;

	private SubCommands(String config, String value) {
		this.config = config;
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String name) {
		this.value = name;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}
	
	public enum Usage {
		
		PAY("Usage.Pay", ""),
		SET("Usage.Set", ""),
		ADD("Usage.Add", ""),
		REMOVE("Usage.Remove", "");

		private String config;
		private String value;

		private Usage(String config, String value) {
			this.config = config;
			this.value = value;
		}

		public String getValue() {
			return value;
		}
		
		public void setValue(String name) {
			this.value = name;
		}

		public String getConfig() {
			return config;
		}

		public void setConfig(String config) {
			this.config = config;
		}
	}
}
