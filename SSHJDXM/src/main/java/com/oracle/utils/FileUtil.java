package com.oracle.utils;


public class FileUtil {
	public static String getSuffix(String fileName){
		return fileName.substring(fileName.lastIndexOf("."), fileName.length());
	}
	public static String getNewFileName(){
		return UUIDUtil.randomUUID();
	}
}
