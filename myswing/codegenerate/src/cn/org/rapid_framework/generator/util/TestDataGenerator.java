package cn.org.rapid_framework.generator.util;

import java.sql.Timestamp;

/**
 * 
 * @author badqiu
 *
 */
public class TestDataGenerator {
	
	/*
	 * 
	    Username ------java.lang.String-----256
        Pwd ------java.lang.String-----256
        Nickname ------java.lang.String-----256
        CreatedBy ------java.lang.String-----255
        CreatedTime ------java.util.Date-----7
        ModifiedBy ------java.lang.String-----255
        ModifiedTime ------java.util.Date-----7
        OwnerId ------java.lang.String-----255
        Deleted ------Long-----22
        Status ------Long-----22
        Locked ------Long-----22
        LockedBy ------java.lang.String-----255
        Id ------java.lang.String-----32
        GroupId ------java.lang.String-----32
	 */

	public String getDBUnitTestData(String columnName,String javaType, int size) {
		if(size <= 0) size = 2;
		
		int MAX_SIZE = 3;
		if(javaType.indexOf("Boolean") >= 0) {
			return "0";
		}
		if(javaType.indexOf("Timestamp") >= 0) {
			return new Timestamp(System.currentTimeMillis()).toString();
		}
		if(javaType.indexOf("java.sql.Date") >= 0) {
			return new java.sql.Date(System.currentTimeMillis()).toString();
		}
		if(javaType.indexOf("java.sql.Time") >= 0) {
			return  new java.sql.Time(System.currentTimeMillis()).toString();
		}
		if(javaType.indexOf("java.util.Date") >= 0) {
			return  new Timestamp(System.currentTimeMillis()).toString();
		}
		if(javaType.indexOf("String") >= 0) {
			if(size > columnName.length()) {
				int tempSize = Math.min(size - columnName.length(), MAX_SIZE);
				return columnName + StringHelper.randomNumeric(tempSize);
			}
			return StringHelper.randomNumeric(Math.min(size, MAX_SIZE));
		}
		if(isNumberType(javaType)){
			return StringHelper.randomNumeric(Math.min(size, MAX_SIZE));
		}
		return "";
	}

	private static boolean isNumberType(String javaType) {
		javaType = javaType.toLowerCase();
		if(javaType.indexOf("byte") >= 0 
				|| javaType.indexOf("short") >= 0 
				|| javaType.indexOf("integer") >= 0 
				|| javaType.indexOf("int") >= 0 
				|| javaType.indexOf("long") >= 0 
				|| javaType.indexOf("double") >= 0 
				|| javaType.indexOf("bigdecimal") >= 0 
				|| javaType.indexOf("float") >= 0) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		TestDataGenerator  tdg = new TestDataGenerator();
		
	}
	
}
