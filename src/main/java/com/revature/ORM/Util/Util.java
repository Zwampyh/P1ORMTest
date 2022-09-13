package com.revature.ORM.Util;

import java.util.ArrayList;

public class Util {

	public static String ArrayListToString(ArrayList<?> array) {
		String string = array.toString();
		string = string.substring(1, string.length() -1);
		
		
		return string;
	
	}

	public static String ArrayListToStringWQ(ArrayList<Object> array) {
		String string = "";
		for(Object i : array) {
			if (i instanceof String) {i = "'" + i + "'";} 
			String temp = i.toString();
			string += "" + temp + ", ";	
		}
		string = string.substring(0, string.length()-2);
		return string;
	}
	
	
	
	
}
