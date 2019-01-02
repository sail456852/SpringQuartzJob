package msc.databasepool;

import org.junit.Test;

import java.io.*;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/2<br/>
 * Time: 15:19<br/>
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesTest {
    /**
     * read it from class path
     * @param args
     * @throws IOException
     * https://stackoverflow.com/questions/12024672/how-to-load-property-file-from-classpath
     */
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertiesTest.class.getClassLoader().getResourceAsStream("redis.properties"));
        String mykey = properties.getProperty("mykey");
        System.err.println("mykey = " + mykey);
    }

    /**
     * The file must be in the root folder of current project
     */
    @Test
    public void write2TestProperties() {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("testConfig.properties");

            // set the properties value
            prop.setProperty("database", "localhost");
            prop.setProperty("dbuser", "mkyong");
            prop.setProperty("dbpassword", "password");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * The file must be in project root path
     */
    @Test
    public void readFromFile() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("testConfig.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
