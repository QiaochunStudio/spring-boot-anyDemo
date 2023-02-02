# 项目简介

## 项目背景介绍

由于本人是想多学学基础框架搭建的过程，看看会遇到现在无法预料的Bug，解决Bug又兴奋又快乐哈哈哈哈~~~

希望也想提升技术的小伙伴们，可以一起跟我搭建项目中成长，互相学习哈哈哈哈。

目前，该项目目前还处于了基础框架的搭建阶段，之后会不断更新比如SpringCloud，nacos，各种中间件等等框架的搭建，也包括服务器Linux中的结合运用。



项目大体框架介绍：
![image](https://user-images.githubusercontent.com/76513734/212838705-fc34009c-bd80-421a-b96c-a2cc7ee38a16.png)





这是公共模块，如果你项目中要引用的话，就直接引入某个模块即可，正在做到轻装上阵。



```java
    demo-spring-boot-mybatis-plus      mybatis-plus 模块
    demo-spring-boot-common            公共模块
    demo-spring-boot-bom               全部版本控制
    demo-spring-boot-core              核心模块
    demo-spring-boot-druid             数据源模块
    demo-spring-boot-encrypt-data      加解密模块     
    demo-spring-boot-security          安全模块
    demo-spring-boot-redis             redis模块
    demo-spring-boot-api               远程调用模块
    demo-spring-boot-log               日志模块 
    demo-spring-boot-gateway           网关模块
    demo-spring-boot-example           各种demo例子
    demo-spring-boot-generator         代码生成器
    demo-spring-boot-mongodb           mongodb
    demo-spring-boot-repeatSubmit      重复提交
    demo-spring-boot-rocketmq          rocketmq
    demo-spring-boot-websocket         websocket
    demo-spring-boot-springSecurity    springSecurity模块
    
    微服务
    demo-springCloud
                     ---- demo-springCloud-all-client        全部客户端
                     ---- demo-springCloud-hystrix           hystrix服务熔断
                     ---- demo-springCloud-oauth2            oauth2单点登录
                     ---- demo-springCloud-seata             seata 
                     ---- demo-springCloud-sentinel-service  sentinel
```

如果改好代码，则可以直接打包部署到私服nexus

要先使用，先搭建一个nexus私服哦（ps：很easy,不懂私信我哈哈哈）

[搭建私服可以看我另外一个教程](https://blog.csdn.net/weixin_40483369/article/details/123794145)

## 相关技术文档

### [1.Rocketmq技术详解](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/Rocketmq%E6%8A%80%E6%9C%AF%E8%AF%A6%E8%A7%A3)

### [2.Rocketmq最佳实践](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/Rocketmq%E6%9C%80%E4%BD%B3%E5%AE%9E%E8%B7%B5)

### [3.Sentinel使用和技术详解](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/Sentinel%E4%BD%BF%E7%94%A8%E5%92%8C%E6%8A%80%E6%9C%AF%E8%AF%A6%E8%A7%A3)

### [Spirng Cloud Gateway详解](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/Spirng-Cloud-Gateway%E8%AF%A6%E8%A7%A3)

### [4.Spring Cloud Hystrix：服务容错保护](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/Spring-Cloud-Hystrix%EF%BC%9A%E6%9C%8D%E5%8A%A1%E5%AE%B9%E9%94%99%E4%BF%9D%E6%8A%A4)

### [5.Spring Cloud Security：Oauth2使用](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/Spring-Cloud-Security%EF%BC%9AOauth2%E4%BD%BF%E7%94%A8)

### [6.webscoket技术文档说明](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/webscoket%E6%8A%80%E6%9C%AF%E6%96%87%E6%A1%A3%E8%AF%B4%E6%98%8E)

### [7.使用Seata彻底解决Spring Cloud中的分布式事务问题！](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/%E4%BD%BF%E7%94%A8Seata%E5%BD%BB%E5%BA%95%E8%A7%A3%E5%86%B3Spring-Cloud%E4%B8%AD%E7%9A%84%E5%88%86%E5%B8%83%E5%BC%8F%E4%BA%8B%E5%8A%A1%E9%97%AE%E9%A2%98%EF%BC%81)

### [8.搭建项目出现报错问题汇总](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/%E6%90%AD%E5%BB%BA%E9%A1%B9%E7%9B%AE%E5%87%BA%E7%8E%B0%E6%8A%A5%E9%94%99%E9%97%AE%E9%A2%98%E6%B1%87%E6%80%BB)

### [9.项目技术文档说明](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/%E9%A1%B9%E7%9B%AE%E6%8A%80%E6%9C%AF%E6%96%87%E6%A1%A3%E8%AF%B4%E6%98%8E)

### [10.项目搭建注解补充说明](https://github.com/hongjiatao/spring-boot-anyDemo/wiki/%E9%A1%B9%E7%9B%AE%E6%90%AD%E5%BB%BA%E6%B3%A8%E8%A7%A3%E8%A1%A5%E5%85%85%E8%AF%B4%E6%98%8E)





先暂时介绍到这，搬砖去了（敲代码，一杯茶泡一天，一行代码敲一天），后续更新.



有不懂得，联系我哈！请叫我行走的活雷锋。

个人博客：https://blog.csdn.net/weixin_40483369?type=blog    （世外平常人）

QQ：1022134186

微信号：sally1022134186

该项目目前仅做公共模块，在你主模块服务中只需要引入该服务即可，使得你主要模块变得更加轻松，便捷
后续会更新主要模块的代码，各位看官觉得不错，点个小星星 Fork支持一下，谢谢。

