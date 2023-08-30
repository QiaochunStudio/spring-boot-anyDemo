# thread-pool-demo
Spring Boot项目中使用线程池示例。使用ThreadPoolTaskExecutor类创建线程池，并注入到ioc容器中，全局可使用该线程池。

业务类[Input2OutputServiceImpl](https://github.com/hongjiatao/spring-boot-anyDemo/tree/main/demo-spring-boot-example)中有三个方法：

- singleProcess：处理单个业务
- multiProcess：批量处理业务
- asyncProcess：异步处理业务

测试类[ThreadPoolApplicationTests](https://github.com/hongjiatao/spring-boot-anyDemo/tree/main/demo-spring-boot-examplea)中调用了multiProcess、asyncProcess作为示例。