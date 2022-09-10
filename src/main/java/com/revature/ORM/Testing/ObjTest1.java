package com.revature.ORM.Testing;

import com.revature.ORM.Annot.Table;

@Table (name = "login")
public class ObjTest1 {
	public String usernames;
	public String passwords;

public ObjTest1(String username, String password) {
	super();
	this.usernames = username;
	this.passwords = password;
}

}
