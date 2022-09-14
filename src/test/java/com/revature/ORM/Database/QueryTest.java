package com.revature.ORM.Database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.ORM.Objects.Pojo;
import com.revature.ORM.Testing.ObjTest1;
import com.revature.ORM.Testing.ObjTest2;

@RunWith(MockitoJUnitRunner.class)
public class QueryTest {
	
	@Mock
	public Database db;
	
	
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

	/* @Test
	void whereTest() throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		Database db = mock(Database.class);
		Mockito.when(db.where(obj.usernames, query.getTable(obj), "usernames", obj.getClass())).thenReturn(obj);
		
		assertEquals(obj, query.where("uTest", "login", "pTest", obj.getClass()));
	}*/
	
}
