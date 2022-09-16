package com.hjt.remote;

import com.hjt.constant.ServiceNameConstants;
import com.hjt.domain.Order;
import com.hjt.domain.Product;
import com.hjt.domain.R;
import com.hjt.factory.RemoteOrderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author :hjt
 * @date : 2022/9/16
 */
@FeignClient(contextId = "remoteOrderService", value = ServiceNameConstants.HJT_PLATFORM_ORDER, fallbackFactory = RemoteOrderFallbackFactory.class)
public interface RemoteOrderService {

        /***
         * 根据订单id查询订单
         * @param orderId
         * @return
         */
    @GetMapping("/order/myOrder/api/v1/order/{orderId}")
    public R<Order> getOrderInfo(@PathVariable("orderId") Long orderId);

    /***
     * 根据商品id获取商品信息
     * @param id
     * @return
     */
    @GetMapping("/order/myOrder/api/v1/product/{id}")
    public R<Product> getProductInfo(@PathVariable("id") Long id);




}
