package com.windea.test;

import com.windea.utility.utils.base.EncodingUtils;
import com.windea.utility.utils.spring.PropertiesUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.Properties;

class PropsUtilsTest {

	@Test
	void test1() throws Exception {
		//乱码
		var props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/test.properties"));
		System.out.println(props.getProperty("name"));
		System.out.println(props.getProperty("name_ch"));
		System.out.println();
	}

	@Test
	void test2() throws Exception {
		//不乱码
		var props = PropertiesLoaderUtils
				.loadProperties(new EncodedResource(new ClassPathResource("/test.properties"), "utf-8"));
		System.out.println(props.getProperty("name"));
		System.out.println(props.getProperty("name_ch"));
		System.out.println();
	}

	@Test
	void test3() throws Exception {
		//找不到指定文件
		var props = new Properties();
		props.load(new FileReader("/test.properties", Charset.forName("utf-8")));
		System.out.println(props.getProperty("name"));
		System.out.println(props.getProperty("name_ch"));
		System.out.println();
	}

	@Test
	void test4() throws Exception {
		//乱码，并且路径不能以/开头
		var props = new Properties();
		var input = PropertiesLoaderUtils.class.getClassLoader().getResourceAsStream("test.properties");
		props.load(input);
		System.out.println(props.getProperty("name"));
		System.out.println(props.getProperty("name_ch"));
		System.out.println();
	}

	@Test
	void test5() throws Exception {
		//成功
		var props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/test.properties"));
		props = PropertiesUtils.encoding(props, "utf-8");
		System.out.println(props.getProperty("name"));
		System.out.println(props.getProperty("name_ch"));
		System.out.println();
	}

	@Test
	void test6() throws Exception {
		//成功，但是依赖Spring
		var props = PropertiesUtils.load("/test.properties");
		System.out.println(props.getProperty("name"));
		System.out.println(props.getProperty("name_ch"));
		System.out.println();
	}

	@Test
	void test7() throws Exception {
		//成功
		var props = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/test.properties"));
		props = EncodingUtils.encoding(props);
		System.out.println(props.getProperty("name"));
		System.out.println(props.getProperty("name_ch"));
		System.out.println();
	}
}
