package msc.databasepool;

import javax.sql.DataSource;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * @date ：2012-2-22上午11:49:11
 * @function: BaseDao工具类
 */

public class BaseDaoUtil {
	public static String firstLetterToLower(String str) {
		if (str == null || str.length() == 0)
			return str;
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	public static String firstLetterToUpper(String str) {
		if (str == null || str.length() == 0)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * 字符串规范化
	 * 
	 * @param str
	 * @return
	 */
	public static String getStrStandardization(String str) {
		if (!"".equals(str) && null != str) {
			String[] strArr = str.split("_");
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < strArr.length; i++) {
				if (i == 0) {
					sb.append(strArr[0].toLowerCase());
				} else {
					sb.append(firstLetterToUpper(strArr[i]));
				}
			}
			return sb.toString();
		}
		return null;
	}

	public static List<String> executeQueryTable(DataSource dataSource, String tableName) {
		String sql = "select * from " + tableName + " limit 0,1";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery(sql);
			metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String columnName = metaData.getColumnName(i);
				list.add(columnName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}

	public static void doPrintClassInserSql(DataSource dataSource, String tableName, Class clazz) {
		List<String> list = executeQueryTable(dataSource, tableName);

		Field[] fields = clazz.getDeclaredFields();
		int fieldsCount = fields.length;

		System.out.println("public static String getInsertSql(");
		String className = clazz.toString().substring(6);
		String classParameterName = className.substring(className.lastIndexOf(".") + 1);
		System.out.print(className + " ");
		System.out.print(classParameterName.toLowerCase() + ",");
		System.out.println("Object...objects){");
		System.out.println("StringBuffer sb = new StringBuffer();");
		System.out.println("sb.append(\" insert into " + tableName + "(\");");
		for (String str : list) {
			System.out.println("sb.append(\"" + str + ",\");");
		}
		System.out.println("sb = sb.delete(sb.length() - 1, sb.length()) ; ");
		System.out.println("sb.append(\" ) values( \");");

		for (String str : list) {
			String strTableKey = str;
			for (int i = 0; i < fieldsCount; i++) {
				Field field = fields[i];
				Class<?> type = field.getType();
				String name = field.getName();
				// System.out.println(name);
				int lastIndex = -1;
				if ((lastIndex = type.getName().lastIndexOf(".")) != -1) {
					String firstLetter = name.substring(0, 1).toUpperCase(); // 获得和字段对应的getXXX()方法的名字：get+属性名称的第一个字母并转成大小+属性名去掉第一个字母
					String getMethodName = "get" + firstLetter + name.substring(1); // 获得和属性对应的getXXX()方法的名字
					try {
						String argsType = type.getName().substring(lastIndex + 1);
						// System.out.println(argsType);
						if (name.toLowerCase().equals(getStrStandardization(strTableKey).toLowerCase())) {
							String waitMethod = classParameterName.toLowerCase() + "." + getMethodName + "()";

							if (argsType.toLowerCase().indexOf("string") != -1) {
								// sb.append("'+ \"+"+waitMethod+"+\"',");

								System.out.println("sb.append(\" '\"+ (null == " + waitMethod + " ? \"\" : " + waitMethod + " ) +\"',\");");
							} else {
								System.out.println("sb.append(\" \"+ " + waitMethod + " +\",\");");
							}

							// System.out.println("}");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}// end if
			}// end for
		}// end for
		System.out.println("sb = sb.delete(sb.length() - 1, sb.length()) ; ");
		System.out.print("sb.append(\" ) \"); ");

		System.out.println("if(objects != null && objects.length > 0){");
		System.out.println("for(int i = 0;i<objects.length ; i++){");
		System.out.println("sb.append(objects[i]);");
		System.out.println("}}");
		System.out.println("return sb.toString();");
		System.out.println("}");
	}

	@SuppressWarnings("unchecked")
	public static void doPrintClassCondition(DataSource dataSource, String tableName, Class clazz) {

		List<String> list = executeQueryTable(dataSource, tableName);

		Field[] fields = clazz.getDeclaredFields();
		int fieldsCount = fields.length;

		System.out.println("public static String getCondition(");
		String className = clazz.toString().substring(6);
		String classParameterName = StringUtil.firstLetterToLower(className.substring(className.lastIndexOf(".") + 1));
		System.out.print(className + " ");
		System.out.print(classParameterName + ",");
		System.out.println("Object...objects){");
		System.out.println("StringBuffer sb = new StringBuffer();");

		for (String str : list) {
			String strTableKey = str;
			for (int i = 0; i < fieldsCount; i++) {
				Field field = fields[i];
				Class type = field.getType();
				String name = field.getName();
				// System.out.println(name);
				int lastIndex = -1;
				if ((lastIndex = type.getName().lastIndexOf(".")) != -1) {
					String firstLetter = name.substring(0, 1).toUpperCase(); // 获得和字段对应的getXXX()方法的名字：get+属性名称的第一个字母并转成大小+属性名去掉第一个字母
					String getMethodName = "get" + firstLetter + name.substring(1); // 获得和属性对应的getXXX()方法的名字
					// System.out.println(setMethodName);
					try {
						String argsType = type.getName().substring(lastIndex + 1);
						// System.out.println(argsType);
						if (name.toLowerCase().equals(getStrStandardization(strTableKey).toLowerCase())) {
							String waitMethod = classParameterName + "." + getMethodName + "()";
							if (argsType.toLowerCase().indexOf("string") != -1) {
								System.out.println(" if(" + waitMethod + " != null && !\"\".equals(" + waitMethod + " )){");
								System.out.println("	sb.append(\" and " + strTableKey + " = '\"+" + waitMethod + "+\"'\" " + ");");
							} else {
								System.out.println(" if(" + waitMethod + " != null && " + waitMethod + " != 0){");
								System.out.println("	sb.append(\" and " + strTableKey + " = \"+" + waitMethod + " );");
							}
							System.out.println("}");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}// end if
			}// end for
		}// end for

		System.out.println("return sb.toString();");
		System.out.println("}");
	}

	@SuppressWarnings("unchecked")
	public static void printCreareMethodByFields(DataSource dataSource, String tableName, Class clazz) {
		List<String> list = executeQueryTable(dataSource, tableName);

		Field[] fields = clazz.getDeclaredFields();
		System.out.println("public String getCondition(");
		int fieldsCount = fields.length;
		for (int i = 0; i < fieldsCount; i++) {
			Field field = fields[i];
			Class type = field.getType();
			String name = field.getName();
			int lastIndex = -1;
			if ((lastIndex = type.getName().lastIndexOf(".")) != -1) {
				String argsType = type.getName().substring(lastIndex + 1);
				/*
				 * if(i != fieldsCount - 1){ System.out.println(" "+argsType + "
				 * "+name+","); }else{ System.out.println(" "+argsType + "
				 * "+name); }
				 */
				System.out.println("  " + argsType + " " + name + ",");
			}
		}
		System.out.println("Object... obj){");
		// System.out.println("){");
		System.out.println("StringBuffer sb = new StringBuffer();");

		for (String str : list) {
			String strTableKey = str;
			for (int i = 0; i < fieldsCount; i++) {
				Field field = fields[i];
				Class type = field.getType();
				String name = field.getName();
				int lastIndex = -1;
				if ((lastIndex = type.getName().lastIndexOf(".")) != -1) {
					String argsType = type.getName().substring(lastIndex + 1);
					// System.out.println(argsType);
					if (name.toLowerCase().equals(getStrStandardization(strTableKey).toLowerCase())) {
						System.out.println(" if(" + name + " != null && !\"\".equals(" + name + ")){");
						if (argsType.toLowerCase().indexOf("string") != -1) {
							System.out.println("	sb.append(\" and " + strTableKey + " = '\"+" + name + "+\"'\" " + ");");
						} else {
							System.out.println("	sb.append(\" and " + strTableKey + " = \"+" + name + " );");
						}
						System.out.println("}");
					}
				}// end if
			}// end for
		}// end for

		System.out.println("if(obj != null && obj.length > 0){");
		System.out.println("for(int i = 0;i<obj.length ; i++){");
		System.out.println("sb.append(obj[i]);");
		System.out.println("}}");
		System.out.println("return sb.toString();");
		System.out.println("}");
	}

	@SuppressWarnings("unchecked")
	public static Object mapToBean(Map map, Class clazz) {
		Map keyMap = new Hashtable();
		Set<?> set = map.keySet();
		Iterator<?> iterator = set.iterator();
		while (iterator.hasNext()) {
			Object strKey = iterator.next(); // 得到key
			Object strValue = map.get(strKey); // 得到value
			strValue = strValue == null ? "" : strValue;
			strKey = getStrStandardization(strKey.toString()).toLowerCase();
			// System.out.println(strKey+":"+strValue);
			keyMap.put(strKey, strValue);
		}

		try {
			// System.out.println("Class :" + classType.getName());
			Field[] fields = clazz.getDeclaredFields(); // 获得对象的所有字段

			int fieldsCount = fields.length;
			for (int i = 0; i < fieldsCount; i++) { // 循环每个字段
				Field field = fields[i]; // 当前字段
				String fieldName = field.getName(); // 字段名称
				Class fileType = field.getType(); // 字段类型
				if (fileType.toString().lastIndexOf(".") != -1) {
					// System.out.println("fieldName:"+fieldName);
					String firstLetter = fieldName.substring(0, 1).toUpperCase(); // 获得和字段对应的getXXX()方法的名字：get+属性名称的第一个字母并转成大小+属性名去掉第一个字母
					String setMethodName = "set" + firstLetter + fieldName.substring(1); // 获得和属性对应的setXXX()方法的名字
					Method setMethod = clazz.getMethod(setMethodName, field.getType()); // 获得和属性对应的setXXX()方法，传入参数为参数的类型
					// System.out.println(fieldName.toLowerCase());
					if (keyMap.containsKey((fieldName = fieldName.toLowerCase()))) {
						// System.out.println(fieldName);
						Object strValue = keyMap.get(fieldName);

						if (fileType.toString().toLowerCase().indexOf("string") != -1) {
							strValue = strValue.equals(false) ? "0" : strValue.equals(true) ? "1" : strValue.toString();
						} else {
							strValue = strValue.equals(false) ? 0 : strValue.equals(true) ? 1 : "".equals(strValue.toString()) || null == strValue.toString() ? 0 : strValue;

							if (fileType.toString().toLowerCase().indexOf("integer") != -1) {
								strValue = Integer.valueOf(strValue.toString());
							} else if (fileType.toString().toLowerCase().indexOf("float") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = Float.valueOf(strValue.toString());
							} else if (fileType.toString().toLowerCase().indexOf("byte") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = Byte.valueOf(strValue.toString());
							} else if (fileType.toString().toLowerCase().indexOf("short") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = Short.valueOf(strValue.toString());
							} else if (fileType.toString().toLowerCase().indexOf("long") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = Long.valueOf(strValue.toString());
							} else if (fileType.toString().toLowerCase().indexOf("character") != -1) {
								strValue = Integer.valueOf(strValue.toString());
								strValue = strValue.toString().toCharArray();
							} else {
								strValue = strValue.toString() + "";
							}
						}// end 检测字段类型是否正确
						// System.out.println(strValue);
						setMethod.invoke(clazz.newInstance(), strValue); // 调用setXXX方法
					}// end 检测字段类型与Key是否匹配
				}// end 检测字段是否存在 "."
			}// end for
		} catch (Exception e) {
			throw new RuntimeException("mapping fail", e);
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

//	public static void printModelProperty(DataSource dataSource, String tableName) {
//		BaseDao baseDao = new BaseDao(dataSource);
//		Connection conn = baseDao.getConnection();
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		ResultSetMetaData metaData = null;
//		try {
//			pstm = conn.prepareStatement("select * from " + tableName + " limit 0,1");
//			rs = pstm.executeQuery();
//			metaData = rs.getMetaData();
//			int count = metaData.getColumnCount(); // 结果集的列数
//
//			for (int i = 1; i <= count; i++) {
//				String columnName = metaData.getColumnName(i);
//				String columnType = metaData.getColumnTypeName(i);
//
//				String fieldName = BaseDaoUtil.getStrStandardization(columnName);
//				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
//
//				String fieldType = StringUtil.replace(columnType, "UNSIGNED", "");
//				fieldType = fieldType.trim();
//				if ("TINYINT".equalsIgnoreCase(fieldType) || "INTEGER".equalsIgnoreCase(fieldType)) {
//					fieldType = "Integer";
//				} else if ("BIGINT".equalsIgnoreCase(fieldType)) {
//					fieldType = "Long";
//				} else {
//					fieldType = "String";
//				}
//				System.out.println("	private " + fieldType + " " + fieldName + ";");// +
//				// "\t"
//				// +
//				// metaData.getColumnTypeName(i)
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		} finally {
//			baseDao.close(conn, pstm, rs);
//		}
//	}

//	public static void genModelSourceFile(DataSource dataSource, String packageName, String tableName) throws Exception {
//		String className = tableName;
//		if (tableName.indexOf(".") != -1) {
//			className = className.substring(className.indexOf(".") + 1);
//		}
//		if (className.startsWith("t_") || className.startsWith("T_")) {
//			className = className.substring(2);
//		}
//		int pos = className.lastIndexOf("_");
//		if (pos != -1) {
//			String str = className.substring(pos + 1);
//			try {
//				Integer.parseInt(str);
//				className = className.substring(0, pos);
//			} catch (Exception e) {
//			}
//		}
//		className = firstLetterToUpper(getStrStandardization(className)) + "Model";
//
//		BaseDao baseDao = new BaseDao(dataSource);
//		Connection conn = baseDao.getConnection();
//		PreparedStatement pstm = null;
//		ResultSet rs = null;
//		ResultSetMetaData metaData = null;
//		List<String> list = new ArrayList<String>();
//		try {
//			pstm = conn.prepareStatement("select * from " + tableName + " limit 0,1");
//			rs = pstm.executeQuery();
//			metaData = rs.getMetaData();
//			int count = metaData.getColumnCount(); // 结果集的列数
//			list.add("package " + packageName + ";");
//			list.add("");
//			list.add("import java.io.Serializable;");
//			list.add("");
//			list.add("public class " + className + " implements Serializable {");
//			list.add("	private static final long serialVersionUID = -1L;");
//			for (int i = 1; i <= count; i++) {
//				String columnName = metaData.getColumnName(i);
//				String columnType = metaData.getColumnTypeName(i);
//
//				String fieldName = BaseDaoUtil.getStrStandardization(columnName);
//				fieldName = firstLetterToUpper(fieldName);
//
//				String fieldType = StringUtil.replace(columnType, "UNSIGNED", "");
//				fieldType = fieldType.trim();
//				if ("TINYINT".equalsIgnoreCase(fieldType) || "INTEGER".equalsIgnoreCase(fieldType)) {
//					fieldType = "Integer";
//				} else if ("BIGINT".equalsIgnoreCase(fieldType)) {
//					fieldType = "Long";
//				} else {
//					fieldType = "String";
//				}
//				list.add("	private " + fieldType + " " + fieldName + ";");
//			}
//			list.add("");
//			for (int i = 1; i <= count; i++) {
//				String columnName = metaData.getColumnName(i);
//				String columnType = metaData.getColumnTypeName(i);
//
//				String fieldName = BaseDaoUtil.getStrStandardization(columnName);
//				fieldName = firstLetterToUpper(fieldName);
//
//				String fieldType = StringUtil.replace(columnType, "UNSIGNED", "");
//				fieldType = fieldType.trim();
//				if ("TINYINT".equalsIgnoreCase(fieldType) || "INTEGER".equalsIgnoreCase(fieldType)) {
//					fieldType = "Integer";
//				} else if ("BIGINT".equalsIgnoreCase(fieldType)) {
//					fieldType = "Long";
//				} else {
//					fieldType = "String";
//				}
//
//				list.add("	public " + fieldType + " get" + fieldName + "() {");
//				list.add("		return " + fieldName + ";");
//				list.add("	}");
//				list.add("");
//
//				list.add("	public void set" + fieldName + "(" + fieldType + " " + firstLetterToLower(fieldName) + ") {");
//				list.add("		" + fieldName + " = " + firstLetterToLower(fieldName) + ";");
//				list.add("	}");
//				list.add("");
//			}
//
//			list.add("	public String toString() {");
//			String str = "";
//			for (int i = 1; i <= count; i++) {
//				String columnName = metaData.getColumnName(i);
//
//				String fieldName = BaseDaoUtil.getStrStandardization(columnName);
//				fieldName = firstLetterToUpper(fieldName);
//
//				if (!"".equals(str)) {
//					str += " + \",\" + ";
//				}
//				str += "\"" + fieldName + ":\" + " + fieldName;
//			}
//			list.add("		return " + str + ";");
//			list.add("	}");
//
//			list.add("}");
//
//			StringBuffer sb = new StringBuffer();
//			for (String line : list) {
//				sb.append(line).append("\n");
//			}
//			File dir = new File(BaseDaoUtil.class.getResource("/").getPath());
//			dir = new File(dir.getParentFile().getParentFile().getParentFile(), "src");
//			String dirName = StringUtil.replace(packageName, ".", "/");
//			dir = new File(dir, dirName);
//			if (!dir.exists())
//				dir.mkdirs();
//			File file = new File(dir, className + ".java");
//			if (file.exists()) {
//				System.out.println(sb.toString());
//				return;
//			}
//			file.createNewFile();
//			FileUtil.addToFile(file, sb.toString());
//			System.out.println("已生成到文件" + file.getPath());
//		} finally {
//			baseDao.close(conn, pstm, rs);
//		}
//
//	}

//	public static void main(String[] args) throws Exception {
//		genModelSourceFile(DBConnectionPool.dataSourceMasterDb, "com.yeahka.ykscript.models", "lepos_business.t_transaction_operation_bypos");
////		doPrintClassCondition(DBConnectionPool.dataSourceMasterDb, "lepos.t_proxy_apply", ProxyApplyModel.class);
//
//	}

}
