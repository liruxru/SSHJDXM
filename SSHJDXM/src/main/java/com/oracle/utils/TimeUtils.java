package com.oracle.utils;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtils {

	public static Timestamp getCxminzhuce(String str){
		
		String hms="00:00:00";
		String cxminzhuce=str.substring(0, str.lastIndexOf("t"));
		String ss=cxminzhuce+hms;
		Timestamp t=Timestamp.valueOf(ss);
		return t;
	}
	
	public static Timestamp getCxMaxzguce(String str){
		String hms=" 00:00:00";
		String cxmaxzhuce=str.substring(str.lastIndexOf(" "), str.length());
		String ss=cxmaxzhuce+hms;
		Timestamp t=Timestamp.valueOf(ss);
		return t;
	}
	
}
