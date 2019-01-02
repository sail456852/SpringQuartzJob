package msc.databasepool;


import java.io.IOException;

public abstract class BaseConfig {
	protected static java.util.Properties prop = new java.util.Properties();

	public static String getString(String propertiesName, String localValue) {
        try {
            prop.load(BaseConfig.class.getClassLoader().getResourceAsStream("redis.properties"));
            if (java.io.File.separator.equals("/")) {
                String value = prop.getProperty(propertiesName);
                if (value == null) throw new RuntimeException("参数" + propertiesName + "未配置");
                return value;
            } else {
                return localValue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return localValue;
    }
	
	public static int getInt(String propertiesName, int localValue) {
        try {
            prop.load(BaseConfig.class.getClassLoader().getResourceAsStream("redis.properties"));
            if (java.io.File.separator.equals("/")) {
                String value = prop.getProperty(propertiesName);
                if (value == null) throw new RuntimeException("参数" + propertiesName + "未配置");
                try {
                    return Integer.parseInt(value);
                } catch(Exception e){
                    throw new RuntimeException("参数" + propertiesName + "配置错误");
                }
            } else {
                return localValue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return localValue;
    }
	
}
