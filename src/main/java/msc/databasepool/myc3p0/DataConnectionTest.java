package msc.databasepool.myc3p0;

import com.mchange.v2.c3p0.DataSources;
import msc.databasepool.Config;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/4<br/>
 * Time: 9:35<br/>
 * To change this template use File | Settings | File Templates.
 */
public class DataConnectionTest {


    /**
     * @author: eugene @date: 2019/1/4
     * succeed in connecting office database
     * sample output
     * 乐刷官网
     * 乐刷官网
     * 测试意向服务商
     * APP
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void simpleJDBCconnection() throws SQLException, ClassNotFoundException {
        String URL = "jdbc:mysql://192.168.33.107:3306/lepos_business?useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull";
        String USER = "root";
        String PASSWORD = "1234";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from t_lepos_ad limit 10");
        while (rs.next()) {
            System.out.println(rs.getString("F_name") + " ");
        }
        rs.close();
        st.close();
        conn.close();
    }


    public static DataSource dataSourceReportSlaveDb;

    /**
     * @author: eugene @date: 2019/1/4
     * succeed connecting and queried database
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        getDBConnection();

        Connection conn = dataSourceReportSlaveDb.getConnection();

        PreparedStatement preparedStatement = conn.prepareStatement("select * from t_ditui_account limit 10");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String f_account_id = resultSet.getString("F_account_id");
            System.err.println("f_account_id = " + f_account_id);
        }
    }


    private static void getDBConnection() throws SQLException {
        Properties properties = new Properties();
        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("myc3p0.properties");
            properties.load(is);
        } catch (FileNotFoundException ex) {
            System.err.println("ex = " + ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("ex = " + ex);
            ex.printStackTrace();
        }

        // this DBURL_REPORT_SLAVE_DB takes no effect, and
        DataSource unpooledDataSource17 = DataSources.unpooledDataSource(Config.DBURL_REPORT_SLAVE_DB, Config.DBNAME_REPORT_SLAVE_DB, Config.DBPASS_REPORT_SLAVE_DB);
        dataSourceReportSlaveDb = DataSources.pooledDataSource(unpooledDataSource17, properties);
        System.err.println("DataConnectionTest.main");
    }

}
