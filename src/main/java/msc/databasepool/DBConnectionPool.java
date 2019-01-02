package msc.databasepool;

import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;

/**
 * 通过c3p0实现的数据库连接池类
 */
public class DBConnectionPool extends BaseDBConnectionPool {
	public static DataSource dataSourceMasterDb;

	static {
		try {
			DataSource unpooledDataSource = DataSources.unpooledDataSource(Config.DBURL_MASTER_DB, Config.DBNAME_MASTER_DB, Config.DBPASS_MASTER_DB);
			Configuration c3p0Config = new Configuration("c3p0.properties");
			dataSourceMasterDb = DataSources.pooledDataSource(unpooledDataSource, c3p0Config.getProperties());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("load DataSource fail", e);
		}
	}

}
