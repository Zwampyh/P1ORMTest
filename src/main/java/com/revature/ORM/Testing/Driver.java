package com.revature.ORM.Testing;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.ORM.Database.Database;

public class Driver {
	
	public static void main(String[] args) throws SQLException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		
		/*
		 * This class is just a main method with examples to show how to use the ORM and to test it's working with my personal database.
		 * This class is not necessary but the classes ObjTest1 and ObjTest2 are used in the Unit tests
		 */
		
		
		
		
		//Database db = new Database("jdbc:postgresql://postgres.ccyxl0ztd58i.us-west-2.rds.amazonaws.com:5432/", System.getenv("db_name"), System.getenv("db_pass"));
	
		//ObjTest1 Davinci = new ObjTest1();
		//Davinci.usernames = "Davinci";
		//Davinci.passwords = "Dinci";
		
		
		
		//db.insert(Davinci);
		
		//Object query =  db.where("Davinci", "login", "usernames", ObjTest1.class);
		//ObjTest1 row = (ObjTest1) query;
		
		
		//ObjTest2 acco = new ObjTest2();
		//acco.usernames = "Davinci";
		//acco.acc_balance = 2.0;
		//acco.acc_num = 4.0;
		
		//db.insert(acco);
		
		//Object account = db.where("Davinci", "account", "usernames", ObjTest2.class);
		//ObjTest2 acc = (ObjTest2) account;
		//System.out.println(acc.acc_balance );
		
		//db.update(Davinci, "passwords", "rdaf");
		//Object query2 =  db.where("Davinci", "login", "usernames", ObjTest1.class);
		//ObjTest1 row2 = (ObjTest1) query;
		
		//System.out.println();
		//System.out.println(row2.usernames);
		//System.out.println(row2.passwords);
		
		
		//db.delete(Davinci);
	}
	
}
