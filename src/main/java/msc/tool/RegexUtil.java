package msc.tool;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java表单验证工具类
 * 
 * @author jiqinlin
 * 
 */
@SuppressWarnings("unchecked")
public class RegexUtil {

    public static void main(String[] args) {
//        System.out.println("过滤中英文特殊字符: "+RegexUtil.stringFilter("中国~~!#$%%."));
//        System.out.println("是否包含中英文特殊字符: "+RegexUtil.isContainsSpecialChar("12"));
//        System.out.println("过滤html代码: "+RegexUtil.htmltoText("<JAVASCRIPT>12</JAVASCRIPT>DDDDD"));
//        System.out.println("判断中文字符: "+RegexUtil.isChineseChar("中国！"));
//        System.out.println("匹配汉字: "+RegexUtil.isChinese("中国中国"));
//        System.out.println("判断英文字符: "+RegexUtil.isEnglish("abc!"));
//        System.out.println("判断合法字符: "+RegexUtil.isRightfulString("F_emailselectinformation_schematableslimit248681"));
//        System.out.println("邮政编码验证: "+RegexUtil.isZipCode("162406"));
//        System.out.println("身份证号码验证: "+RegexUtil.isIdCardNo("35052419880210133e"));
 //       System.out.println("手机号码验证: "+RegexUtil.isInteger("136"));
//        System.out.println("电话号码验证: "+RegexUtil.isPhone("8889333"));
//        System.out.println("电话号码验证: "+RegexUtil.isNumeric("888.9333"));
//        System.out.println("匹配密码: "+RegexUtil.isPwd("d888d_ddddd"));
//        System.out.println("匹配密码: "+RegexUtil.isUrl("http://baidu.com"));
//        System.out.println("验证字符: "+RegexUtil.isEmail("10000ccddh_,.@qq.com"));
//        System.out.println(isEmail("416501600@qq.com"));
        //http://baidu.com www.baidu.com baidu.com
//        System.out.println(NumberUtils.toInt("-0000000002"));
        //System.out.println("匹匹匹abc".length());
//        System.out.println(" 登录用户判断："+RegexUtil.checkUsername("asdf55"));
//    	System.out.println("密码："+RegexUtil.isPwd("123456"));
//    	System.out.println(match("a23456", "^[a-zA-Z\\w]{6,12}$"));
//    	System.out.println("加密密码："+isPassword("E10ADC3949BA59ABBE56E057F20F883E"));
//    	int min = 3;
//    	int max = 20;
//    	System.out.println(match("12", "^[A-Za-z0-9]{"+min+","+max+"}$"));
//    	System.out.println(checkName("大"));
//    	System.out.println(isContainsSpecialChar("美容化妆、/C健身养生"));
//    	System.out.println(checkFTrade("1"));
//    	System.out.println(checkDateTime("2014-02-10 2010:10"));
//    	System.out.println(RegexUtil.isEngChinese("验<证字"));
//    	System.out.println("匹配密码: "+RegexUtil.isUrl("http://baidu.com"));
    	System.out.println(isMobile("132546gnhyj"));
    }
    
    public final static boolean isNull(Object[] objs){
        if(objs==null||objs.length==0) return true;
        return false;
    }
    
    public final static boolean isNull(Integer integer){
        if(integer==null||integer==0) return true;
        return false;
    }
    
    public final static boolean isNull(Collection collection){
        if(collection==null||collection.size()==0) return true;
        return false;
    }
    
    public final static boolean isNull(Map map){
        if(map==null||map.size()==0) return true;
        return false;
    }
    
    public final static boolean isNull(String str){
        return str == null || "".equals(str.trim()) || "null".equals(str.toLowerCase());
    }
    
    
    public final static boolean isNull(Long longs){
        if(longs==null||longs==0) return true;
        return false;
    }
    
    public final static boolean isNotNull(Long longs){
        return !isNull(longs);
    }
    
    public final static boolean isNotNull(String str){
        return !isNull(str);
    }
    
    public final static boolean isNotNull(Collection collection){
        return !isNull(collection);
    }
    
    public final static boolean isNotNull(Map map){
        return !isNull(map);
    }
    
    public final static boolean isNotNull(Integer integer){
        return !isNull(integer);
    }
    
    public final static boolean isNotNull(Object[] objs){
        return !isNull(objs);
    }
    
