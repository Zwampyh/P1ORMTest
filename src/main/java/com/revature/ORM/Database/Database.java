package com.revature.ORM.Database;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.ORM.Testing.ObjTest1;



public class Database {
	String url;
	String name;
	String pass;
	Connection connection = null;
	
	public Database() {}
	
	public Database(String url, String name, String pass) throws SQLException {
		this.connection = DriverManager.getConnection(url, name, pass);
	}
	
	public void insert(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException {
		 new Query(this).insert(obj);
	}


	public Object  where(Object what, String table, String column, Class clazz) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		Object obj = new Query(this).where(what, table, column, clazz);
		return obj;
	}
	
	
	public void update(Object obj, String column, Object newValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		new Query(this).update(obj, column, newValue);
	}

	public void delete(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		new Query(this).delete(obj);
		
	}
	

	
}
