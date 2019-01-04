package msc.databasepool;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Config extends BaseConfig {
    
    /**
     * 快支付2.0环境
     */
    public static String FASTPAYENVIRONMENT = "http://pos.lepass.cn/";
    
	public static String PROJECT_FLAG = "ykscript";
	public static boolean IS_TEST = true;
	public static boolean IS_JOB = false;
	private static String propertiesName = "config.properties";
	private static String DBDRIVER = "com.mysql.jdbc.Driver";
    //增加环境类型,默认为正式
    public static String ENVIRONMENT_TYPE = "RELEASE";
	
	public static String DBURL_BI_MASTER_DB = "jdbc:mysql://192.168.51.202:3310/lepos_bi?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_BI_MASTER_DB = "owen";
	public static String DBPASS_BI_MASTER_DB = "owen@yeahka.com"; 
	
	public static String DBURL_BI_SLAVE_DB = "jdbc:mysql://192.168.51.202:3310/lepos_bi?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_BI_SLAVE_DB = "owen";
	public static String DBPASS_BI_SLAVE_DB = "owen@yeahka.com"; 

	public static String DBURL_MASTER_DB = "jdbc:mysql://192.168.33.107/lepos?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_MASTER_DB = "root";
	public static String DBPASS_MASTER_DB = "1234"; 

	public static String DBURL_SLAVE_DB = "jdbc:mysql://192.168.50.50/lepos?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_SLAVE_DB = "search";
	public static String DBPASS_SLAVE_DB = "search@leposweb";

    public static String DBURL_LEPOSX_SLAVE_DB = "jdbc:mysql://192.168.33.107/lepos0?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    public static String DBNAME_LEPOSX_SLAVE_DB = "root";
    public static String DBPASS_LEPOSX_SLAVE_DB = "1234";

	public static String DBURL_LEPOSX1_SLAVE_DB = "jdbc:mysql://192.168.33.107/lepos0?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_LEPOSX1_SLAVE_DB = "root";
	public static String DBPASS_LEPOSX1_SLAVE_DB = "1234";
	
	public static String DBURL_LEPOS_REPLICAT_SLAVE_DB = "jdbc:mysql://192.168.33.107/lepos0?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_LEPOS_REPLICAT_SLAVE_DB = "root";
	public static String DBPASS_LEPOS_REPLICAT_SLAVE_DB = "1234";
	
    public static String DBURL_AUTOREONCILE_HISTORY_DB = "jdbc:mysql://192.168.51.202/autoreconcile?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_AUTOREONCILE_HISTORY_DB = "search";
	public static String DBPASS_AUTOREONCILE_HISTORY_DB = "search@leposweb";

	public static String DBURL_ADMIN_HISTORY_DB = "jdbc:mysql://192.168.51.202/admin?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_ADMIN_HISTORY_DB = "search";
	public static String DBPASS_ADMIN_HISTORY_DB = "search@leposweb";
	
	public static String DBURL_HIS_DB = "jdbc:mysql://127.0.0.1/lepos?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_HIS_DB = "root";
	public static String DBPASS_HIS_DB = "123456";
	
	public static String DBURL_PAY_DB = "jdbc:mysql://127.0.0.1/pay?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_PAY_DB = "root";
	public static String DBPASS_PAY_DB = "123456";
	
	public static String DBURL_YKADMIN_DB = "jdbc:mysql://127.0.0.1/ykadmin?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_YKADMIN_DB = "root";
	public static String DBPASS_YKADMIN_DB = "1234";
	
	public static String DBURL_BUSINESS_DB = "jdbc:mysql://192.168.51.203/lepos_business?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_BUSINESS_DB = "mysqluser";
	public static String DBPASS_BUSINESS_DB = "hello@openpos";
	
	public static String TEST_DBURL_BUSINESS_DB = "jdbc:mysql://192.168.33.107/lepos_business?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String TEST_DBNAME_BUSINESS_DB = "root";
	public static String TEST_DBPASS_BUSINESS_DB = "1234";
	
	public static String DBURL_BUSINESS_SLAVE_DB = "jdbc:mysql://192.168.51.175/lepos_business?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_BUSINESS_SLAVE_DB = "mysqluser";
	public static String DBPASS_BUSINESS_SLAVE_DB = "hello@openpos";
	
	public static String TEST_DBURL_BUSINESS_SLAVE_DB = "jdbc:mysql://192.168.33.107/lepos_business?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String TEST_DBNAME_BUSINESS_SLAVE_DB = "root";
	public static String TEST_DBPASS_BUSINESS_SLAVE_DB = "1234";
	
	public static String DBURL_SHOP_DB = "jdbc:mysql://127.0.0.1/shop?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_SHOP_DB = "root";
	public static String DBPASS_SHOP_DB = "123456";
	
	public static String DBURL_O2O_MASTER_DB = "jdbc:mysql://127.0.0.1/o2o?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_O2O_MASTER_DB = "root";
	public static String DBPASS_O2O_MASTER_DB = "123456";
	
	public static String DBURL_O2O_SLAVE_DB = "jdbc:mysql://127.0.0.1/o2o?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_O2O_SLAVE_DB = "root";
	public static String DBPASS_O2O_SLAVE_DB = "123456";
	
	public static String DBURL_ADMIN_SLAVE_DB = "jdbc:mysql://127.0.0.1/admin?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_ADMIN_SLAVE_DB = "root";
	public static String DBPASS_ADMIN_SLAVE_DB = "123456";
	
	public static String DBURL_YkPay_SLAVE_DB = "jdbc:mysql://127.0.0.1/ykpay?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_YkPay_SLAVE_DB = "root";
	public static String DBPASS_YkPay_SLAVE_DB = "123456";

	public static String DBURL_YkPay_MASTER_DB = "jdbc:mysql://127.0.0.1/ykpay?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_YkPay_MASTER_DB = "root";
	public static String DBPASS_YkPay_MASTER_DB = "123456";
	
	//lepos表已迁移
	public static String DBURL_WEB_MASTER_DB = "jdbc:mysql://127.0.0.1/lepos?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_WEB_MASTER_DB = "root";
	public static String DBPASS_WEB_MASTER_DB = "123456";

	public static String DBURL_WEB_SLAVE_DB = "jdbc:mysql://127.0.0.1/lepos?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_WEB_SLAVE_DB = "root";
	public static String DBPASS_WEB_SLAVE_DB = "123456";
	
	public static String URL_LEPOSWEB = "jdbc:mysql://127.0.0.1/lepos?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String NAME_LEPOSWEB = "root";
	public static String PASS_LEPOSWEB = "123456";
	
	public static String URL_LEPOSWEB_SLAVE = "jdbc:mysql://127.0.0.1/lepos?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String NAME_LEPOSWEB_SLAVE = "root";
	public static String PASS_LEPOSWEB_SLAVE = "123456";
	
	public static String DBURL_REPORT_MASTER_DB = "jdbc:mysql://127.0.0.1/report?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_REPORT_MASTER_DB = "root";
	public static String DBPASS_REPORT_MASTER_DB = "123456";
	
	public static String DBURL_REPORT_SLAVE_DB = "jdbc:mysql://127.0.0.1/report?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_REPORT_SLAVE_DB = "root";
	public static String DBPASS_REPORT_SLAVE_DB = "123456";
	
	public static String DBURL_POSBILLSLAVE_DB = "jdbc:mysql://127.0.0.1/posbill?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String NAME_POSBILLSLAVE_DB = "root";
	public static String PASS_POSBILLSLAVE_DB = "1234";
	
    //posbill_analyse
	public static String DBURL_POSBILLANALYSESLAVE_DB = "jdbc:mysql://192.168.51.159/posbill_analyse?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	 public static String NAME_POSBILLANALYSESLAVE_DB = "analyse_reader";
	 public static String PASS_POSBILLANALYSESLAVE_DB = "shuabao@20170522";
	
	public static String DBURL_POSBILLMASTER_DB = "jdbc:mysql://192.168.51.152/posbill?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String NAME_POSBILLMASTER_DB = "agentuser";
	public static String PASS_POSBILLMASTER_DB = "www.yeahka.com";

	/*public static String DBURL_POSBILL_ACCOUNTING_SLAVE_DB = "jdbc:mysql://10.8.18.102/posbill_accounting?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String NAME_POSBILL_ACCOUNTING_SLAVE_DB = "root";
	public static String PASS_POSBILL_ACCOUNTING_SLAVE_DB = "1234";*/

	public static String DBURL_POSBILL_ACCOUNTING_SLAVE_DB = "jdbc:mysql://192.168.51.75/posbill_accounting?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String NAME_POSBILL_ACCOUNTING_SLAVE_DB = "selectuser";
	public static String PASS_POSBILL_ACCOUNTING_SLAVE_DB = "www.yeahka.com";

	public static String DBURL_POSBILL_HIS_DB = "jdbc:mysql://127.0.0.1:3307/posbill?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String NAME_POSBILL_HIS_DB = "root";
	public static String PASS_POSBILL_HIS_DB = "1234";
	
	public static String DBURL_LEPOSROUTE_SLAVE_DB = "jdbc:mysql://127.0.0.1/leposroute?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_LEPOSROUTE_SLAVE_DB = "root";
	public static String DBPASS_LEPOSROUTE_SLAVE_DB = "1234";
	
	public static String DBURL_BIZ_DB = "jdbc:mysql://127.0.0.1/biz?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_BIZ_DB = "root";
	public static String DBPASS_BIZ_DB = "1234";
	
	//测试db
	public static String DBURL_TSET_DB = "jdbc:mysql://127.0.0.1/lepos_business?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_TSET_DB = "root";
	public static String DBPASS_TSET_DB = "1234";
	
	public static String DBURL_TSET_POSBILL_DB = "jdbc:mysql://127.0.0.1/lepos_business?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_TSET_POSBILL_DB = "root";
	public static String DBPASS_TSET_POSBILL_DB = "1234";
	
	public static String DBURL_POSBILL_PROFIT_SLAVE_DB = "jdbc:mysql://127.0.0.1/posbill?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String NAME_POSBILL_PROFIT_SLAVE_DB = "root";
	public static String PASS_POSBILL_PROFIT_SLAVE_DB = "1234";


    public static String DBURL_DEdUCTPAY_DB = "jdbc:mysql://192.168.33.107/deductpay?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    public static String DBNAME_DEdUCTPAY_DB = "root";
    public static String DBPASS_DEdUCTPAY_DB = "1234";
    
    public static String DBURL_AUTOREONCILE_DB = "jdbc:mysql://192.168.33.107/autoreconcile?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    public static String DBNAME_AUTOREONCILE_DB = "root";
    public static String DBPASS_AUTOREONCILE_DB = "1234";
    
    public static String DBURL_COPY_DB = "jdbc:mysql://192.168.51.52/lepos?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    public static String DBNAME_COPY_DB = "selectuser";
    public static String DBPASS_COPY_DB = "selectuser";

    public static String DBURL_PAYBILLSTATISTICS_SLAVE_DB = "jdbc:mysql://192.168.51.159/leposstat?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_PAYBILLSTATISTICS_SLAVE_DB = "mysqluser";
	public static String DBPASS_PAYBILLSTATISTICS_SLAVE_DB = "'worEywuAqBweiuBry762_46+234jk(sdnfs!134927!'";

    public static String DBURL_COLLECTPAY_SLAVE_DB = "jdbc:mysql://192.168.51.141/collectpay?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    public static String DBNAME_COLLECTPAY_SLAVE_DB = "selectuser";
    public static String DBPASS_COLLECTPAY_SLAVE_DB = "www.yeahka.com";

	public static String DBURL_LEPOSWEB_REPORT_SLAVE_DB = "jdbc:mysql://192.168.51.152/leposweb?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_LEPOSWEB_REPORT_SLAVE_DB = "web";
	public static String DBPASS_LEPOSWEB_REPORT_SLAVE_DB = "lpmish";
	
	public static String DBURL_SMS_SLAVE_DB = "jdbc:mysql://172.20.51.22/sms?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_SMS_SLAVE_DB = "webzx_read";
	public static String DBPASS_SMS_SLAVE_DB = "v08hLq!i";

	public static String DBURL_SMS_MASTER_DB = "jdbc:mysql://192.168.51.131/sms?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_SMS_MASTER_DB = "smswriter";
	public static String DBPASS_SMS_MASTER_DB = "YurQen7q4Kpyh4A";
	
    public static String DBURL_BANKPAY_SLAVE_DB = "jdbc:mysql://10.8.18.224/bankpay?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    public static String DBNAME_BANKPAY_SLAVE_DB = "root";
    public static String DBPASS_BANKPAY_SLAVE_DB = "1234";
    
    public static String DBURL_PLATFORM2_MASTER_DB = "";
	public static String DBNAME_PLATFORM2_MASTER_DB = "";
	public static String DBPASS_PLATFORM2_MASTER_DB = "";
	
	public static String DBURL_PLATFORM2_SLAVE_DB = "";
	public static String DBNAME_PLATFORM2_SLAVE_DB = "";
	public static String DBPASS_PLATFORM2_SLAVE_DB = "";

	public static String DBURL_CUPSSTATISTICS_SLAVE_DB = "jdbc:mysql://192.168.51.202:3308/cups_statistics?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_CUPSSTATISTICS_SLAVE_DB = "bocheck";
	public static String DBPASS_CUPSSTATISTICS_SLAVE_DB = "xdvIdku6X5g5aqp5zi7ItA==";
	
	public static String DBURL_ASO_MASTER_DB = "jdbc:mysql://127.0.0.1/aso?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_ASO_MASTER_DB = "root";
	public static String DBPASS_ASO_MASTER_DB = "123456";
	
	public static String DBURL_ASO_SLAVE_DB = "jdbc:mysql://127.0.0.1/aso?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_ASO_SLAVE_DB = "root";
	public static String DBPASS_ASO_SLAVE_DB = "123456";
	
	public static String DBURL_O2OTEST_SLAVE_DB = "jdbc:mysql://192.168.33.107/o2o?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_O2OTEST_SLAVE_DB = "root";
	public static String DBPASS_O2OTEST_SLAVE_DB = "1234";
	
	public static String DBURL_LEPOSBUSINESSTEST_DB = "jdbc:mysql://192.168.33.107/lepos_business?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
	public static String DBNAME_LEPOSBUSINESSTEST_DB = "root";
	public static String DBPASS_LEPOSBUSINESSTEST_DB = "1234";
	
	public static String DBURL_POSBILLSLAVE_DB_NEW = "jdbc:mysql://10.8.18.213/posbill?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
    public static String NAME_POSBILLSLAVE_DB_NEW = "selectuser";
    public static String PASS_POSBILLSLAVE_DB_NEW = "www.yeahka.com";
	
	public static String MEMCACHEIP = "127.0.0.1";
	public static String MEMCACHEPORT = "10001";
	public static String MEMCACHETIMEOUT = "3600";
	public static Map<String,String> PAYCHANNELNAME = new HashMap<String,String>();
	
	public static String NCICC_LICENSE_PATH = "/usr/local/test_tomcat/bin/license.txt";
	public static String LESHUAKEY = "99200781899517887019790697170012";
	public static String LESHUACGI = "http://pos.yeahka.com:8077/cgi-bin/";
	public static String LEPOSCGI = "http://192.168.21.244/cgi-bin/";
	
	public static String WEBDB = "lepos";
	public static String ADMINDB = "lepos";
	public static String BUSINESSDB = "lepos_business";
	public static String O2ODB = "o2o";
	
	public static String REDISHOST = "192.168.11.160";
	public static int REDISPORT = 6279;
	public static String REDISPASS = "www.yeahka.com";
	public static int REDISMAXTOTAL = 5000;
	public static int REDISMAXTIMEWAITMILLIS = 2000;
	public static boolean REDISTESTONBORROW = true;
	public static int REDISMAXIDLE = 5;
	
	public static Map<String,String> PAYCHANNELTABLE = new HashMap<String,String>();
	public static Map<String,String> PAYCHANNELORDERNAME = new HashMap<String,String>();
	public static String[] PAYCHANNELORDERKEY= {"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"
		,"16","17","18","19","20","21","22","23","24","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54"};
	//public static String[] PAYCHANNELORDERKEY= {"35"};
	public static String HISTORYDBDATE = "2018-03-01";
	
	//33.107上的ykpay
	public static String YKPAYDB = "ykpay";
	public static String YKPAYADMINDB = "ykadmin";
	
	public static String SHUABAOMERCHANTID_VIPCOMBO = "0000000018"; //正式:8406206031 //测试:0000000018
	public static String FASTPAYCGI = "http://192.168.21.241/cgi-bin/lepos_deduct_payment.cgi";//测试192.168.21.241
	public static String VIPMERCHANTCGI = "http://10.8.18.102/cgi-bin/";//测试环境:10.8.18.102,预发布:192.168.21.248 ,正式环境是settlement.yeahka.com
	public static String QUERYPAYCARDCGI = "http://192.168.33.107/cgi-bin/";//测试环境:http://192.168.33.107/cgi-bin ,正式环境:http://mobilepos.yeahka.com/cgi-bin/
	public static String QUERYPAYCARD_KEY = "wwwyeahkacom20160411000000000000"; //测试:wwwyeahkacom20160411000000000000  ,正式:yeahka20150403yahdfengansfjahjk2
	
	public static final String CUSTOMER_SERVICE_TEL = "0755-66835588";
	public static final String DOWNLOAD_SHORT_URL = "http://t.cn/RcNR5nG";

	public static String MerchantIdCGI = "http://mobilepos.yeahka.com";
	
	public static String POSSBILLCGI = "http://10.8.18.102/cgi-bin/";
	
	public static String DELAY_INSURE_CGI = "http://testlepos.yeahka.com:43102/cgi-bin/";

    /** 加解密服务器地址 ,默认为连接正式环境*/
    public static String ENCDECCGI = "http://192.168.11.210/cgi-bin/crypt.cgi";
	
	public static String WEB_ADDR = "http://pos.lepass.cn/";
	
	static {
		PAYCHANNELNAME.put("0", "银联");
		PAYCHANNELNAME.put("1", "财付通");
		PAYCHANNELNAME.put("2", "银盛");
		PAYCHANNELNAME.put("3", "中信银行（收单通道）");
		PAYCHANNELNAME.put("4", "VIPOS");
		PAYCHANNELNAME.put("5", "商联商用");
		PAYCHANNELNAME.put("6", "微信");
		PAYCHANNELNAME.put("7", "中国银行");
		PAYCHANNELNAME.put("8", "支付宝");
		PAYCHANNELNAME.put("9", "腾付通");
		PAYCHANNELNAME.put("10", "快钱");
		PAYCHANNELNAME.put("11", "建设银行");
		PAYCHANNELNAME.put("12", "工商银行");
		PAYCHANNELNAME.put("13", "农业银行");
		PAYCHANNELNAME.put("14", "银联CUPS");
		PAYCHANNELNAME.put("15", "深银联vipos");
		PAYCHANNELNAME.put("16", "工行新接口（本代本）");
		PAYCHANNELNAME.put("17", "汇付");
		PAYCHANNELNAME.put("18", "银联全渠道");
		PAYCHANNELNAME.put("19", "民生");
		//PAYCHANNELNAME.put("20", "海科");
		PAYCHANNELNAME.put("20", "海科通道（线上）");
		PAYCHANNELNAME.put("23", "海科通道（线下）");
		PAYCHANNELNAME.put("21", "财付通代扣");
		PAYCHANNELNAME.put("22", "ChinaPay代扣");
		PAYCHANNELNAME.put("24", "苏州嘉融");
		PAYCHANNELNAME.put("25", "中付");
		PAYCHANNELNAME.put("26", "工行新接口（本代他）");
		PAYCHANNELNAME.put("27", "民生直连");// 民生直连 
		PAYCHANNELNAME.put("28", "苏州嘉融-摩宝 ");// 苏州嘉融-摩宝 
		PAYCHANNELNAME.put("29", "民生银商");// 民生银商
		PAYCHANNELNAME.put("30", "苏州嘉融-快钱 ");// 苏州嘉融快钱
		PAYCHANNELNAME.put("31", "SZJRYL");// 苏州嘉融-元联
		PAYCHANNELNAME.put("32", "秒付通道");// 秒付通道
		PAYCHANNELNAME.put("33", "易宝Kpos");// 易宝Kpos
		PAYCHANNELNAME.put("34", "支付宝新接口");// 支付宝
		PAYCHANNELNAME.put("35", "微信转清");// 微信转清
		PAYCHANNELNAME.put("36", "北京合资银联");// 北京合资银联
		PAYCHANNELNAME.put("37", "民生支付宝");// 民生支付宝
		PAYCHANNELNAME.put("38", "中付新通道");//  中付新通道
		PAYCHANNELNAME.put("39", "CUPS乐刷");// CUPS乐刷
		PAYCHANNELNAME.put("40", "CUPS北京合资");// CUPS北京合资
		PAYCHANNELNAME.put("41", "民生微信");// 民生微信
		PAYCHANNELNAME.put("42", "兴业微信");// 兴业微信
		PAYCHANNELNAME.put("43", "兴业支付宝");// 兴业支付宝
		PAYCHANNELNAME.put("44", "中信微信");// 中信微信
		PAYCHANNELNAME.put("45", "中信支付宝");// 中信支付宝
		PAYCHANNELNAME.put("46", "深结算支付宝");// 深结算支付宝
		PAYCHANNELNAME.put("47", "艾米支付(刷卡)");// 艾米支付
		PAYCHANNELNAME.put("48", "浦发微信");  // 浦发微信
		PAYCHANNELNAME.put("49", "快捷支付");  // 快捷支付
		PAYCHANNELNAME.put("50", "浦发支付宝");  // 浦发支付宝
		PAYCHANNELNAME.put("51", "银联CUPS携机入网专用");//携机入网通道
		PAYCHANNELNAME.put("52", "广州银嘉");//广州银嘉
		PAYCHANNELNAME.put("53", "华付通微信");//华付通微信
		PAYCHANNELNAME.put("54", "华付通支付宝");//华付通支付宝
		PAYCHANNELNAME.put("65", "银联快捷");
		PAYCHANNELNAME.put("71", "银联封顶快捷");
		PAYCHANNELNAME.put("1032", "山东快捷");
		PAYCHANNELNAME.put("99", "光大银行（打款）");
		PAYCHANNELNAME.put("100", "民生银行（打款）");
		PAYCHANNELNAME.put("101", "中信银行（打款）");
		
		PAYCHANNELTABLE.put("0", "t_unionpay_pos_terminal");
		PAYCHANNELTABLE.put("1", "t_tenpay_pos_terminal");
		PAYCHANNELTABLE.put("2", "t_yinsheng_pos_terminal");
		PAYCHANNELTABLE.put("3", "t_zxpay_pos_terminal");
		PAYCHANNELTABLE.put("4", "t_vipos_pos_terminal");
		PAYCHANNELTABLE.put("5", "t_slsypay_pos_terminal");   
		PAYCHANNELTABLE.put("6", "t_wxpay_pos_terminal");
		PAYCHANNELTABLE.put("7", "t_bocpay_pos_terminal");
		PAYCHANNELTABLE.put("8", "t_alipay_pos_terminal");
		PAYCHANNELTABLE.put("9", "t_tftpay_pos_terminal");
		PAYCHANNELTABLE.put("10", "t_kuaiqian_pos_terminal");
		PAYCHANNELTABLE.put("11", "t_cbcpay_pos_terminal");
		PAYCHANNELTABLE.put("12", "t_icbcpay_pos_terminal");
		PAYCHANNELTABLE.put("13", "t_abcpay_pos_terminal");
		PAYCHANNELTABLE.put("14", "t_upmp_pos_terminal");
		PAYCHANNELTABLE.put("15", "t_newvipos_pos_terminal");
		PAYCHANNELTABLE.put("16", "t_icbcnew_pos_terminal");
		PAYCHANNELTABLE.put("17", "t_hfpay_pos_terminal");
		PAYCHANNELTABLE.put("18", "t_acppay_pos_terminal"); 
		PAYCHANNELTABLE.put("19", "t_minsheng_pos_terminal");
		//PAYCHANNELTABLE.put("20", "t_hkrt_pos_terminal");
		PAYCHANNELTABLE.put("20", "t_hkrt_mpos_terminal");
		PAYCHANNELTABLE.put("23", "t_hkrt_pos_terminal");
		PAYCHANNELTABLE.put("24", "t_szjr_pos_terminal");
		PAYCHANNELTABLE.put("21", "t_cft_deduct_terminal");
		PAYCHANNELTABLE.put("22", "t_chinapay_deduct_terminal");
		PAYCHANNELTABLE.put("25", "t_zhongfu_pos_terminal");
		PAYCHANNELTABLE.put("26", "t_icbcbdt_pos_terminal");
		PAYCHANNELTABLE.put("27", "t_msdyf_pos_terminal");
		PAYCHANNELTABLE.put("28", "t_szjrmb_pos_terminal");
		PAYCHANNELTABLE.put("29", "t_msys_pos_terminal");
		PAYCHANNELTABLE.put("30", "t_szjrkq_pos_terminal");
		PAYCHANNELTABLE.put("31", "t_szjryl_pos_terminal");
		PAYCHANNELTABLE.put("32", "t_miaofu_pos_terminal");
		PAYCHANNELTABLE.put("33", "t_ybkpos_pos_terminal");
		PAYCHANNELTABLE.put("34", "t_alipay2_pos_terminal");
		PAYCHANNELTABLE.put("35", "t_wxpay3_pos_terminal");
		PAYCHANNELTABLE.put("36", "t_upmp_bjhz_terminal");
		PAYCHANNELTABLE.put("37", "t_mssm_pos_terminal");
		PAYCHANNELTABLE.put("38", "t_zfnew_pos_terminal");
		PAYCHANNELTABLE.put("39", "t_cups_pos_terminal");
		PAYCHANNELTABLE.put("40", "t_cups_bjhz_terminal");
		PAYCHANNELTABLE.put("41", "t_ms_weixin_terminal");
		PAYCHANNELTABLE.put("42", "t_xy_weixin_terminal");  // 兴业微信
		PAYCHANNELTABLE.put("43", "t_xy_alipay_terminal");// 兴业支付宝
		PAYCHANNELTABLE.put("44", "t_zx_weixin_terminal");  // 中信微信
		PAYCHANNELTABLE.put("45", "t_zx_alipay_terminal");// 中信支付宝
		PAYCHANNELTABLE.put("46", "t_sjj_alipay_terminal");// 深结算支付宝
		PAYCHANNELTABLE.put("47", "t_impay_pos_terminal");// 艾米支付
		PAYCHANNELTABLE.put("48", "t_pf_weixin_terminal");// 浦发微信
		PAYCHANNELTABLE.put("49", "t_qpay_pos_terminal");// 快捷支付
		PAYCHANNELTABLE.put("50", "t_pf_alipay_terminal");// 浦发支付宝
		PAYCHANNELTABLE.put("51", "t_cups_pos_terminal");  //携机入网
		PAYCHANNELTABLE.put("52", "t_yjpay_pos_terminal");  //广州银嘉
		PAYCHANNELTABLE.put("53", "t_hft_weixin_terminal");  //华付通微信
		PAYCHANNELTABLE.put("54", "t_hft_alipay_terminal");  //华付通支付宝
		
		PAYCHANNELORDERNAME.put("0", "unionpayposterminal");
		PAYCHANNELORDERNAME.put("1", "tenpayposterminal");
		PAYCHANNELORDERNAME.put("2", "yinshengposterminal");
		PAYCHANNELORDERNAME.put("3", "zxpayposterminal");
		PAYCHANNELORDERNAME.put("4", "viposposterminal");
		PAYCHANNELORDERNAME.put("5", "slsypayposterminal");   
		PAYCHANNELORDERNAME.put("6", "wxpayposterminal");
		PAYCHANNELORDERNAME.put("7", "bocpayposterminal");
		PAYCHANNELORDERNAME.put("8", "alipayposterminal");
		PAYCHANNELORDERNAME.put("9", "tftpayposterminal");
		PAYCHANNELORDERNAME.put("10", "kuaiqianposterminal");
		PAYCHANNELORDERNAME.put("11", "cbcpayposterminal");
		PAYCHANNELORDERNAME.put("12", "icbcpayposterminal");
		PAYCHANNELORDERNAME.put("13", "abcpayposterminal");
		PAYCHANNELORDERNAME.put("14", "upmpposterminal");
		PAYCHANNELORDERNAME.put("15", "newviposposterminal");
		PAYCHANNELORDERNAME.put("16", "icbcnewposterminal");
		PAYCHANNELORDERNAME.put("17", "hfpayposterminal");
		PAYCHANNELORDERNAME.put("18", "acppayposterminal"); 
		PAYCHANNELORDERNAME.put("19", "minshengposterminal");
		PAYCHANNELORDERNAME.put("20", "hkrtmposterminal");
		PAYCHANNELORDERNAME.put("23", "hkrtposterminal");
		PAYCHANNELORDERNAME.put("24", "szjrposterminal");
		PAYCHANNELORDERNAME.put("21", "cftdeductterminal");
		PAYCHANNELORDERNAME.put("22", "chinapaydeductterminal");
		PAYCHANNELORDERNAME.put("25", "zhongfuposterminal");
		PAYCHANNELORDERNAME.put("26", "icbcbdtposterminal");
		PAYCHANNELORDERNAME.put("27", "msdyfposterminal");
		PAYCHANNELORDERNAME.put("28", "szjrmbposterminal");
		PAYCHANNELORDERNAME.put("29", "msysposterminal");
		PAYCHANNELORDERNAME.put("30", "szjrkqposterminal");
		PAYCHANNELORDERNAME.put("31", "szjrylposterminal");
		PAYCHANNELORDERNAME.put("32", "miaofuposterminal");
		PAYCHANNELORDERNAME.put("33", "ybkposposterminal");
		PAYCHANNELORDERNAME.put("34", "alipay2posterminal");
		PAYCHANNELORDERNAME.put("35", "wxpay3posterminal");
		PAYCHANNELORDERNAME.put("36", "upmpbjhzterminal");
		PAYCHANNELORDERNAME.put("37", "mssmposterminal");
		PAYCHANNELORDERNAME.put("38", "zfnewposterminal");
		PAYCHANNELORDERNAME.put("39", "cupsposterminal");
		PAYCHANNELORDERNAME.put("40", "cupsbjhzterminal");
		PAYCHANNELORDERNAME.put("41", "msweixinterminal");
		PAYCHANNELORDERNAME.put("42", "xyweixinterminal");  // 兴业微信
		PAYCHANNELORDERNAME.put("43", "xyalipayterminal");// 兴业支付宝
		PAYCHANNELORDERNAME.put("44", "zxweixinterminal");  // 中信微信
		PAYCHANNELORDERNAME.put("45", "zxalipayterminal");// 中信支付宝t_sjj_alipay_terminal
		PAYCHANNELORDERNAME.put("46", "sjjalipayterminal");// 深结算支付宝
		PAYCHANNELORDERNAME.put("47", "impayposterminal");// 艾米支付
		PAYCHANNELORDERNAME.put("48", "pfweixinterminal");// 浦发微信
		PAYCHANNELORDERNAME.put("49", "qpayposterminal");// 快捷支付
		PAYCHANNELORDERNAME.put("50", "pfalipayterminal");// 浦发支付宝
		PAYCHANNELORDERNAME.put("51", "cupsposterminal");// 携机入网
		PAYCHANNELORDERNAME.put("52", "yjpayposterminal");// 广州银嘉
		PAYCHANNELORDERNAME.put("53", "hftweixinterminal");// 华付通微信
		PAYCHANNELORDERNAME.put("54", "hftalipayterminal");// 华付通支付宝
		if (File.separator.equals("/")) {
			IS_TEST = false;
			try {
				java.io.InputStream is = new java.io.FileInputStream(propertiesName);
				prop.load(is);
            } catch (Exception e) {
                File file = new File(Config.class.getResource("/").getPath());
                file = file.getParentFile().getParentFile().getParentFile().getParentFile();
                file = new File(new File(file, "bin"), propertiesName);
                try {
                    java.io.InputStream is = new java.io.FileInputStream(file);
                    prop.load(is);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
		}

		if (IS_TEST) {
            String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath()+ "myc3p0.properties";
            File file = new File(filePath);
            try {
                java.io.InputStream is = new java.io.FileInputStream(file);
                prop.load(is);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

		}

        if (StringUtil.isNotEmpty(BaseConfig.prop.getProperty("ENVIRONMENT_TYPE"))) {
            Config.ENVIRONMENT_TYPE = BaseConfig.prop.getProperty("ENVIRONMENT_TYPE");
        }
        if (StringUtil.isNotEmpty(BaseConfig.prop.getProperty("ENCDECCGI"))) {
            Config.ENCDECCGI = BaseConfig.prop.getProperty("ENCDECCGI");
        }
        REDISHOST = BaseConfig.prop.getProperty("redis.host");
        REDISPORT = Integer.parseInt(BaseConfig.getString("redis.port", "6279"));
        REDISPASS = BaseConfig.prop.getProperty("redis.pass");

        DBURL_MASTER_DB = prop.getProperty("DBURL_MASTER_DB");
        DBNAME_MASTER_DB = prop.getProperty("DBNAME_MASTER_DB");
        DBPASS_MASTER_DB = prop.getProperty("DBPASS_MASTER_DB");

        DBURL_SLAVE_DB = prop.getProperty("DBURL_SLAVE_DB");
        DBNAME_SLAVE_DB = prop.getProperty("DBNAME_SLAVE_DB");
        DBPASS_SLAVE_DB = prop.getProperty("DBPASS_SLAVE_DB");

        DBURL_LEPOSX_SLAVE_DB = prop.getProperty("DBURL_LEPOSX_SLAVE_DB");
        DBNAME_LEPOSX_SLAVE_DB = prop.getProperty("DBNAME_LEPOSX_SLAVE_DB");
        DBPASS_LEPOSX_SLAVE_DB = prop.getProperty("DBPASS_LEPOSX_SLAVE_DB");

		Config.DBURL_LEPOSX1_SLAVE_DB = BaseConfig.prop.getProperty("DBURL_LEPOSX1_SLAVE_DB");
		Config.DBNAME_LEPOSX1_SLAVE_DB =  BaseConfig.prop.getProperty("DBNAME_LEPOSX1_SLAVE_DB");
		Config.DBPASS_LEPOSX1_SLAVE_DB = BaseConfig.prop.getProperty("DBPASS_LEPOSX1_SLAVE_DB");
		
		DBURL_LEPOS_REPLICAT_SLAVE_DB = prop.getProperty("DBURL_LEPOS_REPLICAT_SLAVE_DB");
        DBNAME_LEPOS_REPLICAT_SLAVE_DB = prop.getProperty("DBNAME_LEPOS_REPLICAT_SLAVE_DB");
        DBPASS_LEPOS_REPLICAT_SLAVE_DB = prop.getProperty("DBPASS_LEPOS_REPLICAT_SLAVE_DB");

		DBURL_AUTOREONCILE_HISTORY_DB = prop.getProperty("DBURL_AUTOREONCILE_HISTORY_DB");
		DBNAME_AUTOREONCILE_HISTORY_DB= prop.getProperty("DBNAME_AUTOREONCILE_HISTORY_DB");
		DBPASS_AUTOREONCILE_HISTORY_DB = prop.getProperty("DBPASS_AUTOREONCILE_HISTORY_DB");

		DBURL_ADMIN_HISTORY_DB = prop.getProperty("DBURL_ADMIN_HISTORY_DB");
		DBNAME_ADMIN_HISTORY_DB= prop.getProperty("DBNAME_ADMIN_HISTORY_DB");
		DBPASS_ADMIN_HISTORY_DB = prop.getProperty("DBPASS_ADMIN_HISTORY_DB");
        
        DBURL_HIS_DB = prop.getProperty("DBURL_HIS_DB");
        DBNAME_HIS_DB = prop.getProperty("DBNAME_HIS_DB");
        DBPASS_HIS_DB = prop.getProperty("DBPASS_HIS_DB");

        DBURL_PAY_DB = prop.getProperty("DBURL_PAY_DB");
        DBNAME_PAY_DB = prop.getProperty("DBNAME_PAY_DB");
        DBPASS_PAY_DB = prop.getProperty("DBPASS_PAY_DB");

        DBURL_YKADMIN_DB = prop.getProperty("DBURL_YKADMIN_DB");
        DBNAME_YKADMIN_DB = prop.getProperty("DBNAME_YKADMIN_DB");
        DBPASS_YKADMIN_DB = prop.getProperty("DBPASS_YKADMIN_DB");

        DBURL_BUSINESS_DB = prop.getProperty("DBURL_BUSINESS_DB");
        DBNAME_BUSINESS_DB = prop.getProperty("DBNAME_BUSINESS_DB");
        DBPASS_BUSINESS_DB = prop.getProperty("DBPASS_BUSINESS_DB");

        DBURL_BUSINESS_SLAVE_DB = prop.getProperty("DBURL_BUSINESS_SLAVE_DB");
        DBNAME_BUSINESS_SLAVE_DB = prop.getProperty("DBNAME_BUSINESS_SLAVE_DB");
        DBPASS_BUSINESS_SLAVE_DB = prop.getProperty("DBPASS_BUSINESS_SLAVE_DB");

        DBURL_O2O_MASTER_DB = prop.getProperty("DBURL_O2O_MASTER_DB");
        DBNAME_O2O_MASTER_DB = prop.getProperty("DBNAME_O2O_MASTER_DB");
        DBPASS_O2O_MASTER_DB = prop.getProperty("DBPASS_O2O_MASTER_DB");

        DBURL_O2O_SLAVE_DB = prop.getProperty("DBURL_O2O_SLAVE_DB");
        DBNAME_O2O_SLAVE_DB = prop.getProperty("DBNAME_O2O_SLAVE_DB");
        DBPASS_O2O_SLAVE_DB = prop.getProperty("DBPASS_O2O_SLAVE_DB");


        DBURL_ADMIN_SLAVE_DB = prop.getProperty("DBURL_ADMIN_SLAVE_DB");
        DBNAME_ADMIN_SLAVE_DB = prop.getProperty("DBNAME_ADMIN_SLAVE_DB");
        DBPASS_ADMIN_SLAVE_DB = prop.getProperty("DBPASS_ADMIN_SLAVE_DB");

        DBURL_YkPay_SLAVE_DB = prop.getProperty("DBURL_YKPAY_SLAVE_DB");
        DBNAME_YkPay_SLAVE_DB = prop.getProperty("DBNAME_YKPAY_SLAVE_DB");
        DBPASS_YkPay_SLAVE_DB = prop.getProperty("DBPASS_YKPAY_SLAVE_DB");

        DBURL_COLLECTPAY_SLAVE_DB = prop.getProperty("DBURL_COLLECTPAY_SLAVE_DB");
        DBNAME_COLLECTPAY_SLAVE_DB = prop.getProperty("DBNAME_COLLECTPAY_SLAVE_DB");
        DBPASS_COLLECTPAY_SLAVE_DB = prop.getProperty("DBPASS_COLLECTPAY_SLAVE_DB");

        DBURL_YkPay_MASTER_DB = prop.getProperty("DBURL_YKPAY_MASTER_DB");
        DBNAME_YkPay_MASTER_DB = prop.getProperty("DBNAME_YKPAY_MASTER_DB");
        DBPASS_YkPay_MASTER_DB = prop.getProperty("DBPASS_YKPAY_MASTER_DB");

        DBURL_WEB_MASTER_DB = prop.getProperty("DBURL_WEB_MASTER_DB");
        DBNAME_WEB_MASTER_DB = prop.getProperty("DBNAME_WEB_MASTER_DB");
        DBPASS_WEB_MASTER_DB = prop.getProperty("DBPASS_WEB_MASTER_DB");

        DBURL_WEB_SLAVE_DB = prop.getProperty("DBURL_WEB_SLAVE_DB");
        DBNAME_WEB_SLAVE_DB = prop.getProperty("DBNAME_WEB_SLAVE_DB");
        DBPASS_WEB_SLAVE_DB = prop.getProperty("DBPASS_WEB_SLAVE_DB");

        URL_LEPOSWEB = prop.getProperty("URL_LEPOSWEB");
        NAME_LEPOSWEB = prop.getProperty("NAME_LEPOSWEB");
        PASS_LEPOSWEB = prop.getProperty("PASS_LEPOSWEB");

        URL_LEPOSWEB_SLAVE = prop.getProperty("URL_LEPOSWEB_SLAVE");
        NAME_LEPOSWEB_SLAVE= prop.getProperty("NAME_LEPOSWEB_SLAVE");
        PASS_LEPOSWEB_SLAVE = prop.getProperty("PASS_LEPOSWEB_SLAVE");

        DBURL_REPORT_MASTER_DB = prop.getProperty("DBURL_REPORT_MASTER_DB");
        DBNAME_REPORT_MASTER_DB= prop.getProperty("DBNAME_REPORT_MASTER_DB");
        DBPASS_REPORT_MASTER_DB = prop.getProperty("DBPASS_REPORT_MASTER_DB");

        DBURL_REPORT_SLAVE_DB = prop.getProperty("DBURL_REPORT_SLAVE_DB");
        DBNAME_REPORT_SLAVE_DB= prop.getProperty("DBNAME_REPORT_SLAVE_DB");
        DBPASS_REPORT_SLAVE_DB = prop.getProperty("DBPASS_REPORT_SLAVE_DB");

        DBURL_POSBILLSLAVE_DB = prop.getProperty("DBURL_POSBILLSLAVE_DB");
        NAME_POSBILLSLAVE_DB = prop.getProperty("NAME_POSBILLSLAVE_DB");
        PASS_POSBILLSLAVE_DB = prop.getProperty("PASS_POSBILLSLAVE_DB");
        
        DBURL_POSBILL_PROFIT_SLAVE_DB = prop.getProperty("DBURL_POSBILL_PROFIT_SLAVE_DB");
        NAME_POSBILL_PROFIT_SLAVE_DB = prop.getProperty("NAME_POSBILL_PROFIT_SLAVE_DB");
        PASS_POSBILL_PROFIT_SLAVE_DB = prop.getProperty("PASS_POSBILL_PROFIT_SLAVE_DB");
        
        DBURL_POSBILLMASTER_DB = prop.getProperty("DBURL_POSBILLMASTER_DB");
        NAME_POSBILLMASTER_DB = prop.getProperty("NAME_POSBILLMASTER_DB");
        PASS_POSBILLMASTER_DB = prop.getProperty("PASS_POSBILLMASTER_DB");

		DBURL_POSBILL_ACCOUNTING_SLAVE_DB = prop.getProperty("DBURL_POSBILL_ACCOUNTING_SLAVE_DB");
		NAME_POSBILL_ACCOUNTING_SLAVE_DB = prop.getProperty("NAME_POSBILL_ACCOUNTING_SLAVE_DB");
		PASS_POSBILL_ACCOUNTING_SLAVE_DB = prop.getProperty("PASS_POSBILL_ACCOUNTING_SLAVE_DB");

        DBURL_POSBILL_HIS_DB = prop.getProperty("DBURL_POSBILL_HIS_DB");
        NAME_POSBILL_HIS_DB = prop.getProperty("NAME_POSBILL_HIS_DB");
        PASS_POSBILL_HIS_DB = prop.getProperty("PASS_POSBILL_HIS_DB");
        
        DBURL_TSET_DB = prop.getProperty("DBURL_TSET_DB");
        DBNAME_TSET_DB = prop.getProperty("DBNAME_TSET_DB");
        DBPASS_TSET_DB = prop.getProperty("DBPASS_TSET_DB");
        FASTPAYENVIRONMENT = prop.getProperty("FASTPAYENVIRONMENT");

        DBURL_TSET_POSBILL_DB = prop.getProperty("DBURL_TSET_POSBILL_DB");
        DBNAME_TSET_POSBILL_DB = prop.getProperty("DBNAME_TSET_POSBILL_DB");
        DBPASS_TSET_POSBILL_DB = prop.getProperty("DBPASS_TSET_POSBILL_DB");
        
        Config.DBURL_BANKPAY_SLAVE_DB = BaseConfig.prop.getProperty("DBURL_BANKPAY_SLAVE_DB");
        Config.DBNAME_BANKPAY_SLAVE_DB =  BaseConfig.prop.getProperty("DBNAME_BANKPAY_SLAVE_DB");
        Config.DBPASS_BANKPAY_SLAVE_DB = BaseConfig.prop.getProperty("DBPASS_BANKPAY_SLAVE_DB");

        Config.DBURL_LEPOSROUTE_SLAVE_DB = BaseConfig.prop.getProperty("DBURL_LEPOSROUTE_SLAVE_DB");
        Config.DBNAME_LEPOSROUTE_SLAVE_DB = BaseConfig.prop.getProperty("DBNAME_LEPOSROUTE_SLAVE_DB");
        Config.DBPASS_LEPOSROUTE_SLAVE_DB = BaseConfig.prop.getProperty("DBPASS_LEPOSROUTE_SLAVE_DB");

        Config.DBURL_DEdUCTPAY_DB = BaseConfig.prop.getProperty("DBURL_DEdUCTPAY_DB");
        Config.DBNAME_DEdUCTPAY_DB= BaseConfig.prop.getProperty("DBNAME_DEdUCTPAY_DB");
        Config.DBPASS_DEdUCTPAY_DB = BaseConfig.prop.getProperty("DBPASS_DEdUCTPAY_DB");

        Config.DBURL_AUTOREONCILE_DB = BaseConfig.prop.getProperty("DBURL_AUTOREONCILE_DB");
        Config.DBNAME_AUTOREONCILE_DB= BaseConfig.prop.getProperty("DBNAME_AUTOREONCILE_DB");
        Config.DBPASS_AUTOREONCILE_DB = BaseConfig.prop.getProperty("DBPASS_AUTOREONCILE_DB");

        Config.DBURL_COPY_DB = BaseConfig.prop.getProperty("DBURL_COPY_DB");
        Config.DBNAME_COPY_DB= BaseConfig.prop.getProperty("DBNAME_COPY_DB");
        Config.DBPASS_COPY_DB = BaseConfig.prop.getProperty("DBPASS_COPY_DB");

        DBURL_BIZ_DB = prop.getProperty("DBURL_BIZ_DB");
        DBNAME_BIZ_DB = prop.getProperty("DBNAME_BIZ_DB");
        DBPASS_BIZ_DB = prop.getProperty("DBPASS_BIZ_DB");

        Config.DBURL_PAYBILLSTATISTICS_SLAVE_DB = BaseConfig.prop.getProperty("DBURL_PAYBILLSTATISTICS_SLAVE_DB");
        Config.DBNAME_PAYBILLSTATISTICS_SLAVE_DB = BaseConfig.prop.getProperty("DBNAME_PAYBILLSTATISTICS_SLAVE_DB");
        Config.DBPASS_PAYBILLSTATISTICS_SLAVE_DB = BaseConfig.prop.getProperty("DBPASS_PAYBILLSTATISTICS_SLAVE_DB");

        Config.DBURL_PLATFORM2_MASTER_DB = BaseConfig.prop.getProperty("DBURL_PLATFORM2_DB");
        Config.DBNAME_PLATFORM2_MASTER_DB = BaseConfig.prop.getProperty("DBNAME_PLATFORM2_DB");
        Config.DBPASS_PLATFORM2_MASTER_DB = BaseConfig.prop.getProperty("DBPASS_PLATFORM2_DB");

        Config.DBURL_PLATFORM2_SLAVE_DB = BaseConfig.prop.getProperty("DBURL_PLATFORM2_SLAVE_DB");
        Config.DBNAME_PLATFORM2_SLAVE_DB = BaseConfig.prop.getProperty("DBNAME_PLATFORM2_SLAVE_DB");
        Config.DBPASS_PLATFORM2_SLAVE_DB = BaseConfig.prop.getProperty("DBPASS_PLATFORM2_SLAVE_DB");

        Config.DBURL_CUPSSTATISTICS_SLAVE_DB = BaseConfig.prop.getProperty("DBURL_CUPSSTATISTICS_SLAVE_DB");
        Config.DBNAME_CUPSSTATISTICS_SLAVE_DB =  BaseConfig.prop.getProperty("DBNAME_CUPSSTATISTICS_SLAVE_DB");
        Config.DBPASS_CUPSSTATISTICS_SLAVE_DB = BaseConfig.prop.getProperty("DBPASS_CUPSSTATISTICS_SLAVE_DB");
        
        Config.DBURL_POSBILLSLAVE_DB_NEW = BaseConfig.prop.getProperty("DBURL_POSBILLSLAVE_DB_NEW");
        Config.NAME_POSBILLSLAVE_DB_NEW = BaseConfig.prop.getProperty("NAME_POSBILLSLAVE_DB_NEW");
        Config.PASS_POSBILLSLAVE_DB_NEW = BaseConfig.prop.getProperty("PASS_POSBILLSLAVE_DB_NEW");
        
        DBURL_ASO_MASTER_DB = prop.getProperty("DBURL_ASO_MASTER_DB");
        DBNAME_ASO_MASTER_DB= prop.getProperty("DBNAME_ASO_MASTER_DB");
        DBPASS_ASO_MASTER_DB = prop.getProperty("DBPASS_ASO_MASTER_DB");

        DBURL_ASO_SLAVE_DB = prop.getProperty("DBURL_ASO_SLAVE_DB");
        DBNAME_ASO_SLAVE_DB= prop.getProperty("DBNAME_ASO_SLAVE_DB");
        DBPASS_ASO_SLAVE_DB = prop.getProperty("DBPASS_ASO_SLAVE_DB");
        
        IS_TEST = "true".equalsIgnoreCase(prop.getProperty("IS_TEST"));
        IS_JOB = "true".equalsIgnoreCase(prop.getProperty("IS_JOB"));


        MEMCACHEIP = prop.getProperty("MEMCACHEIP");
        MEMCACHEPORT = prop.getProperty("MEMCACHEPORT");
        MEMCACHETIMEOUT = prop.getProperty("MEMCACHETIMEOUT");
        LESHUACGI = prop.getProperty("LESHUACGI");
        LEPOSCGI = prop.getProperty("LEPOSCGI");
        NCICC_LICENSE_PATH = prop.getProperty("NCICC_LICENSE_PATH");
        ADMINDB = prop.getProperty("ADMINDB");

        POSSBILLCGI = prop.getProperty("POSSBILLCGI");

        DELAY_INSURE_CGI = prop.getProperty("DELAY_INSURE_CGI");

        WEB_ADDR = prop.getProperty("WEB_ADDR");

        SHUABAOMERCHANTID_VIPCOMBO = prop.getProperty("SHUABAOMERCHANTID_VIPCOMBO");
        FASTPAYCGI = prop.getProperty("FASTPAYCGI");
        VIPMERCHANTCGI = prop.getProperty("VIPMERCHANTCGI");
        QUERYPAYCARDCGI = prop.getProperty("QUERYPAYCARDCGI");
        QUERYPAYCARD_KEY = prop.getProperty("QUERYPAYCARD_KEY");

		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("dvice not find", e);
		}
	}
}
