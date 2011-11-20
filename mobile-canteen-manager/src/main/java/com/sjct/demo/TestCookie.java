package com.sjct.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TestCookie {

	static Pattern pattern = Pattern.compile("ad[0-9]{1,5}");

	static Matcher matcher = pattern.matcher("");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String aaa = "ad302";

		if (matcher.reset(aaa).find()) {
			System.out.println("find element aaa");
		}

		String bbb = "ad93022";

		if (matcher.reset(bbb).find()) {
			System.out.println("find element bbb");
		}

		String ccc = "adcokie";

		if (matcher.reset(ccc).find()) {
			System.out.println("find element ccc");
		}
	}

}
