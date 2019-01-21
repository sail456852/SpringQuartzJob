package msc.form;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/1/21<br/>
 * Time: 14:57<br/>
 * To change this template use File | Settings | File Templates.
 */
public class AliPayOrder {
    private static final long serialVersionUID = -8044525739313237726L;

    /** 正常:1 */
    public static final int ORDER_NORMAL_STATUS = 1;
    /** 关闭:2 */
    public static final int ORDER_CLOSE_STATUS = 2;
    /** 退款中:3 */
    public static final int ORDER_REFUND_STATUS = 3;

    /** 未支付：0 */
    public static final int UNPAY_STATUS = 0;
    /** 支付成功：1 */
    public static final int SUCCESS_PAY_STATUS = 1;
    /** 支付失败：2 */
    public static final int FAILED_PAY_STATUS = 2;

    private Integer f_id; //订单表主键
    private String f_account_id;
    //	private String f_sp_id; //账号的sp_id
    private String f_third_order_id; //前端唯一ID
    private String f_ls_order_id; //乐刷交易订单ID
    private String f_ali_order_id; //支付宝订单ID
    //	private Integer f_product_id;
    private Integer f_buy_num;
    private Integer f_amount;
    private String f_phone; //收货手机号
    private String f_receiver; //收货人
    private String f_address; //收货地址
    private Integer f_order_status;
    private Integer f_pay_status;
    private String f_pay_method;
    private Date f_create_time;
    private Date f_update_time;
    private String f_client_ip;
    private String f_goods_name;
    private Integer f_goods_id;


    public String getF_account_id() {
        return f_account_id;
    }
    public void setF_account_id(String f_account_id) {
        this.f_account_id = f_account_id;
    }
    //	public String getF_sp_id() {
//		return f_sp_id;
//	}
//	public void setF_sp_id(String f_sp_id) {
//		this.f_sp_id = f_sp_id;
//	}
    public String getF_third_order_id() {
        return f_third_order_id;
    }
    public void setF_third_order_id(String f_third_order_id) {
        this.f_third_order_id = f_third_order_id;
    }
    //	public Integer getF_product_id() {
//		return f_product_id;
//	}
//	public void setF_product_id(Integer f_product_id) {
//		this.f_product_id = f_product_id;
//	}
    public Integer getF_buy_num() {
        return f_buy_num;
    }
    public void setF_buy_num(Integer f_buy_num) {
        this.f_buy_num = f_buy_num;
    }
    public String getF_address() {
        return f_address;
    }
    public void setF_address(String f_address) {
        this.f_address = f_address;
    }
    public Integer getF_amount() {
        return f_amount;
    }
    public void setF_amount(Integer f_amount) {
        this.f_amount = f_amount;
    }
    public String getF_phone() {
        return f_phone;
    }
    public void setF_phone(String f_phone) {
        this.f_phone = f_phone;
    }
    public Integer getF_order_status() {
        return f_order_status;
    }
    public void setF_order_status(Integer f_order_status) {
        this.f_order_status = f_order_status;
    }
    public Integer getF_pay_status() {
        return f_pay_status;
    }
    public void setF_pay_status(Integer f_pay_status) {
        this.f_pay_status = f_pay_status;
    }
    public String getF_pay_method() {
        return f_pay_method;
    }
    public void setF_pay_method(String f_pay_method) {
        this.f_pay_method = f_pay_method;
    }
    public Date getF_create_time() {
        return f_create_time;
    }
    public void setF_create_time(Date f_create_time) {
        this.f_create_time = f_create_time;
    }
    public Date getF_update_time() {
        return f_update_time;
    }
    public void setF_update_time(Date f_update_time) {
        this.f_update_time = f_update_time;
    }
    public String getF_client_ip() {
        return f_client_ip;
    }
    public void setF_client_ip(String f_client_ip) {
        this.f_client_ip = f_client_ip;
    }
    public String getF_goods_name() {
        return f_goods_name;
    }
    public void setF_goods_name(String f_goods_name) {
        this.f_goods_name = f_goods_name;
    }
    public String getF_receiver() {
        return f_receiver;
    }
    public void setF_receiver(String f_receiver) {
        this.f_receiver = f_receiver;
    }
    public String getF_ls_order_id() {
        return f_ls_order_id;
    }
    public void setF_ls_order_id(String f_ls_order_id) {
        this.f_ls_order_id = f_ls_order_id;
    }
    public String getF_ali_order_id() {
        return f_ali_order_id;
    }
    public void setF_ali_order_id(String f_ali_order_id) {
        this.f_ali_order_id = f_ali_order_id;
    }
    public Integer getF_goods_id() {
        return f_goods_id;
    }
    public void setF_goods_id(Integer f_goods_id) {
        this.f_goods_id = f_goods_id;
    }
    public Integer getF_id() {
        return f_id;
    }
    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }

}
