package com.windea.test;

import org.junit.jupiter.api.Test;

//WARN 使用先行版本，无法识别WebApplicationInitializer！！！
class Java12Test {
	int getNum(int num) {
		return num;
	}

	//@Tested(condition = "语言级别：12，添加编译参数--enable-preview")
	//@Test
	//void switchTest1() {
	//	int num = this.getNum(2);
	//	String str = switch(num) {
	//		case 1 -> "one";
	//		case 2 -> "two";
	//		default -> "other";
	//	};
	//	System.out.println(str);
	//}

	@Test
	void switchTest2() {
		int num = getNum(2);
		String str;
		switch(num) {
			case 1:
				str = "one";
				break;
			case 2:
				str = "two";
				break;
			default:
				str = "other";
				break;
		}
		System.out.println(str);
	}

	//@Test
	//@Tested(condition = "（可能）语言级别：13")
	//void multilineStringTest() {
	//	var str = `
	//	123456
	//	`;
	//	System.out.println(str);
	//}
}
