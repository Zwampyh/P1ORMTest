package com.revature.ORM.Objects;


import java.lang.reflect.Field;
import java.util.ArrayList;

import com.revature.ORM.Annot.Table;


public class Pojo {
	
	public String table;
	public ArrayList<String> columns = new ArrayList<>();
	public ArrayList<Object> values = new ArrayList<>();
	
	
	
	public Pojo (Class<?> clazz) {
		Table annot = clazz.getAnnotation(Table.class);
		table = annot.name();
		Field[] fields = (clazz.getDeclaredFields());
		for (Field field : fields) {
			columns.add(field.getName());
		}
	}
		


}
