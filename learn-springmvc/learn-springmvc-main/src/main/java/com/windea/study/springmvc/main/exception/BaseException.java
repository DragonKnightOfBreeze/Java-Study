package com.windea.study.springmvc.main.exception;

/**
 * 自定义异常类
 * <p>针对预期的异常进行抛出
 */
public class BaseException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "出错了！";

	public BaseException() {
		super(MESSAGE);
	}

	public BaseException(Throwable throwable) {
		super(MESSAGE, throwable);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
