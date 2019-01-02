package msc.databasepool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Configuration
{
    private Properties propertie;

    public Configuration() {
		super();
	}

    public Configuration(String propName)
    {
        propertie = new Properties();
        try {
        	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propName);
        	propertie.load(is);
        } catch (FileNotFoundException ex) {
            System.out.println("读取属性文件失败,原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
        }
    }

    public String getValue(String key)
    {
        if(propertie.containsKey(key)){
            String value = propertie.getProperty(key);//得到某一属性
            return value;
        }
        else
            return "";
    }

    /**
     * 清除properties文件中所有的key和其值
     */
    public void clear(){
        propertie.clear();
    }

    public void setValue(String key, String value)
    {
        propertie.setProperty(key, value);
    }

    public Properties getProperties(){
    	return propertie;
    }

    public Properties getPropertie() {
		return propertie;
	}

	public void setPropertie(Properties propertie) {
		this.propertie = propertie;
	}

	public static void main(String[] args)
    {

    }

}
