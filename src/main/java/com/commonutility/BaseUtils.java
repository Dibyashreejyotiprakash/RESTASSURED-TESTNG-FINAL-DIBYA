package com.commonutility;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class BaseUtils {
	
	public static Integer getRandomNumber() {
		Random num = new Random();
		int randomnum = num.nextInt(1000);
		return randomnum;
	}
	
	public static String getRandomString() {
		String randomstring = RandomStringUtils.random(1000);
		return randomstring;
	}

}
