package com.hjt.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;

import java.util.Scanner;

@Data
public class CodeGenerator {

    /**
     * 配置文件名
     */
//    private final static String APP_PROPERTY = "application.properties";
    private final static String APP_PROPERTY = "application.yml";
    private String projectPath = System.getProperty("user.dir")+"";
    /**
     * 公共包路径
     */
    private String parentPackage = "com.hjt";
    /**
     * 模块名
     */
//    private String module = "test";

    /**
     *
     * 自定义模板位置
     */
    private String templatePath = "templates/mp/";
    private String controllerTemplate = templatePath + "controller.java";
    private String serviceTemplate = templatePath + "service.java";
    private String serviceImplTemplate = templatePath + "serviceImpl.java";
    private String mapperTemplate = templatePath + "mapper.java";
    private String xml = templatePath + "mapperXml.java";

    private static String url = "jdbc:mysql://1.15.180.135:3307/demo-spring-boot-mybatis-plus?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static String username = "root";
    private static String password = "970628hjt.";


    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.execute();
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (ipt != null && !ipt.trim().isEmpty()) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public void execute() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        //获取当前路径的项目名称
        String path = ClassLoader.getSystemResource("").getPath();
        String[] split1 = path.split("/target/classes/");
        String substring = split1[0].substring(1,split1[0].length());
//        gc.setOutputDir(projectPath +"/"+ split[4]+"/src/main/java");
        gc.setOutputDir(substring+"/src/main/java");
        gc.setAuthor(scanner("Author"));
        gc.setOpen(false);
        gc.setActiveRecord(true);
        gc.setIdType(IdType.AUTO);
        gc.setServiceName("%sService");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        gc.setFileOverride(true);
        //   实体属性 Swagger2 注解
         gc.setSwagger2(true);
         gc.setXmlName("test");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        mpg.setDataSource(dataSourceConfig());

        // 包配置
        PackageConfig pc = new PackageConfig();

        pc.setParent(parentPackage);
        String moduleName = scanner("模块名字");
        pc.setModuleName(moduleName);
        //配置xml输出路径
//        pc.set
        mpg.setPackageInfo(pc);
        //设置xml存放的路径
        System.out.println("输出路径:"+substring+"/src/main/resources/mapper/test");
        pc.setXml("test3");
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setService(serviceTemplate);
        templateConfig.setServiceImpl(serviceImplTemplate);
        templateConfig.setMapper(mapperTemplate);
        templateConfig.setController(controllerTemplate);
        templateConfig.setXml(xml);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);

        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }


    private static DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
//        String resourcePath = System.getProperty("user.dir") + "/src/main/resources/" + APP_PROPERTY;
        try {
//            InputStream inStream = new FileInputStream(new File(resourcePath));
//            Properties prop = new Properties();
//            prop.load(inStream);

//            dsc.setUrl(prop.getProperty("spring.datasource.url"));
//            dsc.setDriverName(prop.getProperty("spring.datasource.driver-class-name"));
//            dsc.setUsername(prop.getProperty("spring.datasource.username"));
//            dsc.setPassword(prop.getProperty("spring.datasource.password"));

            dsc.setUrl(url);
            dsc.setDriverName(driverName);
            dsc.setUsername(username);
            dsc.setPassword(password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsc;
    }






}