package msc.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/7<br/>
 * Time: 17:06<br/>
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

    public static Date add(Date date, int calendarField, int amount)
    {
        if(date == null)
        {
            throw new IllegalArgumentException("The date must not be null");
        } else
        {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static void main(String[] args) {
        Date curDate = new Date();
        Date fiveDaysAgo = DateUtil.add(curDate, Calendar.DAY_OF_WEEK, -5);
        System.err.println("fiveDaysAgo = " + fiveDaysAgo);
    }
}
