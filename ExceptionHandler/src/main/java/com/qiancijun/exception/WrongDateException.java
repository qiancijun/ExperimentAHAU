package com.qiancijun.exception;

public class WrongDateException extends Exception {
	private String msg;

	public WrongDateException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}
