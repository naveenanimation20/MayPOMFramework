package com.qa.hubspot.util;

public class TimeUtil {
	
	public static void shortWait(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void mediumWait(){
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void longWait(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

}
