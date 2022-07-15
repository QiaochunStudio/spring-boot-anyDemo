# Spirng Cloud Gateway详解

Gateway是在Spring生态系统之上构建的API网关服务，基于Spring 5，Spring Boot 2和 Project Reactor等技术。Gateway旨在提供一种简单而有效的方式来对API进行路由，以及提供一些强大的过滤器功能， 例如：熔断、限流、重试等。

Spring Cloud Gateway 具有如下特性：

- 基于Spring Framework 5, Project Reactor 和 Spring Boot 2.0 进行构建；
- 动态路由：能够匹配任何请求属性；
- 可以对路由指定 Predicate（断言）和 Filter（过滤器）；
- 集成Hystrix的断路器功能；
- 集成 Spring Cloud 服务发现功能；
- 易于编写的 Predicate（断言）和 Filter（过滤器）；
- 请求限流功能；
- 支持路径重写。

------

## 相关概念解析

- Route（路由）：路由是构建网关的基本模块，它由ID，目标URI，一系列的断言和过滤器组成，如果断言为true则匹配该路由；
- Predicate（断言）：指的是Java 8 的 Function Predicate。 输入类型是Spring框架中的ServerWebExchange。 这使开发人员可以匹配HTTP请求中的所有内容，例如请求头或请求参数。如果请求与断言相匹配，则进行路由；
- Filter（过滤器）：指的是Spring框架中GatewayFilter的实例，使用过滤器，可以在请求被路由前后对请求进行修改。

### 两种不同的配置路由方式

Gateway 提供了两种不同的方式用于配置路由，一种是通过yml文件来配置，另一种是通过Java Bean来配置，下面我们分别介绍下。

### 1.yml配置(一般选择这种)

```java
spring:
  cloud:
    gateway:
      discovery:
        locator:
          lowerCaseServiceId: true
          enabled: true
      routes:
        # 认证中心
        - id: hjt-platform-auth
          uri: lb://hjt-platform-auth
          predicates:
            - Path=/auth/**
          filters:
            # 验证码处理
            # - CacheRequestFilter
            # - ValidateCodeFilter
            #- SwaggerHeaderFilter
            - StripPrefix=1
```

- id：一般配置的是  应用名称  要唯一
- uri：lb 指的是注册到服务中心（nacos,eurkea等）名称，也可以配ip eg:http://127.0.0.1:7702
-  predicates:  断言  指的是在请求的路径拼上该路径

```
hjt-platform-auth 服务是 7701端口 
```

eg: http://127.0.0.1:7702/auth/test  等价于 http://127.0.0.1:7701/test

### 2.Java Bean

```
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route2", r -> r.path("/auth/test")
                        .uri("http://127.0.0.1:7702/auth/test"))
                .build();
    }
}
```

##  Route Predicate 的使用

Spring Cloud Gateway将路由匹配作为Spring WebFlux HandlerMapping基础架构的一部分。 Spring Cloud Gateway包括许多内置的Route Predicate工厂。 所有这些Predicate都与HTTP请求的不同属性匹配。 多个Route Predicate工厂可以进行组合，下面我们来介绍下一些常用的Route Predicate。

注意：Predicate中提到的配置都在application-predicate.yml文件中进行修改，并用该配置启动api-gateway服务。

### Predicate

在指定时间之后的请求会匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: after_route
          uri: ${service-url.user-service}
          predicates:
            - After=2019-09-24T16:30:00+08:00[Asia/Shanghai]
```



### Predicate

在指定时间之前的请求会匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: before_route
          uri: ${service-url.user-service}
          predicates:
            - Before=2019-09-24T16:30:00+08:00[Asia/Shanghai]
```

### Between Route Predicate

在指定时间区间内的请求会匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: before_route
          uri: ${service-url.user-service}
          predicates:
            - Between=2019-09-24T16:30:00+08:00[Asia/Shanghai], 2019-09-25T16:30:00+08:00[Asia/Shanghai]
```

### Cookie Route Predicate

带有指定Cookie的请求会匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: cookie_route
          uri: ${service-url.user-service}
          predicates:
            - Cookie=username,hjt
```



使用curl工具发送带有cookie为`username=hjt`的请求可以匹配该路由。



```bash
curl http://localhost:9201/user/1 --cookie "username=hjt"
```



### Header Route Predicate

带有指定请求头的请求会匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: header_route
        uri: ${service-url.user-service}
        predicates:
        - Header=X-Request-Id, \d+
