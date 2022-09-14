package com.revature.ORM.Testing;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import com.revature.ORM.Database.Database;




public class AccountDao {

	
	public Account getAccountById(int accId) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException, SQLException {
		Database db = new Database("jdbc:postgresql://postgres.ccyxl0ztd58i.us-west-2.rds.amazonaws.com:5432/", System.getenv("db_name"), System.getenv("db_pass"));
		Account acc = (Account) db.where(accId, "account", "acc_num", Account.class);
		return acc;
	}
	
}
