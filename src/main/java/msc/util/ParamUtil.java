package msc.util;

import com.google.common.base.Splitter;
import msc.form.AliPayOrder;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2018/12/10<br/>
 * Time: 10:06<br/>
 * To change this template use File | Settings | File Templates.
 */
public class ParamUtil {


    /**
     * @author: eugene
     * @date: 2018/12/7
     * @return 校验没有入参是否全部为空
     * @throws IllegalAccessException
     */
    public static boolean isAllNull(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(obj) != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @author: eugene @date: 2019/1/15
     * @param obj
     * @return
     * @throws IllegalAccessException
     * 检查一个POJO  是不是所有field独有值, 用于FORM校验
     * true 全都有值
     */
    public static boolean allFieldHasValue(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(obj) == null) {
                return false;
            }
        }
        return true;
    }


    /**
     * POJO -> PostMan parameters key:value format
     *
     * @return
     * @throws IllegalAccessException
     * @paramdfsdobj
     */
    public static String toPostmanParamFormat(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            int modifiers = f.getModifiers();
            String modifiersStr = Modifier.toString(modifiers);
            if (!StringUtils.isEmpty(modifiersStr) && (!modifiersStr.contains("static") || !modifiersStr.contains("final"))) {
                String name = f.getName();
                String newName = name.substring(0, 1).toUpperCase() + name.substring(1);
                String getMethodName = "get" + newName;
                try {
                    Method method = obj.getClass().getMethod(getMethodName);
                    Object returnValue = method.invoke(obj);
                    System.err.println(name + " : " + returnValue);
                } catch (NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


//    public static void main(String[] args) {
//
//        AliPayOrder aliPayOrder = new AliPayOrder();
//        aliPayOrder.setF_account_id("admin");
//        aliPayOrder.setF_third_order_id("201901211900001234");
//        aliPayOrder.setF_buy_num(0);
//        aliPayOrder.setF_address("");
//        aliPayOrder.setF_amount(1);
//        aliPayOrder.setF_phone("");
//        aliPayOrder.setF_order_status(0);
//        aliPayOrder.setF_pay_status(0);
//        aliPayOrder.setF_pay_method("");
//        aliPayOrder.setF_create_time(new Date());
//        aliPayOrder.setF_update_time(new Date());
//        aliPayOrder.setF_client_ip("192.168.6.82");
//        aliPayOrder.setF_goods_name("testProduct");
//        aliPayOrder.setF_receiver("");
//        aliPayOrder.setF_ls_order_id("");
//        aliPayOrder.setF_ali_order_id("");
//        aliPayOrder.setF_goods_id(0);
//        aliPayOrder.setF_id(0);
//
//        try {
//            toPostmanParamFormat(aliPayOrder);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     *  key:value string to JSON string for postman
     * @param keyValueString
     * @return
     */
    public static String postmanKVparamsToJsonFormat(String keyValueString) {
        int count = 1;
        Map<String, String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        String halfJson = keyValueString.replaceAll("\\n", ",");
        System.err.println("halfJson = " + halfJson);
        String[] split = halfJson.split("[,:]");
        for (String s : split) {
            list.add(s);
        }


        System.err.println("map = " + map);
        StringBuilder sb = new StringBuilder();
        String nearJson = halfJson.replaceAll(",", ",\"").replaceAll(":", "\":");
//        System.err.println("nearJson = " + nearJson);
        String fullJson  = sb.append("{ \"").append(nearJson).append(" }").toString();
//        System.err.println("fullJson = " + fullJson);
        return fullJson;
    }

    public static void main(String[] args) {
        String kvStr = "f_mobile:17777777779\n" +
                "f_password:app123456\n" +
                "f_parent_account_id:00089189";
        String jsonStr = postmanKVparamsToJsonFormat(kvStr);
//        System.err.println(jsonStr);
    }
}
