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
    public static DataSource dataSourceBusinessSlaveDb;
    public static DataSource dataSourceBusinessDb;

    static {
		try {
			DataSource unpooledDataSource = DataSources.unpooledDataSource(Config.DBURL_MASTER_DB, Config.DBNAME_MASTER_DB, Config.DBPASS_MASTER_DB);
			Configuration c3p0Config = new Configuration("myc3p0.properties");
			dataSourceMasterDb = DataSources.pooledDataSource(unpooledDataSource, c3p0Config.getProperties());

            DataSource unpooledDataSource2 = DataSources.unpooledDataSource(Config.DBURL_SLAVE_DB, Config.DBNAME_SLAVE_DB, Config.DBPASS_SLAVE_DB);
            dataSourceSlaveDb = DataSources.pooledDataSource(unpooledDataSource2, c3p0Config.getProperties());

            DataSource unpooledDataSource17 = DataSources.unpooledDataSource(Config.DBURL_REPORT_SLAVE_DB, Config.DBNAME_REPORT_SLAVE_DB, Config.DBPASS_REPORT_SLAVE_DB);
            dataSourceReportSlaveDb = DataSources.pooledDataSource(unpooledDataSource17, c3p0Config.getProperties());

            DataSource unpooledDataSource55 = DataSources.unpooledDataSource(Config.DBURL_BUSINESS_SLAVE_DB, Config.DBNAME_BUSINESS_SLAVE_DB, Config.DBPASS_BUSINESS_SLAVE_DB);
            dataSourceBusinessSlaveDb = DataSources.pooledDataSource(unpooledDataSource55, c3p0Config.getProperties());

            DataSource unpooledDataSource5 = DataSources.unpooledDataSource(Config.DBURL_BUSINESS_DB, Config.DBNAME_BUSINESS_DB, Config.DBPASS_BUSINESS_DB);
            dataSourceBusinessDb = DataSources.pooledDataSource(unpooledDataSource5, c3p0Config.getProperties());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("load DataSource fail", e);
		}
	}

}
