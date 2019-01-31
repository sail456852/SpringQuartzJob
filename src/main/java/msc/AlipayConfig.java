package msc;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/31<br/>
 * Time: 16:37<br/>
 * To change this template use File | Settings | File Templates.
 * For backup purpose this worked in sandbox environment, so I saved it
 * corresponding to your onenote for more detail
 */
public class AlipayConfig {

    //    // 商户appid
//    public static String APPID = "2019013163195349"; // yuzhenAliPayTest
//    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC+vwtPJwF0epmU\n" +
            "xMByk5WBDfSKTD1zm4ZpY+DEhTa5AcdzU5xQ3gLWabyhseGISDFA0fvtz3IN6+PQ\n" +
            "myKa/uI6KxtF0MGu+fAJyGgvWzt4DQBTPUYLCsuH4pTIk2CtG1pooZF8XRoOBtZq\n" +
            "AlqRRLoEMw4hO8MJ1zPt0pNv8WnTvWS4Ox56NpqAwc+DkdurtAPt3E6SIsuLSuJM\n" +
            "O+XPDkEq4KnkWwoEXKThniV4h0tqN4DXfwppRSaIDQR+1CMLcl9LljW3p0acQ/jI\n" +
            "jI/7p01V3wxfjuUinu7YuF3MnVEL7qve+B7+rlHGX9IIkmHzIeUuX9Cr9pn7lcPL\n" +
            "KmYax60FAgMBAAECggEAA2k/NocoC4E1xedO2b2x6sDpIqACaVLasH/MwTbwp5ZU\n" +
            "jsdsADY4LsVtTib0NY+08cfPjY0sKPuS82QS1lrAiPHEZxc4n3xwwnwHAPHMunTh\n" +
            "EJwD6KakxSVUK9IW5IGcuEpLY8H2eVG9+99W50onWXPgSM/cyQxELXiakmHq1pKA\n" +
            "k6RDMMVypNOM28qmhIRX8gwmMJRk21uKpEY8CE75mAocOdgCLgKlnZSXOT/s1u5d\n" +
            "j3htmM8vQPo5uwwK4UxAujjKjkx7+S/04ZxqCidGjwXZsiMe94qcJxx9c97IRNo1\n" +
            "vFjVgF+ikOqeZUfzG4Hgqz9EVA7kveFYAn2lns8hgQKBgQDqofSY6k0GQdLc9UfN\n" +
            "FHQnVJ2TyE51rd3Jm2wUb6IV1aZtBDEJfGm/uOQr23UZ2J31m8R0tX8dg2rgQYRT\n" +
            "irqDx8n3z+ej0fsqQoaSLD0MSnt2jypP4Xhd5PO8aNWubv9g35BbKFbgYWujdAPo\n" +
            "9WIaXPc2KSICKQc/dHNcQpdaHQKBgQDQHfSlqfwOEeT38SB83IWohuBQ6LgQBYe+\n" +
            "vNo8SOwwyl7gRXbiYjLCKR5G+P9jnuEU4U0i+YAfsw3kLhI6rXeiLrcaSBFE796i\n" +
            "fsArjnjzp7IQIzfIOPFWCXh+XL0ewtZDcwx+jLNqSLxqA+FkI/pLAlnX0aKdxP7Q\n" +
            "Y3V8M8PqCQKBgD7eFkPCw9ChYnaB4jM4/8ZqDa7DRQKTC5Ixaswu/ScMkAM11E3O\n" +
            "YCdWywxVSRMbz3c3vXPoDZ9Czyg3ZdVcno0gwyT7N/3VDY9NM/GGmPTqIBqsUZIF\n" +
            "v9KovgYFsPuEEjlKrcs/C8gjUkwBkeqhlR+rOTHx44Lgq2h9M0vNK/ChAoGAZloF\n" +
            "IqBZ1PWZ/RJ6Xs35cet7UG9TbpW5r+WjErjuaARu6q9fC3Et821TdaYpu+28Vo+D\n" +
            "Zyr7hxuBPnImBvOe/YUqCePdkmDBxnq9ue4kr/gOS392hKL0ldKsjsC/ReyegaeI\n" +
            "QoiiCChjeWQVKjHDe7ZZRxnTdvIWO5hehEnVFTkCgYAVRu5BeiVcKgyAPLGIR42r\n" +
            "SQEgupOE3gMyt+yN00BH+WPIOcztuFz+SRH+ba6ngUc0Bps8l88VnBHvRho762dn\n" +
            "GBA31eDEuad7Ndo6n0hhJXM4lTcvZb6sJVONLIaqahhpzG/YHTPn/aC3DP18sdIl\n" +
            "nhYE9z6lkl6536NbYhUcQQ==";  // yuzhenAliPayTest & SANDBOX SHARED

    // SandBox environment start

    public static String APPID = "2016092500592250"; // SANDBOX


    // SandBox environment end


    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://wangyuzhen.iask.in/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://wangyuzhen.iask.in/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
    // 请求网关地址
//    public static String URL = "https://openapi.alipay.com/gateway.do";  // PRD
    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";  // SANDBOX
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrEVFMOSiNJXaRNKicQuQdsREraftDA9Tua3WNZwcpeXeh8Wrt+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
