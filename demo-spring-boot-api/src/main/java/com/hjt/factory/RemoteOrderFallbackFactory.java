package com.hjt.factory;

import com.hjt.domain.Order;
import com.hjt.domain.Product;
import com.hjt.domain.R;
import com.hjt.remote.RemoteOrderService;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :hjt
 * @date : 2022/9/16
 */
public class RemoteOrderFallbackFactory implements FallbackFactory<RemoteOrderService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteOrderFallbackFactory.class);
    @Override
    public RemoteOrderService create(Throwable throwable) {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteOrderService(){

            @Override
            public R<Order> getOrderInfo(Long orderId) {
                log.error("orderId错误:{}", orderId);
                return null;
            }

            @Override
            public R<Product> getProductInfo(Long id) {
                log.error("商品Id错误:{}", id);
                return null;
            }

            @Override
            public R<Order> updateOrderById(Long orderId) {
                log.error("订单Id错误:{}", orderId);
                return null;
            }
        };
    }
}
