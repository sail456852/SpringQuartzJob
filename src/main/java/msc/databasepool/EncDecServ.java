package msc.databasepool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by defire on 2017/12/27.
 * email:982883422@qq.com
 */
public class EncDecServ {
    private final static Logger logger = LoggerFactory.getLogger(EncDecServ.class);
    private final static Integer LENGTH_MOBILE_NO_ENC = 11;
    private final static Integer LENGTH_MOBILE_DECED = 32;
    private final static Integer LENGTH_IDC_NO_ENC1 = 15;
    private final static Integer LENGTH_IDC_NO_ENC2 = 18;
    private final static Integer LENGTH_IDC_DECED1 = 32;//15位身份证加密后长度
    private final static Integer LENGTH_IDC_DECED2 = 48;//18位身份证加密后长度
    private final static Integer MAX_NUMBER = 10;
    private final static String VERSION = "1";
    private final static String ENC = "encrypt";
    private final static String DEC = "decrypt";
    /**
     * 最大解密条数100条,超过100条不解密
     */
    private static final int MAX_DEC_COUNT = 100;

    public static void main(String[] args) {
        //加密测试
        //        System.out.println(encMobile("14789984146"));//正常加密测试(单个)
        //        System.out.println(encMobile("1803305363"));//长度异常加密测试(单个)
        //        System.out.println(encMobile("1803305363c"));//内容异常加密测试(单个)
        //        System.out.println(encMobile(""));//内容异常加密测试(单个)
        //        System.out.println(encMobile("547A4FE8E324D924AD823702C925036E"));//密文调用加密(单个)
        //
        //        testPacthEnc1();//批量加密
        //        testPacthEnc2();//批量加密含错误
//                testPacthEnc3("547A4FE8E324D924AD823702C925036E");//使用密文调加密
//                testPacthEnc4("14789984146");//使用密文调加密
//                testPacthEnc5();//使用密文调加密
        //
        //
        //
        //        //解密测试
        //        System.out.println(decMobile("547A4FE8E324D924AD823702C925036E"));//正常解密测试(单个)
        //        System.out.println(decMobile("12345678899"));//长度异常解密测试(单个)
        //        System.out.println(decMobile("547A4FE8E324D924AD823702C925036E"));//内容异常解密测试(单个)
        //        testPacthDec1();//批量解密

        //        testPacthDec2();//批量解密含错误
        //        testPacthDec3("14789984146");//使用明文调解密
//        testPacthDec4("6C543DB4833DEB7E1FF769DDBDB7DD11");//批量解密 (含空)

        //测试身份证单个 加密
//    	testEncIdc("440882199008081");//873F7639563F712F95DA1A67316E6675
//    	testDecIdc("873F7639563F712F95DA1A67316E6675");

        //测试身份证单个 解密
//    	testEncIdc("44088219900808115x");//873F7639563F712F5A03C68D9A47CFC1582D47BBAE9EC1A5
//    	testDecIdc("873F7639563F712F5A03C68D9A47CFC154C50DA7FCDAE1E3");

        //测试身份证批量加密
//    	List<String> list = new ArrayList<String>();
//    	list.add("440882199008081");//873F7639563F712F95DA1A67316E6675
//    	list.add("440882199008082");//873F7639563F712F45CF5CD7B7751C80
//    	list.add("440882199008081150");//873F7639563F712F5A03C68D9A47CFC1582D47BBAE9EC1A5
//    	list.add("44088219900808115x");//873F7639563F712F5A03C68D9A47CFC154C50DA7FCDAE1E3
//    	testEncIdcs(list);

        //测试身份证批量 解密
//    	List<String> list = new ArrayList<String>();
//    	list.add("873F7639563F712F95DA1A67316E6675");
//    	list.add("873F7639563F712F45CF5CD7B7751C80");
//    	list.add("873F7639563F712F5A03C68D9A47CFC1582D47BBAE9EC1A5");
//    	list.add("873F7639563F712F5A03C68D9A47CFC154C50DA7FCDAE1E3");
//    	testDecIdcs(list);

        //测试用户名 单个 加密
//    	testEncName("张三");//35D9339CB0AECFAE
//    	testEncName("1234sad89012345a");

        //测试用户名单个 解密
//    	testDecName("35D9339CB0AECFAE");
//    	testDecName("1234sad89012345a");

        //测试用户名批量加密
//    	List<String> list = new ArrayList<String>();
//    	list.add("张三");//35D9339CB0AECFAE
//    	list.add("李四");//E0C0E65FE745FBFC
//    	list.add("asdfa");//CC6889DE22E3A4A5
//    	list.add("fdsfa123");//B8FF4B46848888CE
//    	testEncNames(list);

        //测试用户名批量 解密
//    	List<String> list = new ArrayList<String>();
//    	list.add("35D9339CB0AECFAE");
//    	list.add("E0C0E65FE745FBFC");
//    	list.add("CC6889DE22E3A4A5");
//    	list.add("B8FF4B46848888CE");
//    	testDecNames(list);

        //测试银行卡号 单个 加密
//    	testEncBankCard("6911314584131434");//DC781E2DD754329AFA15F279D79C7064
//    	testEncBankCard("69113145qa431123");

        //测试银行卡单个 解密
//    	testDecBankCard("DC781E2DD754329AFA15F279D79C7064");
//    	testDecBankCard("69113145qa431123");

        //测试银行卡批量加密
//    	List<String> list = new ArrayList<String>();
//    	list.add("69113qa51a4131443");//D70AB3B93EE7A5CBB248D26E3E298AB6890B725BD841F1C8
//    	list.add("69113145841314");//DC781E2DD754329A3E8AC98884D9A741
//    	list.add("6911314584131431");//DC781E2DD754329A1D19AB0104F9E3DB
//    	list.add("6911314584131sdfs");//DC781E2DD754329AFCA7AC06FAEB2F9564EE5CB2FBAE36B0
//    	testEncBankCards(list);

        //测试银行卡批量 解密
//    	List<String> list = new ArrayList<String>();
//    	list.add("D70AB3B93EE7A5CBB248D26E3E298AB6890B725BD841F1C8");
//    	list.add("DC781E2DD754329A3E8AC98884D9A741");
//    	list.add("DC781E2DD754329A1D19AB0104F9E3DB");
//    	list.add("DC781E2DD754329AFCA7AC06FAEB2F9564EE5CB2FBAE36B0");
//    	testDecBankCards(list);


    }


