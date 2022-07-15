# 1、nexus使用

1. ###### 自己创建仓库,选择 maven2(proxy)

![image-20220705134043201](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705134043201.png)

###### 2.配置阿里云地址

**http://maven.aliyun.com/nexus/content/groups/public/**

![image-20220705112711627](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705112711627.png)

![image-20220705112825591](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705112825591.png)

###### 3.在maven-publict添加你刚才创建的仓库

![image-20220705112937453](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705112937453.png)

![image-20220705113105262](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705113105262.png)

###### 4.Maven 配置使用私服,有两种方式

- setting.xml：该文件配置的是全局模式

- pom.xml：该文件的配置的是项目独享模式

  **注意**：若 **pom.xml** 和 **setting.xml** 同时配置了，以 **pom.xml** 为准。

###### 5.新建好仓库的名字

![image-20220705134120825](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705134120825.png)

###### 6.pom文件的配置

```java
<!--打包到自己的依赖-->
<distributionManagement>
    <repository>
        <id>nexus</id>
        <name>hjt-maven-release</name>
       <url>http://1.15.180.135:8081/repository/hjt-maven-release/</url>
    </repository>
</distributionManagement>
```

setting中的配置

配置nexus的账号密码  

- 注意 <id>中的nexus和pom文件中的<id>nexus要对于上

![image-20220705145150315](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705145150315.png)

![image-20220705145421839](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705145421839.png)

这里可以配置hjt-maven-release仓库，也可以配置maven-public。因为刚才我在maven-public中配置了我新建的仓库 hjt-maven-release仓库。

###### 7.部署成功

![image-20220705145825404](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705145825404.png)

![image-20220705145839326](C:\Users\Lenovo\AppData\Roaming\Typora\typora-user-images\image-20220705145839326.png)