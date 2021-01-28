package com.ystoreplugins.ypoints.utils;

import java.text.DecimalFormat;

public class Formatter {
	
	public static String[] formats = new String[0];
	
	public static String letterFormatter(Object bigDecimal) {

		String val = new DecimalFormat("#,###").format(bigDecimal).replace(".", ",");
		Integer ii = val.indexOf(","), i = val.split(",").length;
		if (ii == -1)
			return val;
		return (val.substring(0, ii + 2) + formats[i]).replace(",0", "");
	}
}
