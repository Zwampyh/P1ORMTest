package com.revature.ORM.Database;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ORM.Objects.Pojo;
import com.revature.ORM.Objects.Property;
import com.revature.ORM.Testing.ObjTest1;
import com.revature.ORM.Util.Util;

public class Query {
	Object obj;
	Database database;
	Pojo pojo;
	ArrayList<Object> values = new ArrayList<>();
	
	
	public Query(Database database) {
		this.database = database;

		
		
	}
	
	public ArrayList<Object> getValues(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Pojo pojo = new Pojo(obj.getClass());
		for(String c : pojo.columns) {
			Class<?> clazz = obj.getClass();
			Field field = clazz.getDeclaredField(c);
			Object value = field.get(obj);
			values.add(value);
		}
		return values;
	}
	
	public ArrayList<String> getColumns(Object obj) {
		Pojo pojo = new Pojo(obj.getClass());
		return pojo.columns;
	}
	
	public String getTable(Object obj) {
		Pojo pojo = new Pojo(obj.getClass());
		return pojo.table;
	}
	
	
	public String getInsertString(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String table = getTable(obj);
		ArrayList<String> columns = getColumns(obj);
		ArrayList<Object> values = getValues(obj);
		String cString = Util.ArrayListToString(columns);
		String vString = Util.ArrayListToStringWQ(values);
		String sql = "INSERT INTO " + table + " ("+cString+") values ("+vString+");";
		return sql;
		
	}
	
	public void insert(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException {
		final String sql = getInsertString(obj);
		
		try(PreparedStatement statement = database.connection.prepareStatement(sql);)
		{
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public <T> Object getConstructor(Class<?> clazz) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object row = clazz.getDeclaredConstructor().newInstance();
		return row;
	}
	
	public void putValue(Object con, String name, Object value, Class<?> clazz) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Pojo pojo = new Pojo(clazz);
		Property prop = pojo.propertyMap.get(name);
		prop.field.set(con, value);
	}
	

	public Object where(String what, String table, String column, Class<?> clazz) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		ArrayList<Object> values = new ArrayList<>();
		ArrayList<String> columns = new ArrayList<>();
		ArrayList<Object> rows = new ArrayList<>();
		Object row = getConstructor(clazz);
		final String sql = "SELECT * FROM " + table + " WHERE " + column + " = '" + what + "';";
		try(PreparedStatement statement = database.connection.prepareStatement(sql);)
		{
			ResultSet set = statement.executeQuery();
			final ResultSetMetaData meta = set.getMetaData();
			final int columnC = meta.getColumnCount();
			while (set.next()) {	
				for(int i = 1; i <= columnC; i++) {
				values.add(set.getObject(i));
				columns.add(meta.getColumnLabel(i));
				}
				
			for(int i = 0; i < values.size(); i++) {
				putValue(row, columns.get(i), values.get(i), clazz);
				}
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return row;
	}

	public void update(Object obj, String column, String newValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final String sql = getUpdateString(obj, column, newValue);
		
		try(PreparedStatement statement = database.connection.prepareStatement(sql);)
		{
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
			
		
		
	}

	private String getUpdateString(Object obj, String column, Object newValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String table = getTable(obj);
		Pojo pojo = new Pojo(obj.getClass());
		ArrayList<String> columns = pojo.columns;
		String column2 = columns.get(0);
		ArrayList<Object> values = getValues(obj);
		Object oldValue = values.get(0);
		if (newValue instanceof String) {newValue = "'" + newValue +"'";}
		if (oldValue instanceof String) {oldValue = "'" + oldValue +"'";}
		String sql = "UPDATE " + table + " SET " + column + " = " + newValue + " WHERE " + column2 + " = " + oldValue + ";";
		return sql;
	}

	public void delete(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final String sql = getDeleteString(obj);
		try(PreparedStatement statement = database.connection.prepareStatement(sql);)
		{
			statement.execute();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private String getDeleteString(Object obj) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String table = getTable(obj);
		Pojo pojo = new Pojo(obj.getClass());
		ArrayList<String> columns = pojo.columns;
		String column = columns.get(0);
		System.out.println(column);
		ArrayList<Object> values = getValues(obj);
		Object value = values.get(0);
		System.out.println(value);
		if (value instanceof String) {value = "'" + value +"'";}
		String sql = "DELETE FROM " + table + " WHERE " + column + " = " + value + ";";
		System.out.println(sql);
		return sql;
	}
		
	
}
