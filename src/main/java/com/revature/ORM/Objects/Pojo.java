package com.revature.ORM.Objects;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import com.revature.ORM.Annot.Table;


public class Pojo {
	
	public String table;
	public ArrayList<String> columns = new ArrayList<>();
	public ArrayList<Object> values = new ArrayList<>();
	public Map<String, Property> propertyMap = new LinkedHashMap<>();
	
	
	public Pojo (Class<?> clazz) {
Table annot = clazz.getAnnotation(Table.class);
		table = annot.name();
		Field[] fields = (clazz.getDeclaredFields());
		for (Field field : fields) {
			columns.add(field.getName());
		}
		getProperties(clazz);
		
	}

	public void getProperties (Class<?> clazz) {
		for(Field field : clazz.getFields()) {
			Property prop = new Property();
			prop.name = field.getName();
			prop.field = field;
			prop.dataType = field.getType();
			propertyMap.put(prop.name, prop);
		}
	}

		


}