```



使用curl工具发送带有请求头为`X-Request-Id:123`的请求可以匹配该路由。



```bash
curl http://localhost:9201/user/1 -H "X-Request-Id:123" 
```



### Host Route Predicate

带有指定Host的请求会匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: host_route
          uri: ${service-url.user-service}
          predicates:
            - Host=**.hjtzheng.com
```




使用curl工具发送带有请求头为`Host:www.hjtzheng.com`的请求可以匹配该路由。



```bash
curl http://localhost:9201/user/1 -H "Host:www.hjtzheng.com" 
```



### Method Route Predicate

发送指定方法的请求会匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: method_route
        uri: ${service-url.user-service}
        predicates:
        - Method=GET
```



使用curl工具发送GET请求可以匹配该路由。



```bash
curl http://localhost:9201/user/1
```



使用curl工具发送POST请求无法匹配该路由。



```bash
curl -X POST http://localhost:9201/user/1
```



### Path Route Predicate

发送指定路径的请求会匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: path_route
          uri: ${service-url.user-service}/user/{id}
          predicates:
            - Path=/user/{id}
```



使用curl工具发送`/user/1`路径请求可以匹配该路由。



```bash
curl http://localhost:9201/user/1
```



使用curl工具发送`/abc/1`路径请求无法匹配该路由。



```bash
curl http://localhost:9201/abc/1
```



### Query Route Predicate

带指定查询参数的请求可以匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: query_route
        uri: ${service-url.user-service}/user/getByUsername
        predicates:
        - Query=username
```



使用curl工具发送带`username=hjt`查询参数的请求可以匹配该路由。



```bash
curl http://localhost:9201/user/getByUsername?username=hjt
```



使用curl工具发送带不带查询参数的请求无法匹配该路由。



```bash
curl http://localhost:9201/user/getByUsername
```



### RemoteAddr Route Predicate

从指定远程地址发起的请求可以匹配该路由。



```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: remoteaddr_route
        uri: ${service-url.user-service}
        predicates:
        - RemoteAddr=192.168.1.1/24
```



使用curl工具从192.168.1.1发起请求可以匹配该路由。



```bash
curl http://localhost:9201/user/1
```

1

### Weight Route Predicate

使用权重来路由相应请求，以下表示有80%的请求会被路由到localhost:8201，20%会被路由到localhost:8202。



```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: weight_high
        uri: http://localhost:8201
        predicates:
        - Weight=group1, 8
      - id: weight_low
        uri: http://localhost:8202
        predicates:
        - Weight=group1, 2
```



## Route Filter 的使用

> 路由过滤器可用于修改进入的HTTP请求和返回的HTTP响应，路由过滤器只能指定路由进行使用。Spring Cloud Gateway 内置了多种路由过滤器，他们都由GatewayFilter的工厂类来产生，下面我们介绍下常用路由过滤器的用法。

### AddRequestParameter GatewayFilter

给请求添加参数的过滤器。



```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: add_request_parameter_route
          uri: http://localhost:8201
          filters:
            - AddRequestParameter=username, hjt
          predicates:
            - Method=GET
```



以上配置会对GET请求添加`username=hjt`的请求参数，通过curl工具使用以下命令进行测试。



```bash
curl http://localhost:9201/user/getByUsername
```



相当于发起该请求：



```bash
curl http://localhost:8201/user/getByUsername?username=hjt
```



### GatewayFilter

对指定数量的路径前缀进行去除的过滤器。



```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: strip_prefix_route
        uri: http://localhost:8201
        predicates:
        - Path=/user-service/**
        filters:
        - StripPrefix=2
```



以上配置会把以`/user-service/`开头的请求的路径去除两位，通过curl工具使用以下命令进行测试。



```bash
curl http://localhost:9201/user-service/a/user/1
```



相当于发起该请求：



```bash
curl http://localhost:8201/user/1
```

### GatewayFilter

与StripPrefix过滤器恰好相反，会对原有路径进行增加操作的过滤器。



```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: prefix_path_route
        uri: http://localhost:8201
        predicates:
        - Method=GET
        filters:
        - PrefixPath=/user
```



以上配置会对所有GET请求添加`/user`路径前缀，通过curl工具使用以下命令进行测试。



```bash
curl http://localhost:9201/1
```



相当于发起该请求：



```bash
curl http://localhost:8201/user/1
```





参考来源：http://www.macrozheng.com/