    /**
     * 匹配URL地址
     * 
     * @param str
     * @return
     * @author jiqinlin
     */
    public final static boolean isUrl(String str) {
        return match(str, "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$");
    }
    
    /**
     * 匹配密码，以字母开头，长度在6-12之间，只能包含字符、数字和下划线。
     * 
     * @param str
     * @return
     * @author jiqinlin
     */
    public final static boolean isPwd(String str) {
        return match(str, "^[a-zA-Z]\\w{6,12}$");
    }
    
    /**
     * 匹配加密后的密码，长度为32，只能包含大写字母以及数字
     * @param str
     * @return
     */
    public final static boolean isPassword(String str){
    	return match(str, "^[A-Z\\d]{32}$");
    }
    /**
     * 验证字符，只能包含中文、英文、数字、下划线等字符。
     * 
     * @param str
     * @return
     * @author jiqinlin
     */
    public final static boolean stringCheck(String str) {
        return match(str, "^[a-zA-Z0-9\u4e00-\u9fa5-_]+$");
    }
    
    /**
     * 匹配Email地址
     * 
     * @param str
     * @return
     * @author jiqinlin
     */
    public final static boolean isEmail(String str) {
        return match(str, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }
    
    /**
     * 匹配非负整数（正整数+0）
     * 
     * @param str
     * @return
     * @author jiqinlin
     */
    public final static boolean isInteger(String str) {
        return match(str, "^[+]?\\d+$");
    }
    
    /**
     * 判断数值类型，包括整数和浮点数
     * 
     * @param str
     * @return
     * @author jiqinlin
     */
    public final static boolean isNumeric(String str) { 
        if(isFloat(str) || isInteger(str)) return true;
        return false;
    }
    
    /**
     * 只能输入数字
     * 
     * @param str
     * @return
     * @author jiqinlin
     */
    public final static boolean isDigits(String str) {
        return match(str, "^[0-9]*$");
    }
    
    /**
     * 匹配正浮点数
     * 
     * @param str
     * @return
     * @author jiqinlin
     */
    public final static boolean isFloat(String str) {
        return match(str, "^[-\\+]?\\d+(\\.\\d+)?$");
    }
    
    /**
     * 联系电话(手机/电话皆可)验证   
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isTel(String text){
        if(isMobile(text)||isPhone(text)) return true;
        return false;
    }
    
    /**
     * 电话号码验证  
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isPhone(String text){
        return match(text, "^(\\d{3,4}-?)?\\d{7,9}$");
    }
    
    /**
     * 手机号码验证   
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isMobile(String text){
        if(text.length()!=11) return false;
        return match(text, "^(((13[0-9]{1})|(15[0-9]{1})|(177)|(14[7]{1})|(18[0-9]{1}))+\\d{8})$");
    }
    
    /**
     * 身份证号码验证 
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isIdCardNo(String text){
        return match(text, "^(\\d{6})()?(\\d{4})(\\d{2})(\\d{2})(\\d{3})(\\w)$");
    }
    /**
     * 注册号验证
     * @param text
     * @return
     */
    public final static boolean isLicense(String text){
    	return match(text,"^[0-9a-zA-Z]{10,20}$");
    }
    /**
     * 邮政编码验证 
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isZipCode(String text){
        return match(text, "^[0-9]{6}$");
    }
    
    /**
     * 判断整数num是否等于0
     * 
     * @param num
     * @return
     * @author jiqinlin
     */
    public final static boolean isIntEqZero(int num){ 
         return num==0;
    }
    
    /**
     * 判断整数num是否大于0
     * 
     * @param num
     * @return
     * @author jiqinlin
     */
    public final static boolean isIntGtZero(int num){ 
         return num>0;
    }
    
    /**
     * 判断整数num是否大于或等于0
     * 
     * @param num
     * @return
     * @author jiqinlin
     */
    public final static boolean isIntGteZero(int num){ 
        return num>=0;
    }
    
    /**
     * 判断浮点数num是否等于0
     * 
     * @param num 浮点数
     * @return
     * @author jiqinlin
     */
    public final static boolean isFloatEqZero(float num){ 
         return num==0f;
    }
    
    /**
     * 判断浮点数num是否大于0
     * 
     * @param num 浮点数
     * @return
     * @author jiqinlin
     */
    public final static boolean isFloatGtZero(float num){ 
         return num>0f;
    }
    
    /**
     * 判断浮点数num是否大于或等于0
     * 
     * @param num 浮点数
     * @return
     * @author jiqinlin
     */
    public final static boolean isFloatGteZero(float num){ 
        return num>=0f;
    }
    
    /**
     * 判断是否为合法字符(a-zA-Z0-9-_)
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isRightfulString(String text){
        return match(text, "^[A-Za-z0-9_-]+$"); 
    }
    
    /**
     * 判断英文字符(a-zA-Z)
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isEnglish(String text){
        return match(text, "^[A-Za-z]+$"); 
    }
    
    /**
     * 判断中文字符(包括汉字和符号)
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isChineseChar(String text){
        return match(text, "^[\u0391-\uFFE5]+$");
    }
    
    /**
     * 匹配汉字
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isChinese(String text){
        return match(text, "^[\u4e00-\u9fa5]+$");
    }
    
    /**
     * 匹配英文加汉字
     * 
     * @param text
     * @return
     * @author jiqinlin
     */
    public final static boolean isEngChinese(String text){
        return match(text, "^[\u4e00-\u9fa5A-Za-z0-9]+$");
    }
    
    /**
     * 是否包含中英文特殊字符，除英文"-_"字符外
     * 
     * @param text
     * @return
     */
    public static boolean isContainsSpecialChar(String text) {
        if(StringUtils.isBlank(text)) return false;
        String[] chars={"[","`","~","!","@","#","$","%","^","&","*","(",")","+","=","|","{","}","'",
                ":",";","'",",","[","]",".","<",">","/","?","~","！","@","#","￥","%","…","&","*","（","）",
                "—","+","|","{","}","【","】","‘","；","：","”","“","’","。","，","、","？","]"};
        for(String ch : chars){
            if(text.contains(ch)) return true;
        }
        return false;
    }
    
    /**
     * 过滤中英文特殊字符，除英文"-_"字符外
     * 
     * @param text
     * @return
     */
    public static String stringFilter(String text) {
        String regExpr="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
        Pattern p = Pattern.compile(regExpr);
        Matcher m = p.matcher(text);
        return m.replaceAll("").trim();     
    }
    
    /**
	 * java正则表达式匹配真实姓名(2~7个中文或者3~10个英文)
	 * @param name
	 * @return
	 */
	public static boolean checkName(String name) {
		String regx = "(([\u4E00-\u9FA5]{2,7})|([a-zA-Z]{3,10}))";
		return Pattern.matches(regx, name);
	}
    
    /**
     * 过滤html代码
     * 
     * @param inputString 含html标签的字符串
     * @return
     */
    public static String htmlFilter(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;
        Pattern p_ba;
        Matcher m_ba;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String patternStr = "\\s+";

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

//            p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
//            m_ba = p_ba.matcher(htmlStr);
//            htmlStr = m_ba.replaceAll(""); // 过滤空格

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }
        return textStr;// 返回文本字符串
    }
    /**
     * 匹配 登录账号
     * 规则：英文+数字
     * @param username  登录账号
     * @return
     */
    public static boolean checkUsername(String username,Integer min,Integer max){
    	return match(username, "^[_A-Za-z0-9]{"+min+","+max+"}$");
    }
    /**
     * 匹配所属行业
     * 规则数字、大写字母 顿号
     * @param name
     * @return
     */
    public static boolean checkFTrade(String name){
    	return match(name, "^[0-9A-Z、\u4e00-\u9fa5]{2,}$");
	}
    
    /**
     * 匹配\w
     */
    public static boolean checkWord(String name,Integer min,Integer max){
    	return match(name, "^[\\w]{"+min+","+max+"}$");
    }
    /**
     * 匹配详细地址 中文、大小写字母、-  长度大于2
     * @param text
     * @return
     */
    public static boolean checkDetailAddress(String text){
    	return match(text, "^[\u4e00-\u9fa5a-zA-Z0-9\\-]{3,}$");
    }
    
    public static boolean checkDate(String text){
    	return match(text, "^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$");
    }
    public static boolean checkDateTime(String text){
    	String reg = "^^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30))) [0-2][0-4]:[0-6]\\d:[0-6]\\d$";
    	return match(text, reg);
    }
   
    
    private final static boolean match(String text, String reg) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(reg))
            return false;
        return Pattern.compile(reg).matcher(text).matches();
    }
    
  
}
