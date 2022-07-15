package com.hjt.rule;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @author Jerry <br/>
 * 灰度路由
 * @Time： 2021年03月24日 <br/>
 */
public interface GrayLoadBalancer {

	/**
	 * 根据serviceId 筛选可用服务
	 *
	 * @param serviceId 服务ID
	 * @param request   当前请求
	 * @return
	 */
	ServiceInstance choose(String serviceId, ServerHttpRequest request);

}

