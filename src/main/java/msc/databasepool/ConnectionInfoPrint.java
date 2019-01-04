package msc.databasepool;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/2<br/>
 * Time: 14:28<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionInfoPrint {

    protected static BaseDaoSlave businessSlave = new BaseDaoSlave(DBConnectionPool.dataSourceReportSlaveDb);

    /**
     * failed to execute enc method cause
     * @param args
     */
    public static void main(String[] args) {
        System.err.println("ConnectionInfoPrint.main");
        List<Map<String, Object>> maps = businessSlave.executeQuery("select * from t_ditui_account limit 10");
        for (Map<String, Object> map : maps) {
            String f_name = (String)map.get("F_name");
            System.err.println("f_name = " + f_name);
        }
    }
}
