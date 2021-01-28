package com.ystoreplugins.ypoints.methods;

public class IsNumeric {
	
	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}
}
