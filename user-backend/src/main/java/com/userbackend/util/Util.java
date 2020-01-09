package com.userbackend.util;

public class Util {
//	private static final String DB_HOST			= "localhost";
//	private static final String DB_PORT			= "27017";
//	public static final String DB_NAME			= "db_user";
//	public static final String COLLECTION_USER	= "user_collection";
//	
//	public static final String URI_MONGO		= "mongodb://" + Util.DB_HOST + ":" + Util.DB_PORT;
	
	public static void concatWhere(String sql) {
		if(!sql.toLowerCase().contains("where")) {
			sql = sql + " where ";
		}
	}
	
}
