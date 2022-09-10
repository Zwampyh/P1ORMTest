package com.revature.ORM.Database;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.ORM.Objects.Pojo;
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

	
	public String getWhereString() {
		return null;
		
	}
	

	public ArrayList<Object> where(String pk, String table, String column) {
		ArrayList<Object> values = new ArrayList<>();
		final String sql = "SELECT * FROM " + table + " WHERE " + column + " = '" + pk + "';";
		try(PreparedStatement statement = database.connection.prepareStatement(sql);)
		{
			ResultSet set = statement.executeQuery();
			final ResultSetMetaData meta = set.getMetaData();
			final int columnC = meta.getColumnCount();
			while (set.next()) {	
				for(int i = 1; i <= columnC; i++) {
				values.add(set.getObject(i));
				}
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return values;
	}
}
