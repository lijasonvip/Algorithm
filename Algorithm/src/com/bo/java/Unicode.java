package com.bo.java;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Unicode {

	public static void main(String[] args) throws IOException {
		
		//编码转换
		String s1 = "你好";
		String s2 = new String(s1.getBytes("GB2312"), "ISO-8859-1");
		
		//
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println(df.format(0.35678));
		
		//日期和时间
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH));		//从0开始 8月输出7
		System.out.println(cal.get(Calendar.DATE));
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
		System.out.println(cal.get(Calendar.MINUTE));
		System.out.println(cal.get(Calendar.SECOND));
		
		//java 8 引入 LocalDateTime dt = LocalDateTime.now(); dt.getYear() dt.getMonthValue() dt.get...
		
		//获取毫秒参数
		long mills = Calendar.getInstance().getTimeInMillis();
		System.out.println(mills);
		//或者
		System.out.println(System.currentTimeMillis());
		
		//或者 java 8
		//Clock.systemDefaultZone().millis();
		
		//Java.text.DataFormat 子类 SimpleDateFormat 中format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date1 = new Date(mills);
		System.out.println(dateFormat.format(date1));
		
		/** 
		   * 字符串转换为java.util.Date<br> 
		   * 支持格式为 yyyy.MM.dd G 'at' hh:mm:ss z 如 '2002-1-1 AD at 22:10:59 PSD'<br> 
		   * yy/MM/dd HH:mm:ss 如 '2002/1/1 17:55:00'<br> 
		   * yy/MM/dd HH:mm:ss pm 如 '2002/1/1 17:55:00 pm'<br> 
		   * yy-MM-dd HH:mm:ss 如 '2002-1-1 17:55:00' <br> 
		   * yy-MM-dd HH:mm:ss am 如 '2002-1-1 17:55:00 am' <br> 
		   * @param time String 字符串<br> 
		   * @return Date 日期<br> 
		   */ 
	}
}
