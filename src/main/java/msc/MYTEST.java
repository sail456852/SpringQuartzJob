package msc;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/27<br/>
 * Time: 10:47<br/>
 * To change this template use File | Settings | File Templates.
 */
public class MYTEST {
    @Test
    public void testSubString() {
        String f_merchant_id = "123456789";
        String F_merchant_id = "123456789";
//        int reverseIndex2 = f_merchant_id.length() - 2;
//        String tableName = "lepos" + "t_merchant_fee_rate_"+ f_merchant_id.substring(reverseIndex2);
//        String dbName = "lepos"  + f_merchant_id.substring(reverseIndex2 - 1, reverseIndex2);
//
//        System.err.println("tableName = " + tableName);
//        System.err.println("dbName = " + dbName);

        int reverseIndex2 = F_merchant_id.length() - 2;
        String table_no = "t_order_by_merchant_" + F_merchant_id.substring(reverseIndex2);
        String dbt  = "lepos"  + F_merchant_id.substring(reverseIndex2 - 1, reverseIndex2) + "." + table_no;
        System.err.println("table_no = " + table_no + " dbt: " + dbt);
    }
    
    @Test
    public void getCurrentClassName() {
        // target: output MYTEST
        String name = this.getClass().getSimpleName();
        System.err.println("name = " + name);
    }

}
