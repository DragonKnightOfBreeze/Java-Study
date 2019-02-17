package com.windea.utility.utils.base;

import java.util.Properties;

public class EncodingUtils {
	/**
	 * 重新编码指定的字符串。
	 */
	public static String encoding(String str) {
		return encoding(str, "UTF-8");
	}

	/**
	 * 重新编码指定的字符串。
	 */
	public static String encoding(String str, String charsetName) {
		return encoding(str, "ISO-8859-1", charsetName);
	}

	/**
	 * 重新编码指定的字符串。
	 */
	public static String encoding(String str, String rCharsetName, String charsetName) {
		try {
			return new String(str.getBytes(rCharsetName), charsetName);
		} catch(Exception e) {
			System.out.println("Failed to encoding.");
			return str;
		}
	}


	/**
	 * 重新编码指定的属性文件。
	 */
	public static Properties encoding(Properties properties) {
		return encoding(properties, "UTF-8");
	}

	/**
	 * 重新编码指定的属性文件。
	 */
	public static Properties encoding(Properties properties, String charsetName) {
		return encoding(properties, "ISO-8859-1", charsetName);
	}

	/**
	 * 重新编码指定的属性文件。
	 */
	public static Properties encoding(Properties properties, String rCharsetName, String charsetName) {
		try {
			var result = new Properties();
			for(var entry : properties.entrySet()) {
				var key = new String(entry.getKey().toString().getBytes(rCharsetName), charsetName);
				var value = new String(entry.getValue().toString().getBytes(rCharsetName), charsetName);
				result.setProperty(key, value);
			}
			return result;
		} catch(Exception e) {
			System.out.println("Failed to encoding.");
			return properties;
		}
	}
}
