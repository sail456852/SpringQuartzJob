package msc.databasepool;

import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;

/**
 * 通过c3p0实现的数据库连接池类
 */
public class DBConnectionPool extends BaseDBConnectionPool {
	public static DataSource dataSourceMasterDb;
    public static DataSource dataSourceSlaveDb;

    public static DataSource dataSourceReportSlaveDb;

    static {
		try {
			Configuration c3p0Config = new Configuration("myc3p0.properties");
            DataSource unpooledDataSource17 = DataSources.unpooledDataSource(Config.DBURL_REPORT_SLAVE_DB, Config.DBNAME_REPORT_SLAVE_DB, Config.DBPASS_REPORT_SLAVE_DB);
            dataSourceReportSlaveDb = DataSources.pooledDataSource(unpooledDataSource17, c3p0Config.getProperties());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("load DataSource fail", e);
		}
	}

}
