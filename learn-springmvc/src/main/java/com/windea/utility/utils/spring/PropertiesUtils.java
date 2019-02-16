package com.windea.utility.utils.spring;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesUtils {
	public static Properties load(String path) {return load(path, "utf-8");}

	public static Properties load(String path, String encode) {
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(new EncodedResource(new ClassPathResource(path), encode));
		} catch(Exception ignored) {}
		return props;
	}

	public static Properties encoding(Properties props, String encode) throws Exception {
		var result = new Properties();
		for(var entry : props.entrySet()) {
			var key = new String(entry.getKey().toString().getBytes(), StandardCharsets.UTF_8);
			var value = new String(entry.getValue().toString().getBytes(), StandardCharsets.UTF_8);
			result.setProperty(key, value);
		}
		return result;
	}
}
