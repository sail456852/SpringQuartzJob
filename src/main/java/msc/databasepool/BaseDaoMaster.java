package msc.databasepool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class BaseDaoMaster {
	static final Log log = LogFactory.getLog(BaseDaoMaster.class);

	private DataSource dataSource;

	public BaseDaoMaster(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public BaseDaoMaster() {
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("Get Connection Fail", e);
		}
		return conn;
	}

	public Connection getMasterDbConnection() {
		Connection conn = null;
		try {
			conn = DBConnectionPool.dataSourceMasterDb.getConnection();
		} catch (Exception e) {
			throw new RuntimeException("Get Connection Fail", e);
		}
		return conn;
	}

	// 查询多行，结果集为List<Map<String, Object>>
	public List<Map<String, Object>> executeQuery(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {

			pstm = conn.prepareStatement(reSql(sql,objects,0));

			rs = pstm.executeQuery();
			metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> resultMap = new HashMap<String, Object>(
						count);
				for (int i = 1; i <= count; i++) {
					String columnName = metaData.getColumnLabel(i);

					String columnType = metaData.getColumnTypeName(i);
					String fieldType = StringUtil.replace(columnType,
							"UNSIGNED", "");
					fieldType = fieldType.trim();
					if ("TINYINT".equalsIgnoreCase(fieldType)
							|| "INTEGER".equalsIgnoreCase(fieldType)) {
						fieldType = "INTEGER";
						resultMap.put(columnName, new Integer(rs
								.getInt(columnName)));
					} else if ("BIGINT".equalsIgnoreCase(fieldType) || "DECIMAL".equalsIgnoreCase(fieldType)) {
						fieldType = "Long";
						resultMap.put(columnName, new Long(rs
								.getLong(columnName)));
					} else if ("TIMESTAMP".equalsIgnoreCase(fieldType)) {
						fieldType = "String";
						String value = rs.getString(columnName);
						if (value != null && value.endsWith(".0")) value = value.substring(0, value.length()-2);
						resultMap.put(columnName, value);
					} else {
						fieldType = "String";
						resultMap.put(columnName, rs.getString(columnName));
					}

				}
				mapList.add(resultMap);
			}
            DbSecurityUtil.dec(mapList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstm, rs);
		}
		return mapList;
	}

	public int executeUpdateId(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement psmt = null;
		int result = 0;
		try {
			conn.setAutoCommit(true);

			reSql(sql,objects,1);
			psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			
			for (int i = 0; i < objects.length; i++) {
				psmt.setObject(i+1, objects[i]);
			}

			psmt.executeUpdate();
			ResultSet rs = psmt.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, psmt, null);
		}
		return result;
	}
	
	public Map<Object, Object> executeQueryKeyAndValue(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {

			pstm = conn.prepareStatement(reSql(sql,objects,0));

			rs = pstm.executeQuery();
			metaData = rs.getMetaData();
			while (rs.next()) {
				Object key = null, val = null;
				if (1==1) {
					String columnName = metaData.getColumnLabel(1);
					String columnType = metaData.getColumnTypeName(1);
					String fieldType = StringUtil.replace(columnType,
							"UNSIGNED", "");
					fieldType = fieldType.trim();
					if ("TINYINT".equalsIgnoreCase(fieldType)
							|| "INTEGER".equalsIgnoreCase(fieldType)) {
						fieldType = "INTEGER";
						key = new Integer(rs.getInt(columnName));
					} else if ("BIGINT".equalsIgnoreCase(fieldType) || "DECIMAL".equalsIgnoreCase(fieldType)) {
						fieldType = "Long";
						key = new Long(rs.getLong(columnName));
					} else if ("TIMESTAMP".equalsIgnoreCase(fieldType)) {
						fieldType = "String";
						String value = rs.getString(columnName);
						if (value != null && value.endsWith(".0")) value = value.substring(0, value.length()-2);
						key = value;
					} else {
						fieldType = "String";
						key = rs.getString(columnName);
					}
				}
				
				if (1==1) {
					String columnName = metaData.getColumnLabel(1);
					String columnType = metaData.getColumnTypeName(1);
					String fieldType = StringUtil.replace(columnType,
							"UNSIGNED", "");
					fieldType = fieldType.trim();
					if ("TINYINT".equalsIgnoreCase(fieldType)
							|| "INTEGER".equalsIgnoreCase(fieldType)) {
						fieldType = "INTEGER";
						val = new Integer(rs.getInt(columnName));
					} else if ("BIGINT".equalsIgnoreCase(fieldType) || "DECIMAL".equalsIgnoreCase(fieldType)) {
						fieldType = "Long";
						val = new Long(rs.getLong(columnName));
					} else if ("TIMESTAMP".equalsIgnoreCase(fieldType)) {
						fieldType = "String";
						String value = rs.getString(columnName);
						if (value != null && value.endsWith(".0")) value = value.substring(0, value.length()-2);
						val = value;
					} else {
						fieldType = "String";
						val = rs.getString(columnName);
					}
				}
				map.put(key, val);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstm, rs);
		}
		return map;
	}
	
	// 查询单行，结果集为Map<String, Object>
	public Map<String, Object> executeQueryMap(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		Map<String, Object> resultMap = null;
		try {

			pstm = conn.prepareStatement(reSql(sql,objects,0));

			rs = pstm.executeQuery();
			metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			resultMap = new HashMap<String, Object>(count);
			if (rs.next()) {
				for (int i = 1; i <= count; i++) {
					String columnName = metaData.getColumnLabel(i);
					String columnType = metaData.getColumnTypeName(i);
					String fieldType = StringUtil.replace(columnType,
							"UNSIGNED", "");
					fieldType = fieldType.trim();
					if ("TINYINT".equalsIgnoreCase(fieldType)
							|| "INTEGER".equalsIgnoreCase(fieldType)) {
						fieldType = "INTEGER";
						resultMap.put(columnName, new Integer(rs
								.getInt(columnName)));
					} else if ("BIGINT".equalsIgnoreCase(fieldType) || "DECIMAL".equalsIgnoreCase(fieldType)) {
						fieldType = "Long";
						resultMap.put(columnName, new Long(rs
								.getLong(columnName)));
					} else if ("TIMESTAMP".equalsIgnoreCase(fieldType)) {
						fieldType = "String";
						String value = rs.getString(columnName);
						if (value != null && value.endsWith(".0")) value = value.substring(0, value.length()-2);
						resultMap.put(columnName, value);
					} else {
						fieldType = "String";
						resultMap.put(columnName, rs.getString(columnName));
					}
				}
			}
            DbSecurityUtil.dec(resultMap);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstm, rs);
		}
		return resultMap;
	}

	public Map<String, Object> executeQueryMap(String sql,
			PageResult pageResult, Object... objects) {
		Map<String, Object> map = executeQueryMap(sql, objects);
		if (pageResult == null)
			return map;
		pageResult.setTotal((int) ((Long) map.get("DATA_SUM")).longValue());
		map.put("pageCount", pageResult.getPageCount());
		return map;
	}

	// 查询多行，结果集为Model的list集合
	@SuppressWarnings("unchecked")
	public void executeQueryModelList(String sql, List list,
			Class clazz, Object... objects) {
		executeQueryModelList(sql, list, clazz,null, objects);
	}

	// 查询多行，结果集为Model的list集合
	@SuppressWarnings("unchecked")
	public void executeQueryModelList(String sql, List list,
			Class clazz, PageResult pageResult, Object... objects) {
        if (pageResult != null) {
            sql += " limit " + pageResult.getStart() + ","
                    + pageResult.getPageSize();
        }
        List<Map<String, Object>> lists = executeQuery(sql, objects);

        for (Map<String, Object> resultMap : lists) {
            list.add(MapToBean.conversion(resultMap, clazz));
        }
	}

	// 查询单行，结果集为Model
	@SuppressWarnings("unchecked")
	public <E> E executeQueryModel(String sql, Class clazz, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ResultSetMetaData metaData = null;
		Map<String, Object> resultMap = null;
		try {

			pstm = conn.prepareStatement(reSql(sql,objects,0));

			rs = pstm.executeQuery();
			metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			resultMap = new HashMap<String, Object>(count);
			if (rs.next()) {
				for (int i = 1; i <= count; i++) {
					String columnName = metaData.getColumnLabel(i);
					String columnType = metaData.getColumnTypeName(i);
					String fieldType = StringUtil.replace(columnType,
							"UNSIGNED", "");
					fieldType = fieldType.trim();
					if ("TINYINT".equalsIgnoreCase(fieldType)
							|| "INTEGER".equalsIgnoreCase(fieldType)) {
						fieldType = "INTEGER";
						resultMap.put(columnName, new Integer(rs
								.getInt(columnName)));
					} else if ("BIGINT".equalsIgnoreCase(fieldType) || "DECIMAL".equalsIgnoreCase(fieldType)) {
						fieldType = "Long";
						resultMap.put(columnName, new Long(rs
								.getLong(columnName)));
					} else if ("TIMESTAMP".equalsIgnoreCase(fieldType)) {
						fieldType = "String";
						String value = rs.getString(columnName);
						if (value != null && value.endsWith(".0")) value = value.substring(0, value.length()-2);
						resultMap.put(columnName, value);
					} else { 
						fieldType = "String";
						resultMap.put(columnName, rs.getString(columnName));
					}
				}
			}
			if (resultMap.size() == 0) {
				return null;
			} else {
                DbSecurityUtil.dec(resultMap);
				return (E)MapToBean.conversion(resultMap, clazz);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, pstm, rs);
		}
	}

	// 查询一个字符串字段值
	public String executeQueryString(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        Map<String, Object> resultMap = null;
        try {
            reSql(sql, objects, 0);
            psmt = conn.prepareStatement(sql);

            for (int i = 0; i < objects.length; i++) {
                psmt.setObject(i+1, objects[i]);
            }

            rs = psmt.executeQuery();
            metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            resultMap = new HashMap<String, Object>(count);
            if (rs.next()) {
                String columnName = metaData.getColumnLabel(1);
                resultMap.put(columnName, rs.getString(1));
                DbSecurityUtil.dec(resultMap);
                return (String)resultMap.get(columnName);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        } finally {
            close(conn, psmt, null);
        }
        return null;
	}

	// 查询一个字符串List
	public List<String> executeQueryStringList(String sql, Object... objects) {
		List<String> list = new ArrayList<String>();
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        ResultSetMetaData metaData = null;
        try {
            reSql(sql, objects, 0);
            psmt = conn.prepareStatement(sql);

            for (int i = 0; i < objects.length; i++) {
                psmt.setObject(i+1, objects[i]);
            }

            List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
            rs = psmt.executeQuery();
            metaData = rs.getMetaData();
            int count = metaData.getColumnCount();
            String columnName = metaData.getColumnLabel(1);
            while (rs.next()) {
                Map<String, Object> resultMap = new HashMap<String, Object>(count);
                resultMap.put(columnName, rs.getString(1));
                mapList.add(resultMap);
            }
            DbSecurityUtil.dec(mapList);
            for (Map<String, Object> tmpMap : mapList) {
                list.add((String)tmpMap.get(columnName));
            }
            return list;
        } catch (SQLException e) {
            log.error(e);
            throw new RuntimeException(e);
        } finally {
            close(conn, psmt, null);
        }
	}	
	
	// 查询一个整型字段值
	public Integer executeQueryInteger(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement psmt = null;
		ResultSet re = null;
		try {

			psmt = conn.prepareStatement(reSql(sql,objects,0));

			re = psmt.executeQuery();
			if (re.next()) {
				return re.getInt(1);
			}
		} catch (SQLException e) {
			log.error(e);
			throw new RuntimeException(e);
		} finally {
			close(conn, psmt, null);
		}
		return 0;
	}
	
	public List<Integer> queryIntegerList(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement psmt = null;
		ResultSet re = null;
		List<Integer> list = new ArrayList<Integer>();
		try {
			conn.setAutoCommit(true);

			psmt = conn.prepareStatement(reSql(sql,objects,0));

			re = psmt.executeQuery();
			while (re.next()) {
				list.add(re.getInt(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, psmt, null);
		}
		return list;
	}
	
	public int executeUpdate(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement psmt = null;
		int result;
		try {
			conn.setAutoCommit(true);

			//psmt = conn.prepareStatement(reSql(sql,objects,1));
			reSql2(sql,objects,1);
			psmt = conn.prepareStatement(sql);
			
			
			for (int i = 0; i < objects.length; i++) {
				psmt.setObject(i+1, objects[i]);
			}

			result = psmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, psmt, null);
		}
		return result;
	}

	public boolean execute(String sql, Object... objects) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		PreparedStatement psmt = null;
		boolean flag;
		try {
			conn.setAutoCommit(true);

//			pstm = conn.prepareStatement(reSql(sql,objects,0));
			reSql2(sql, objects, 0);
			psmt = conn.prepareStatement(sql);
			
			for (int i = 0; i < objects.length; i++) {
				psmt.setObject(i+1, objects[i]);
			}

			flag = psmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(conn, psmt, null);
		}
		return flag;
	}
	
	public void executeBatch(String sql, List<Object[]> params) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		log.info("executeNoAutoCommit start");
		PreparedStatement psmt= null;
		try {
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(sql); 
			for (int i = 0; i < params.size(); i++) {
				for (int j = 0; j < params.get(i).length; j++) {
					psmt.setObject(j+1, params.get(i)[j]);
				}
				psmt.addBatch();
			}
			psmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			log.info("executeNoAutoCommit success end ");
		} catch (SQLException e) {
			rollback(conn);
			log.error("executeNoAutoCommit 执行异常", e);
			throw new RuntimeException(e);
		} finally {
			close(conn, psmt, null);
		}
	}
	
	public void executeBatch(String[] sql, List<Object[]> params) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		log.info("executeNoAutoCommit start");
		PreparedStatement psmt= null;
		try {
			conn.setAutoCommit(false);

			for (int i = 0; i < sql.length; i++) {
				reSql(sql[i], params.get(i), 0);
				psmt = conn.prepareStatement(sql[i]); 
				for (int j = 0; j < params.get(i).length; j++) {
					psmt.setObject(j+1, params.get(i)[j]);
				}
				psmt.execute();
				log.info("executeNoAutoCommit for :"+sql[i]);
			}
			psmt.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			log.info("executeNoAutoCommit success end ");
		} catch (SQLException e) {
			rollback(conn);
			log.error("executeNoAutoCommit 执行异常", e);
			throw new RuntimeException(e);
		} finally {
			close(conn, psmt, null);
		}
	}

	public void executeUpdate(String[] sql, Object... params) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			for (int i = 0; i < sql.length; i++) {
				stmt.addBatch(sql[i]);
				log.info("EXECUTE_SQL_BINLOG:"+sql[i]);
			}
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			rollback(conn);
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, null);
		}
	}

	public void execute(String[] sql) {
		Connection conn = dataSource != null ? getConnection()
				: getMasterDbConnection();
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);

			stmt = conn.createStatement();
			for (int i = 0; i < sql.length; i++) {
				stmt.addBatch(sql[i]);
				log.debug("QUERY_SQL_BINLOG: " + sql[i]);
			}
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			rollback(conn);
			throw new RuntimeException(e);
		} finally {
			close(conn, stmt, null);
		}
	}

	public void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void close(Connection conn, Statement pstm, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (pstm != null) {
			try {
				pstm.close();
				pstm = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void close(Connection conn) {
		close(conn, null, null);
	}

	public void close(PreparedStatement psmt) {
		close(null, psmt, null);
	}

	public void close(ResultSet rs) {
		close(null, null, rs);
	}

	public static void executeUpdate(String sql, Connection conn)
			throws Exception {
		Statement stmt = null;
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		log.info("EXECUTE_SQL_BINLOG:" + sql);
		stmt.close();
	}

	/**
	 * 拼装sql语句
	 * 
	 * @param sql
	 * @param objects
	 * @param flag
	 *            0 查询 1 增、删、改
	 * @return
	 */
	public static String reSql(String sql, Object[] objects, int flag) {

		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				if (objects[i] == null) {
					sql = sql.replaceFirst("\\?", "''");
				} else if (objects[i] instanceof String) {
					String tmp = (String) objects[i];
					tmp = tmp.replace("$", "\\$");
					tmp = setWen(tmp);
					sql = sql.replaceFirst("\\?", "'" + tmp + "'");
				} else if (objects[i] instanceof Integer) {
					int tmp = ((Integer) objects[i]).intValue();
					sql = sql.replaceFirst("\\?", tmp + "");
				} else if (objects[i] instanceof Long) {
					long tmp = ((Long) objects[i]).longValue();
					sql = sql.replaceFirst("\\?", tmp + "");
				} else if (objects[i] instanceof Double) {
					double tmp = ((Double) objects[i]).doubleValue();
					sql = sql.replaceFirst("\\?", tmp + "");
				} else if (objects[i] instanceof Float) {
					float tmp = ((Float) objects[i]).floatValue();
					sql = sql.replaceFirst("\\?", tmp + "");
				} else if (objects[i] instanceof Date) {
					Date tmp = (Date) objects[i];
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					sql = sql.replaceFirst("\\?", "'" + sdf.format(tmp) + "'");
				} else {
					String tmp = objects[i].toString();
					tmp = setWen(tmp);
					sql = sql.replaceFirst("\\?", "'" + tmp + "'");
				}
			}
		}

		if (flag == 1) {
			log.info("EXECUTE_SQL_BINLOG:" + sql);
		} else {
			log.debug("QUERY_SQL_BINLOG:" + sql);
		}
		sql = getWen(sql);
		return sql;
	}

    //单独给跟新方法使用
    public static String reSql2(String sql, Object[] objects, int flag) {

        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] == null) {
                    sql = sql.replaceFirst("\\?", "''");
                } else if (objects[i] instanceof String) {
                    String tmp = (String) objects[i];
                    tmp = tmp.replace("$", "\\$");
                    tmp = setWen(tmp);
                    sql = sql.replaceFirst("\\?", "'" + tmp + "'");
                } else if (objects[i] instanceof Integer) {
                    int tmp = ((Integer) objects[i]).intValue();
                    sql = sql.replaceFirst("\\?", tmp + "");
                } else if (objects[i] instanceof Long) {
                    long tmp = ((Long) objects[i]).longValue();
                    sql = sql.replaceFirst("\\?", tmp + "");
                } else if (objects[i] instanceof Double) {
                    double tmp = ((Double) objects[i]).doubleValue();
                    sql = sql.replaceFirst("\\?", tmp + "");
                } else if (objects[i] instanceof Float) {
                    float tmp = ((Float) objects[i]).floatValue();
                    sql = sql.replaceFirst("\\?", tmp + "");
                } else if (objects[i] instanceof Date) {
                    Date tmp = (Date) objects[i];
                    SimpleDateFormat sdf = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    sql = sql.replaceFirst("\\?", "'" + sdf.format(tmp) + "'");
                } else {
                    String tmp = objects[i].toString();
                    tmp = setWen(tmp);
                    sql = sql.replaceFirst("\\?", "'" + tmp + "'");
                }
            }
        }

        if (flag == 1) {
            log.info("EXECUTE_SQL_BINLOG:" + sql);
        } else {
            log.debug("QUERY_SQL_BINLOG:" + sql);
        }
        sql = getWen(sql);
        return sql;
    }
	/**
	 * 替换value里面的?
     * @param str
     * @return
     */
	private static String setWen(String str){
		return str.replaceAll("\\?", "？");
	}
	
	/**
	 * 把替换的?替换回来
	 * @param sql
	 */
	private static String getWen(String sql){
		return sql.replaceAll("？", "?");
	}
}