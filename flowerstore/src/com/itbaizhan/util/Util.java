package com.itbaizhan.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	
	
	
	
	//获取当前系统时间
	public static String getTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
	//获取当前系统时间
	public static String getTime2(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return sdf.format(date.getTime());
	}
	
}
