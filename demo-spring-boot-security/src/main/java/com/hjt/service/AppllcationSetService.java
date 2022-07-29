//package com.hjt.service;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
//import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
//import com.hjt.config.DbAutoConfiguration;
//import com.hjt.domain.DBDriverProperties;
//import com.hjt.domain.DbaTables;
//import com.hjt.mapper.DbaTablesMapper;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.ServletContextAware;
//
//import javax.servlet.ServletContext;
//import java.util.List;
//
///**
// * @Description: 应用设置服务
// * @Author: hjt  <1022134186@qq.com>
// * @Date: 2021/08/19
// */
//@Service
//public class AppllcationSetService implements InitializingBean, ServletContextAware {
//
//    @Autowired
//    private DbaTablesMapper dbaTablesMapper;
//    @Autowired
//    private DBDriverProperties dbDriverProperties;
//    @Autowired
//    private PaginationInterceptor paginationInterceptor;
//    @Autowired
//    private DbAutoConfiguration dbAutoConfiguration;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//    }
//
//    @Override
//    public void setServletContext(ServletContext servletContext) {
//        String schema = dbAutoConfiguration.selection(dbDriverProperties);
//        if (schema == null) {
//            return;
//        }
//        DynamicTableNameParser iSqlParser = (DynamicTableNameParser) paginationInterceptor.getSqlParserList().get(0);
//        if (iSqlParser.getTableNameHandlerMap().size() == 0) {
//            QueryWrapper<DbaTables> queryWrapper = new QueryWrapper();
//            queryWrapper.select("TABLE_NAME").eq("owner", schema);
//            List<DbaTables> tablesLists = dbaTablesMapper.selectList(queryWrapper);
//            dbAutoConfiguration.setDbTable(tablesLists, schema, paginationInterceptor);
//        }
//    }
//}
