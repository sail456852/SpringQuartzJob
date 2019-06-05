package spring.utils;

import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Exrickx
 */
public class StringUtils {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化 日期 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getTimeStamp(Date date) {
        if (date == null) {
            return dateFormat.format(new Date());
        } else {
            return dateFormat.format(date);
        }
    }

    /**
     * 格式化 日期 yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static Date getDate(String time) {

        try {
            return dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 判断字符创是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    /**
     * 判断字符创是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * Bean 转 Map
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    public static String randomCommentString() {
        // first search on redis then if null choose hard coded strings
        List<String> list = new ArrayList<String>();
        list.add("6点以后可以看房，请联系我");
        list.add("up");
        list.add("急转!降价了1860住福田市中心！，白菜价！");
        list.add("男女比例1:1");
        list.add("一楼只有2个房间，就有一个卫生间");
        list.add("少见的复式户型，空间大");
        list.add("包网包物业啊！亲们");

        int size = list.size();
        int i = IntUtils.randomIntSingleton(size);
        String comment = list.get(i);
        return comment;
    }

}
