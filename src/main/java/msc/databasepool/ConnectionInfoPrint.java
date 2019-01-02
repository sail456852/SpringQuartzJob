package msc.databasepool;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/2<br/>
 * Time: 14:28<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionInfoPrint {

    protected static BaseDaoMaster businessMaster = new BaseDaoMaster(DBConnectionPool.dataSourceMasterDb);

    public static void main(String[] args) {
        System.err.println("ConnectionInfoPrint.main");
    }
}
