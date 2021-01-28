package com.ystoreplugins.ypoints.enums;

public enum Messages {

	PERMISSION("Permission", ""),
	PLAYERPOINTS("Player points", ""),
	PLAYERPOINTSTARGET("Player points target", ""),
	RELOAD("Reload", ""),
	NOTANUMBER("Not a number", ""),
	PLAYERNOTFOUND("Player not found", ""),
	PAYYOURSELF("Pay yourself", ""),
	PAY("Pay", ""),
	PAYTARGET("Pay target", ""),
	ADD("Add", ""),
	REMOVE("Remove", ""),
	REMOVEMORE("Remove more", ""),
	SET("Set", ""),
	;

	private String name;
	private String value;

	private Messages(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}