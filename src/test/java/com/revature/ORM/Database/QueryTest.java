package com.revature.ORM.Database;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import com.revature.ORM.Testing.ObjTest1;
import com.revature.ORM.Testing.ObjTest2;

@RunWith(MockitoJUnitRunner.class)
public class QueryTest {
	
	@Mock
	public Database db;
	
	Connection c;
	
	public ObjTest1 obj;
	
	public ObjTest2 obj2;
	
	public Query query;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		
		//db = new Database("jdbc:postgresql://postgres.ccyxl0ztd58i.us-west-2.rds.amazonaws.com:5432/", System.getenv("db_name"), System.getenv("db_pass"));
		
		obj = new ObjTest1();
		obj.passwords = "pTest";
		obj.usernames = "uTest";
		
		obj2 = new ObjTest2();
		obj2.acc_balance = 2.7;
		obj2.acc_num = 2.9;
		obj2.usernames = "uTest";
		
		query = new Query(db);
		
		
		
		
		
	}
	
	@Test
	void getInsertStringTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		assertEquals("INSERT INTO login (usernames, passwords) values ('uTest', 'pTest');", query.getInsertString(obj));
	}
	
	@Test
	void getBadInsertStringTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		assertEquals("INSERT INTO account (usernames, acc_num, acc_balance) values ('uTest', 2.9, 2.7);", query.getInsertString(obj2));
	}
	
	@Test
	void getValuesTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ArrayList<Object> correctValues = new ArrayList<>();
		correctValues.add("uTest");
		correctValues.add("pTest");
		assertEquals(correctValues, query.getValues((obj)));
	}
	
	@Test
	void getColumnsTest() {
	ArrayList<String> correctColumns = new ArrayList<>();
	correctColumns.add("usernames");
	correctColumns.add("passwords");
	assertEquals(correctColumns, query.getColumns(obj));
	}
	
	@Test
	void getTableTest() {
		String table = "account";
		assertEquals(table, query.getTable(obj2));
	}

	@Test
	void whereTestDB() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, SQLException {
		Database db = mock(Database.class);
		
		
		Mockito.when(db.where(obj.usernames, query.getTable(obj), query.getColumns(obj).get(0), obj.getClass())).thenReturn(obj);
		
		assertEquals(obj, db.where("uTest", "login", "usernames", obj.getClass()));
	}
	
	
	@Test
	void updateQTest() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		Query q = mock(Query.class);
		
		Mockito.when(q.where(obj.usernames, query.getTable(obj), query.getColumns(obj).get(0), obj.getClass())).thenReturn(obj);
		
		assertEquals(obj, q.where("uTest", "login", "usernames", obj.getClass()));
	}
	@Test
	void insertTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException {
		Query q = mock(Query.class);
		
		doNothing().when(q).insert(obj);	
	}

	@Test
	void deleteTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, SQLException {
		Query q = mock(Query.class);
		
		doNothing().when(q).delete(obj);
		
		
	}
	
	@Test
	void getDeleteStringTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		assertEquals("DELETE FROM login WHERE usernames = 'uTest';", query.getDeleteString(obj));
	}
	
	@Test
	void getUpdateStringTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		assertEquals("UPDATE login SET usernames = 'testeroni' WHERE usernames = 'uTest';", query.getUpdateString(obj, "usernames", "testeroni"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
