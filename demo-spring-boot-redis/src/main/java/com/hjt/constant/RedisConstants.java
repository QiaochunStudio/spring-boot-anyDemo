package com.hjt.constant;

/**
 * @author hjt
 * @date:2022/8/21
 * redis持节化
 */
public class RedisConstants {
    /**
     * 验证码缓存
     * redis存 手机号码:CODE
     */
    public static final String PHONE_NUMBER_CODE = ":code";


    /***
     * 订单模块缓存
     * 书写规范
     */
    public static final String COLON = ":";
    /***
     * 订单对库存加锁的时间
     */
    public static final long MODULES_ORDER_PRODUCT_STOCK_TIME = 10L;

    /***
     * 防止重复提交设置的key过期时间为 1800s=30分钟
     */
    public static final long MODULES_ORDER_EXPIRE_TIME = 1800L;

    /***
     * 防止订单重复提交
     */
    public static final String MODULES_ORDER_ID = "modules_order_id";


    /***
     * 订单库存key
     */
    public static final String MODULES_ORDER_PRODUCT_STOCK = "modules_order_product_id";


    /***
     * 订单超时后回加库存的key
     */
    public static final String MODULES_ORDER_RETURN_PRODUCT_STOCK = "modules_order_return_product_id";


    /***
     * 订单对回退的库存加锁的时间  5s
     */
    public static final long MODULES_ORDER_RETURN_PRODUCT_STOCK_TIME = 10L;


}
