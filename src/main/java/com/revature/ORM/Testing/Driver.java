package com.revature.ORM.Testing;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.ORM.Database.Database;

public class Driver {
	public static void main(String[] args) throws SQLException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		 Database db = new Database("jdbc:postgresql://postgres.ccyxl0ztd58i.us-west-2.rds.amazonaws.com:5432/", System.getenv("db_name"), System.getenv("db_pass"));

		ObjTest1 Davinci = new ObjTest1("Davinci", "Dinci");
		
		//db.insert(Davinci);
		ArrayList<Object> loginCs =  db.where("Davinci", "login", "usernames");
		ObjTest1 loginC = new ObjTest1(loginCs.get(0).toString(), loginCs.get(1).toString());
		System.out.println(loginC.usernames + " " + loginC.passwords);
	}
}
