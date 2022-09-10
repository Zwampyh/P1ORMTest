package com.revature.ORM.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;



public class Database {
	String url;
	String name;
	String pass;
	Connection connection = null;
	
	public Database(String url, String name, String pass) throws SQLException {
		this.connection = DriverManager.getConnection(url, name, pass);
	}
	
	public void insert(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException {
		 new Query(this).insert(obj);
	}


	public ArrayList<Object>  where(String pk, String table, String column) {
		ArrayList<Object> values = new Query(this).where(pk, table, column);
		return values;
	}
	
}