    //批量加密测试100个手机号（不含错误手机号）
    private static void testPacthEnc1() {
        ArrayList<String> mobiles = new ArrayList<>();
        long j = 17000000000l;
        for (int i = 0; i < MAX_NUMBER; i++) {
            mobiles.add(j + i + 1L + "");
        }
        printResult(encMobiles(mobiles));
    }


    //批量加密测试102个手机号（包含两个错误手机号）
    private static void testPacthEnc2() {
        ArrayList<String> mobiles = new ArrayList<>();
        mobiles.add("123456789011");//错误手机号
        long j = 17000000000l;
        for (int i = 0; i < MAX_NUMBER; i++) {
            mobiles.add(j + i + 1L + "");
        }
        mobiles.add("123456789011");//错误手机号
        printResult(encMobiles(mobiles));
    }


    //批量加密测试102个手机号（包含两个错误手机号）
    private static void testPacthEnc3(String key) {
        ArrayList<String> mobiles = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER; i++) {
            mobiles.add(key);
        }
        printResult(encMobiles(mobiles));
    }

    //批量加密测试102个手机号（含空）
    private static void testPacthEnc4(String key) {
        ArrayList<String> mobiles = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER; i++) {
            mobiles.add(key);
        }
        mobiles.add("");
        mobiles.add(null);
        mobiles.add("null");
        printResult(encMobiles(mobiles));
    }

    //批量加密测试10个手机号正常加密
    private static void testPacthEnc5() {
        ArrayList<String> mobiles = new ArrayList<>();
        mobiles.add("15521113042");
        mobiles.add("15524606897");
        mobiles.add("13147145355");
        mobiles.add("13824275551");
        mobiles.add("15521113042");

        mobiles.add("15062757887");
        mobiles.add("15051872628");
        mobiles.add("13306795025");
        mobiles.add("18025313115");
        mobiles.add("15602968481");
        printResult(encMobiles(mobiles));
    }

    //批量解密测试100个手机号
    private static void testPacthDec1() {
        ArrayList<String> mobiles = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER; i++) {
            mobiles.add("6C543DB4833DEB7E1FF769DDBDB7DD11");
        }
        printResult(decMobiles(mobiles));
    }

    //批量解密测试102个手机号（含2个错误密文）
    private static void testPacthDec2() {
        ArrayList<String> mobiles = new ArrayList<>();
        mobiles.add("6C543DB4833DEB7E1FF769DDBDB7DD1111");//长度不对
        for (int i = 0; i < MAX_NUMBER; i++) {
            mobiles.add("6C543DB4833DEB7E1FF769DDBDB7DD11");
        }
        mobiles.add("14789984146");//内容不对
        printResult(decMobiles(mobiles));
    }


    //批量解密测试 明文调用解密
    private static void testPacthDec3(String mobile) {
        ArrayList<String> mobiles = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER; i++) {
            mobiles.add(mobile);
        }
        printResult(decMobiles(mobiles));
    }

    private static void testPacthDec4(String mobile) {
        ArrayList<String> mobiles = new ArrayList<>();
        for (int i = 0; i < MAX_NUMBER; i++) {
            mobiles.add(mobile);
        }
        mobiles.add("");
        mobiles.add("null");
        printResult(decMobiles(mobiles));
    }

    private static void printResult(List<String> list) {
        logger.info("返回数据长度{}", list.size());
        for (String key : list) {
            System.out.println(key);
        }
    }

    private static void testEncIdc(String obj) {
        System.out.println(encIdc(obj));
    }

    private static void testDecIdc(String obj) {
        System.out.println(decIdc(obj));
    }

    private static void testEncIdcs(List<String> obj) {
        List<String> list = encIdcs(obj);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void testDecIdcs(List<String> obj) {
        List<String> list = decIdcs(obj);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void testEncName(String obj) {
        System.out.println(encName(obj));
    }

    private static void testDecName(String obj) {
        System.out.println(decName(obj));
    }

    private static void testEncNames(List<String> obj) {
        List<String> list = encNames(obj);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void testDecNames(List<String> obj) {
        List<String> list = decNames(obj);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void testEncBankCard(String obj) {
        System.out.println(encBankCard(obj));
    }

    private static void testDecBankCard(String obj) {
        System.out.println(decBankCard(obj));
    }

    private static void testEncBankCards(List<String> obj) {
        List<String> list = encBankCards(obj);
        for (String s : list) {
            System.out.println(s);
        }
    }

    private static void testDecBankCards(List<String> obj) {
        List<String> list = decBankCards(obj);
        for (String s : list) {
            System.out.println(s);
        }
    }


    /************************************************************以上为测试代码，******************************************************************/


    /************************************************************银行卡号加密解密代码start******************************************************************/

    /**
     * 加密单个银行卡号
     *
     * @param bc 银行卡号
     * @return 加密之后的密文
     */
    public static String encBankCard(String bc) {
        return checkEncCode(bc) ? bc : encOrDecOneBankCard(bc, ENC);
    }

    /**
     * 加密多个银行卡号
     *
     * @param bcs 需要加密的银行卡号
     * @return 加密之后的密文list 顺序和提交过来的顺序一致
     */
    public static List<String> encBankCards(List<String> bcs) {
        return encOrDecBankCards(bcs, ENC);
    }

    /**
     * 解密单个银行卡号
     *
     * @param bc 需要解密的银行卡号
     * @return 解密之后的明文
     */
    public static String decBankCard(String bc) {
        return checkEncCode(bc) ? encOrDecOneBankCard(bc, DEC) : bc;
    }

    /**
     * 批量解密银行卡号
     *
     * @param bcs 批量银行卡号
     * @return 解密之后的银行卡号 顺序和提交过来的顺序一致
     */
    public static List<String> decBankCards(List<String> bcs) {
        boolean isInvoke = false;
        if (bcs.size() > MAX_DEC_COUNT) {
            isInvoke = false;
        } else {
            for (String bc : bcs) {
                if (checkEncCode(bc)) {
                    isInvoke = true;
                    break;
                }
            }
        }
        return isInvoke ? encOrDecBankCards(bcs, DEC) : bcs;
    }

    /**
     * 加密 解密 银行卡号 单个方法
     *
     * @param bc
     * @param method
     * @return
     */
    private static String encOrDecOneBankCard(String bc, String method) {
        String result = "";
        try {
            JSONArray bcArray = new JSONArray();
            bcArray.add(bc);
            result = encOrDecCommon(bcArray, method);
            JSONArray resultArray = JSONArray.parseArray(result);
            if (resultArray != null && resultArray.size() > 0) {
                JSONObject jsonObject = resultArray.getJSONObject(0);
                String encBc = jsonObject.get(bc) != null ? jsonObject.get(bc).toString() : null;
                logger.trace("银行卡号加密接口调用 bc = {} 返回   encBc = {}", bc, jsonObject.get(bc));
                if (method.equals(ENC)) {
                    if (checkEncCode(encBc)) {
                        bc = encBc;
                    } else {
                        logger.error("encBc server wrong, bc = {}, encBc={}", bc, encBc);
                    }
                } else {
                    if (!checkEncCode(encBc)) {
                        bc = encBc;
                    } else {
                        logger.error("decBc server wrong, bc = {}, encBc={}", bc, encBc);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("encOrDecOneBankCard exception result = {}", e, result);
            logger.error("bc={},method={}", bc, method);
        }
        return bc;
    }

    /**
     * 批量加解密 用户名 公共函数
     *
     * @param bcs
     * @param method
     * @return
     */
    private static List<String> encOrDecBankCards(List<String> bcs, String method) {
        List<String> returnResult = new ArrayList<>();
        String result = "";
        Map<String, String> rmap = new HashMap<String, String>();
        if (bcs != null && bcs.size() > 0) {
            try {
                JSONArray bcArray = new JSONArray();
                for (String bc : bcs) {
                    bc = StringUtils.isEmpty(bc) ? "" : bc;
                    bcArray.add(bc);
                }
                result = encOrDecCommon(bcArray, method);
                if (StringUtils.isEmpty(result)) {
                    throw new Exception("上游服务异常，未返回任何数据");
                }
                JSONArray array = JSON.parseArray(result);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject one = array.getJSONObject(i);
                    Set<String> set = one.keySet();
                    for (String key : set) {
                        if (method.equals(ENC)) {
                            if (one.get(key) != null && !checkEncCode(key)) {
                                String encBc = one.get(key).toString();
                                if (checkEncCode(encBc)) {
                                    rmap.put(key, encBc);
                                } else {
                                    logger.error("encBcs server wrong, bc={}, encBc={}", key, encBc);
                                }
                            } else {
                                rmap.put(key, key);
                            }
                        }
                        if (method.equals(DEC)) {
                            if (one.get(key) != null && checkEncCode(key)) {
                                String decBc = one.get(key).toString();
                                if (!checkEncCode(decBc)) {
                                    rmap.put(key, one.get(key).toString());
                                } else {
                                    logger.error("encBcs server wrong, bc={}, decBc={}", key, decBc);
                                }
                            } else {
                                rmap.put(key, key);
                            }
                        }
                    }
                }
                logger.trace(rmap.toString());
                for (String bc : bcs) {
                    returnResult.add(rmap.get(bc) != null ? rmap.get(bc) : bc);
                }
            } catch (Exception e) {
                logger.error("encOrDecBankCards exception", e);
                logger.error("上游服务器返回异常！异常数据内容:{} ,bcs={}", result, bcs);
                returnResult = bcs;
            }
        }
        return returnResult;
    }

    /************************************************************银行卡号加密解密代码end******************************************************************/


    /************************************************************用户名加密解密代码start******************************************************************/

    /**
     * 加密单个用户名
     *
     * @param name 用户名
     * @return 加密之后的密文
     */
    public static String encName(String name) {
        return checkEncCode(name) ? name : encOrDecOneName(name, ENC);
    }

    /**
     * 加密多个用户名
     *
     * @param names 需要加密的用户名
     * @return 加密之后的密文list 顺序和提交过来的顺序一致
     */
    public static List<String> encNames(List<String> names) {
        return encOrDecNames(names, ENC);
    }

    /**
     * 解密单个用户名
     *
     * @param name 需要解密的用户名
     * @return 解密之后的明文
     */
    public static String decName(String name) {
        return checkEncCode(name) ? encOrDecOneName(name, DEC) : name;
    }

    /**
     * 批量解密用户名
     *
     * @param names
     * @return 解密之后的手机号 顺序和提交过来的顺序一致
     */
    public static List<String> decNames(List<String> names) {
        boolean isInvoke = false;
        if (names.size() > MAX_DEC_COUNT) {
            isInvoke = false;
        } else {
            for (String name : names) {
                if (checkEncCode(name)) {
                    isInvoke = true;
                    break;
                }
            }
        }
        return isInvoke ? encOrDecNames(names, DEC) : names;
    }

    /**
     * 加密 解密 用户名 单个方法
     *
     * @param name
     * @param method
     * @return
     */
    private static String encOrDecOneName(String name, String method) {
        String result = "";
        try {
            JSONArray idcArray = new JSONArray();
            idcArray.add(name);
            result = encOrDecCommon(idcArray, method);
            JSONArray resultArray = JSONArray.parseArray(result);
            if (resultArray != null && resultArray.size() > 0) {
                JSONObject jsonObject = resultArray.getJSONObject(0);
                String encName = jsonObject.get(name) != null ? jsonObject.get(name).toString() : null;
                logger.trace("用户名加密接口调用 name = {} 返回   encName = {}", name, jsonObject.get(name));
                if (method.equals(ENC)) {
                    if (checkEncCode(encName)) {
                        name = encName;
                    } else {
                        logger.error("encName server wrong, name = {}, encName={}", name, encName);
                    }
                } else {
                    if (!checkEncCode(encName)) {
                        name = encName;
                    } else {
                        logger.error("decName server wrong, name = {}, encName={}", name, encName);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("encName exception result = {}", e, result);
            logger.error("name={},method={}", name, method);
        }
        return name;
    }

    /**
     * 批量加解密 用户名 公共函数
     *
     * @param names
     * @param method
     * @return
     */
    private static List<String> encOrDecNames(List<String> names, String method) {
        List<String> returnResult = new ArrayList<>();
        String result = "";
        Map<String, String> rmap = new HashMap<String, String>();
        if (names != null && names.size() > 0) {
            try {
                JSONArray nameArray = new JSONArray();
                for (String name : names) {
                    name = StringUtils.isEmpty(name) ? "" : name;
                    nameArray.add(name);
                }
                result = encOrDecCommon(nameArray, method);
                if (StringUtils.isEmpty(result)) {
                    throw new Exception("上游服务异常，未返回任何数据");
                }
                JSONArray array = JSON.parseArray(result);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject one = array.getJSONObject(i);
                    Set<String> set = one.keySet();
                    for (String key : set) {
                        if (method.equals(ENC)) {
                            if (one.get(key) != null && !checkEncCode(key)) {
                                String encName = one.get(key).toString();
                                if (checkEncCode(encName)) {
                                    rmap.put(key, encName);
                                } else {
                                    logger.error("encNames server wrong, name={}, encName={}", key, encName);
                                }
                            } else {
                                rmap.put(key, key);
                            }
                        }
                        if (method.equals(DEC)) {
                            if (one.get(key) != null && checkEncCode(key)) {
                                String decName = one.get(key).toString();
                                if (!checkEncCode(decName)) {
                                    rmap.put(key, one.get(key).toString());
                                } else {
                                    logger.error("decNames server wrong, name={}, decName={}", key, decName);
                                }
                            } else {
                                rmap.put(key, key);
                            }
                        }
                    }
                }
                logger.trace(rmap.toString());
                for (String name : names) {
                    returnResult.add(rmap.get(name) != null ? rmap.get(name) : name);
                }
            } catch (Exception e) {
                logger.error("encOrDecNames exception", e);
                logger.error("上游服务器返回异常！异常数据内容:{} ,names={}", result, names);
                returnResult = names;
            }
        }
        return returnResult;
    }

    /************************************************************用户名加密解密代码end******************************************************************/

    /************************************************************身份证号加密解密代码start******************************************************************/
    /**
     * 加密单个身份证
     *
     * @param idc 身份证号
     * @return 加密之后的密文
     */
    public static String encIdc(String idc) {
        return encOrDecOneIdc(idc, ENC, LENGTH_IDC_NO_ENC1, LENGTH_IDC_NO_ENC2);
    }

    /**
     * 加密多个身份证
     *
     * @param idcs 需要加密的身份证
     * @return 加密之后的密文list 顺序和提交过来的顺序一致
     */
    public static List<String> encIdcs(List<String> idcs) {
        return encOrDecIdcs(idcs, ENC);
    }

    /**
     * 解密单个身份证
     *
     * @param idc 需要解密的身份证
     * @return 解密之后的明文
     */
    public static String decIdc(String idc) {
        return encOrDecOneIdc(idc, DEC, LENGTH_IDC_DECED1, LENGTH_IDC_DECED2);
    }

    /**
     * 批量解密身份证
     *
     * @param idcs
     * @return 解密之后的手机号 顺序和提交过来的顺序一致
     */
    public static List<String> decIdcs(List<String> idcs) {
        boolean isInvoke = false;
        if (idcs.size() > MAX_DEC_COUNT) {
            isInvoke = false;
        } else {
            for (String idc : idcs) {
                if (StringUtils.isNotEmpty(idc) && (idc.length() == LENGTH_IDC_DECED1 || idc.length() == LENGTH_IDC_DECED2)) {
                    isInvoke = true;
                    break;
                }
            }
        }
        return isInvoke ? encOrDecIdcs(idcs, DEC) : idcs;
    }

    /**
     * 批量加解密 身份证 公共函数
     *
     * @param idcs
     * @param method
     * @return
     */
    private static List<String> encOrDecIdcs(List<String> idcs, String method) {
        List<String> returnResult = new ArrayList<>();
        String result = "";
        Map<String, String> rmap = new HashMap<String, String>();
        if (idcs != null && idcs.size() > 0) {
            try {
                JSONArray idcArray = new JSONArray();
                for (String idc : idcs) {
                    idc = StringUtils.isEmpty(idc) ? "" : idc;
                    idcArray.add(idc);
                }
                result = encOrDecCommon(idcArray, method);
                if (StringUtils.isEmpty(result)) {
                    throw new Exception("上游服务异常，未返回任何数据");
                }
                JSONArray array = JSON.parseArray(result);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject one = array.getJSONObject(i);
                    Set<String> set = one.keySet();
                    for (String key : set) {
                        if (method.equals(ENC)) {
                            if (one.get(key) != null && (key.length() == LENGTH_IDC_NO_ENC1 || key.length() == LENGTH_IDC_NO_ENC2)) {
                                String encIdc = one.get(key).toString();
                                if (encIdc.length() == LENGTH_IDC_DECED1 || encIdc.length() == LENGTH_IDC_DECED2) {
                                    rmap.put(key, encIdc);
                                } else {
                                    logger.error("encIdcs server wrong, idc={}, encIdc={}", key, encIdc);
                                }
                            } else {
                                rmap.put(key, key);
                            }
                        }
                        if (method.equals(DEC)) {
                            if (one.get(key) != null && (key.length() == LENGTH_IDC_DECED1 || key.length() == LENGTH_IDC_DECED2)) {
                                String decIdc = one.get(key).toString();
                                if (decIdc.length() == LENGTH_IDC_NO_ENC1 || decIdc.length() == LENGTH_IDC_NO_ENC2) {
                                    rmap.put(key, one.get(key).toString());
                                } else {
                                    logger.error("decIdcs server wrong, idc={}, decIdc={}", key, decIdc);
                                }
                            } else {
                                rmap.put(key, key);
                            }
                        }
                    }
                }
                logger.trace(rmap.toString());
                for (String idc : idcs) {
                    returnResult.add(rmap.get(idc) != null ? rmap.get(idc) : idc);
                }
            } catch (Exception e) {
                logger.error("encOrDecIdcs exception", e);
                logger.error("上游服务器返回异常！异常数据内容:{} ,idc={}", result, idcs);
                returnResult = idcs;
            }
        }
        return returnResult;
    }

    /**
     * 加密 解密 身份证 单个方法
     *
     * @param idc
     * @param method
     * @param length1
     * @param length2
     * @return
     */
    private static String encOrDecOneIdc(String idc, String method, Integer length1, Integer length2) {
        String result = "";
        if (StringUtils.isNotEmpty(idc) && (idc.length() == length1 || idc.length() == length2)) {
            try {
                JSONArray idcArray = new JSONArray();
                idcArray.add(idc);
                result = encOrDecCommon(idcArray, method);
                JSONArray resultArray = JSONArray.parseArray(result);
                if (resultArray != null && resultArray.size() > 0) {
                    JSONObject jsonObject = resultArray.getJSONObject(0);
                    String encIdc = jsonObject.get(idc) != null ? jsonObject.get(idc).toString() : null;
                    logger.trace("身份证号加密接口调用 idc = {} 返回   encIdc = {}", idc, jsonObject.get(idc));
                    if (method.equals(ENC)) {
                        if (encIdc != null && (encIdc.length() == LENGTH_IDC_DECED1 || encIdc.length() == LENGTH_IDC_DECED2)) {
                            idc = encIdc;
                        } else {
                            logger.error("encIdc server wrong, idc = {}, encIdc={}", idc, encIdc);
                        }
                    } else {
                        if (encIdc != null && (encIdc.length() == LENGTH_IDC_NO_ENC1 || encIdc.length() == LENGTH_IDC_NO_ENC2)) {
                            idc = encIdc;
                        } else {
                            logger.error("decIdc server wrong, idc = {}, encIdc={}", idc, encIdc);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("encIdc exception result = {}", e, result);
                logger.error("idc={},method={}", idc, method);
            }
        } else {
            logger.info("本次调用身份证号为空，或者长度不对，返回请求值 idc = {},method={}", idc, method);
        }
        return idc;
    }

    /************************************************************身份证号加密解密代码end******************************************************************/


    /************************************************************手机号加密解密代码start******************************************************************/
    /**
     * 加密单个手机号
     *
     * @param mobile
     * @return 加密之后的密文
     */
    public static String encMobile(String mobile) {
        return encOrDecOneMobile(mobile, ENC, LENGTH_MOBILE_NO_ENC);
    }

    /**
     * 加密多个手机号
     *
     * @param mobiles 需要加密的手机号
     * @return 加密之后的密文list 顺序和提交过来的顺序一致
     */
    public static List<String> encMobiles(List<String> mobiles) {
        return encOrDecMobiles(mobiles, ENC);
    }

    /**
     * 解密单个手机号
     *
     * @param mobile
     * @return 解密之后的手机号 顺序和提交过来的顺序一致
     */
    public static String decMobile(String mobile) {
        return isShouldDec(mobile) ? encOrDecOneMobile(mobile, DEC, LENGTH_MOBILE_DECED) : mobile;
    }

    /**
     * 批量解密手机号
     *
     * @param mobiles
     * @return
     */
    public static List<String> decMobiles(List<String> mobiles) {
        boolean isInvoke = false;
        if (mobiles.size() > MAX_DEC_COUNT) {
            isInvoke = false;
        } else {
            for (String mobile : mobiles) {
                if (isShouldDec(mobile)) {
                    isInvoke = true;
                    break;
                }
            }
        }
        return isInvoke ? encOrDecMobiles(mobiles, DEC) : mobiles;
    }

    /**
     * 是否需要解码
     *
     * @param mobile
     * @return
     */
    private static boolean isShouldDec(String mobile) {
        return !StringUtils.isBlank(mobile) && mobile.length() != 11;
    }


    /**
     * 批量加解密 公共函数
     *
     * @param mobiles
     * @return
     */
    private static List<String> encOrDecMobiles(List<String> mobiles, String method) {
        List<String> returnResult = new ArrayList<>();
        String result = "";
        Map<String, String> rmap = new HashMap<String, String>();
        if (mobiles != null && mobiles.size() > 0)
            try {
                JSONArray mobileArray = new JSONArray();
                for (String mobile : mobiles) {
                    mobile = StringUtils.isEmpty(mobile) ? "" : mobile;
                    mobileArray.add(mobile);
                }
                result = encOrDecCommon(mobileArray, method);
                if (StringUtils.isEmpty(result)) {
                    throw new Exception("上游服务异常，未返回任何数据");
                }
                JSONArray array = JSON.parseArray(result);
                for (int i = 0; i < array.size(); i++) {
                    JSONObject one = array.getJSONObject(i);
                    Set<String> set = one.keySet();
                    for (String key : set) {
                        if (method.equals(ENC)) {
                            if (one.get(key) != null && key.length() == LENGTH_MOBILE_NO_ENC) {
                                String encMobile = one.get(key).toString();
                                if (encMobile.length() == LENGTH_MOBILE_DECED) {
                                    rmap.put(key, encMobile);
                                } else {
                                    logger.error("encMobiles server wrong, mobile={}, encMobile={}", key, encMobile);
                                }
                            } else {
                                rmap.put(key, key);
                            }
                        }
                        if (method.equals(DEC)) {
                            if (one.get(key) != null && key.length() == LENGTH_MOBILE_DECED) {
                                rmap.put(key, one.get(key).toString());
                            } else {
                                rmap.put(key, key);
                            }
                        }
                    }
                }
                logger.trace(rmap.toString());
                for (String mobile : mobiles) {
                    returnResult.add(rmap.get(mobile) != null ? rmap.get(mobile) : mobile);
                }
            } catch (Exception e) {
                logger.error("encMobiles exception", e);
                logger.error("上游服务器返回异常！异常数据内容:{} ,mobile={}", result, mobiles);
                returnResult = mobiles;
            }
        return returnResult;
    }

    /**
     * 加密 解密单个方法的公共逻辑
     *
     * @param mobile
     * @param length
     * @return
     */
    private static String encOrDecOneMobile(String mobile, String method, Integer length) {
        String result = "";
        if (StringUtils.isNotEmpty(mobile) && mobile.length() == length) {
            try {
                JSONArray mobileArray = new JSONArray();
                mobileArray.add(mobile);
                result = encOrDecCommon(mobileArray, method);
                JSONArray resultArray = JSONArray.parseArray(result);
                if (resultArray != null && resultArray.size() > 0) {
                    JSONObject jsonObject = resultArray.getJSONObject(0);
                    logger.trace("手机号加密接口调用返回手机号 {},{}", mobile, jsonObject.get(mobile));

                    if (method.equals(ENC)) {
                        String encMobile = jsonObject.get(mobile) != null ? jsonObject.get(mobile).toString() : null;
                        if (encMobile != null && encMobile.length() == LENGTH_MOBILE_DECED) {
                            mobile = encMobile;
                        } else {
                            logger.error("encMobiles server wrong, mobile={}, encMobile={}", mobile, encMobile);
                        }
                    } else {
                        mobile = jsonObject.get(mobile) != null ? jsonObject.get(mobile).toString() : mobile;
                    }
                }
            } catch (Exception e) {
                logger.error("encMobile exception result = {}", e, result);
                logger.error("mobile={},method={}", mobile, method);
            }
        } else {
            logger.info("本次调用手机后为空，或者长度不对，返回请求值{},method={}", mobile, method);
        }
        return mobile;
    }

    /************************************************************手机号加密解密代码end******************************************************************/

    /************************************************************以下为加密基础功能代码******************************************************************/


    /**
     * 公共函数
     *
     * @param mobiles
     * @param method
     * @return
     */
    private static String encOrDecCommon(JSONArray mobiles, String method) {
        logger.trace("EncDecServ request start method={}, mobiles={}", method, mobiles);
        long start = System.currentTimeMillis();
        JSONObject obj = new JSONObject();
        obj.put("method", method);
        obj.put("version", VERSION);
        obj.put("data", mobiles);
        String result = httpMethodPost(Config.ENCDECCGI, obj.toJSONString(), "utf-8");
        long end = System.currentTimeMillis();
        logger.trace("EncDecServ request method={} 耗时={}毫秒", method, end - start);
        return result;
    }


    /**
     * 调用请求
     *
     * @param url
     * @param params
     * @param gb
     * @return
     */
    private static String httpMethodPost(String url, String params, String gb) {
        if (null == gb || "".equals(gb)) {
            gb = "utf-8";
        }
        StringBuffer sb = new StringBuffer();
        URL urls;
        HttpURLConnection uc = null;
        BufferedReader in = null;
        try {
            urls = new URL(url);
            uc = (HttpURLConnection) urls.openConnection();
            uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestMethod("GET");
            uc.setUseCaches(false);
            uc.connect();
            DataOutputStream out = new DataOutputStream(uc.getOutputStream());
            out.write(params.getBytes(gb));
            out.flush();
            out.close();
            InputStream i = uc.getInputStream();
            in = new BufferedReader(new InputStreamReader(uc.getInputStream(), gb));
            String readLine = "";
            while ((readLine = in.readLine()) != null) {
                sb.append(readLine).append("\n");
            }
            if (in != null) {
                in.close();
            }
            if (uc != null) {
                uc.disconnect();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            sb.append(e.getMessage());
        } finally {
            if (uc != null) {
                uc.disconnect();
            }
        }
        return sb.toString();
    }

    //校验加密规则
    private static boolean checkEncCode(String code) {
        return StringUtils.isNotEmpty(code) && code.length() % 16 == 0 && match("^(?![0-9]+$)(?![A-Z]+$)[0-9A-F]+$", code);
    }


    //正则校验
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
