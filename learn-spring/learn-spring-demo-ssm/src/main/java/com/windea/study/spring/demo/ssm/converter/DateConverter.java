package com.windea.study.spring.demo.ssm.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateConverter implements Converter<String, Date> {
	@Override
	public Date convert(String source) {
		var format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
		try {
			return format.parse(source);
		} catch(ParseException e) {
			return null;
		}
	}
}
