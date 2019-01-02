package msc.databasepool;

import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapToBean {

	@SuppressWarnings("unchecked")
	public static <E> E conversion(Map map, Class clazz) {
		Map<Object, Object> keyMap = new HashMap<Object, Object>();
		Set<?> set = map.keySet();
		Iterator<?> iterator = set.iterator();
		while (iterator.hasNext()) {
			Object strKey = iterator.next(); // 得到key
			Object strValue = map.get(strKey); // 得到value
			strValue = strValue == null ? "" : strValue;
			strKey = BaseDaoUtil.getStrStandardization(strKey.toString()).toLowerCase();
			// System.out.println(strKey+":"+strValue);
			keyMap.put(strKey, strValue);
		}

		Object resultObject = null;
		try {
			resultObject = clazz.newInstance();
			// Class<?> clazz = object.getClass();
			// System.out.println("Class :" + classType.getName());
			Field[] fields = clazz.getDeclaredFields(); // 获得对象的所有字段

			int fieldsCount = fields.length;
			for (int i = 0; i < fieldsCount; i++) { // 循环每个字段
				Field field = fields[i]; // 当前字段
				String fieldName = field.getName(); // 字段名称

				String fieldTypeFullName = field.getType().toString().toLowerCase();// 字段类型全名
				if (fieldTypeFullName.lastIndexOf(".") != -1) {
					// System.out.println("fieldName:" + fieldName);
					String firstLetter = fieldName.substring(0, 1).toUpperCase(); // 获得和字段对应的getXXX()方法的名字：get+属性名称的第一个字母并转成大小+属性名去掉第一个字母
					String setMethodName = "set" + firstLetter + fieldName.substring(1); // 获得和属性对应的setXXX()方法的名字
					Method setMethod = clazz.getMethod(setMethodName, field.getType()); // 获得和属性对应的setXXX()方法，传入参数为参数的类型
					// System.out.println(fieldName.toLowerCase());
					if (keyMap.containsKey((fieldName = fieldName.toLowerCase()))) {
						// System.out.println(fieldName);
						Object strValue = keyMap.get(fieldName);
						// System.out.println(strValue);

						if (fieldTypeFullName.indexOf("string") != -1) {
							strValue = strValue.equals(false) ? "0" : strValue.equals(true) ? "1" : strValue.toString();
						} else {
							strValue = strValue.equals(false) ? 0
									: strValue.equals(true) ? 1
											: "".equals(strValue.toString()) || null == strValue.toString() ? 0
													: strValue;

							if (fieldTypeFullName.indexOf("integer") != -1) {
								strValue = Integer.valueOf(strValue.toString());
							} else if (fieldTypeFullName.indexOf("float") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = Float.valueOf(strValue.toString());
							} else if (fieldTypeFullName.indexOf("double") != -1) {
								strValue = Double.valueOf(strValue.toString());
							} else if (fieldTypeFullName.indexOf("byte") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = Byte.valueOf(strValue.toString());
							} else if (fieldTypeFullName.indexOf("short") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = Short.valueOf(strValue.toString());
							} else if (fieldTypeFullName.indexOf("long") != -1) {
								strValue = Long.valueOf(strValue.toString());
							} else if (fieldTypeFullName.indexOf("character") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = strValue.toString().toCharArray();
							} else if (fieldTypeFullName.indexOf("java.util.date") != -1) {
								strValue = DateUtils.parseDate(strValue.toString(), "yyyy-MM-dd HH:mm:ss.SSS");
							} else {
								strValue = strValue.toString() + "";
							}
						} // end 检测字段类型是否正确
							// System.out.println(strValue);
						setMethod.invoke(resultObject, strValue); // 调用setXXX方法
					} // end 检测字段类型与Key是否匹配
				} // end 检测字段是否存在 "."
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("mapping fail", e);
		}
		if (resultObject != null) {
			return (E) resultObject;
		}
		return null;
	}

}
