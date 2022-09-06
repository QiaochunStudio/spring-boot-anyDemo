package com.hjt.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.MapUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjt.common.api.CommonResult;
import com.hjt.domain.Member;
import com.hjt.domain.Order;
import com.hjt.domain.OrderData;
import com.hjt.domain.Product;
import com.hjt.read.IndexOrNameDataListener;
import com.hjt.strategy.CustomMergeStrategy;
import com.hjt.testSysUser.entity.SysUser;
import com.hjt.testSysUser.mapper.SysUserMapper;
import com.hjt.util.LocalJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * EasyExcel导入导出测试Controller
 * Created by hjt on 2021/10/12.
 */
@Controller
@Api(tags = "EasyExcelController")
@RequestMapping("/easyExcel")
public class EasyExcelController {

    @Resource
    private SysUserMapper sysUserMapper;

    @SneakyThrows(IOException.class)
    @ApiOperation(value = "导出会员列表Excel")
    @RequestMapping(value = "/exportMemberList", method = RequestMethod.GET)
    public void exportMemberList(HttpServletResponse response) {
        setExcelRespProp(response, "会员列表");
        List<Member> memberList = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
        EasyExcel.write(response.getOutputStream())
                .head(Member.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("会员列表")
                .doWrite(memberList);
    }

    @SneakyThrows
    @ApiOperation("从Excel导入会员列表")
    @RequestMapping(value = "/importMemberList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult importMemberList(@RequestPart("file") MultipartFile file) {
        List<Member> memberList = EasyExcel.read(file.getInputStream())
                .head(Member.class)
                .sheet()
                .doReadSync();
        return CommonResult.success(memberList);
    }

    @SneakyThrows
    @ApiOperation(value = "导出订单列表Excel")
    @RequestMapping(value = "/exportOrderList", method = RequestMethod.GET)
    public void exportOrderList(HttpServletResponse response) {
        List<Order> orderList = getOrderList();
        List<OrderData> orderDataList = convert(orderList);
        setExcelRespProp(response, "订单列表");
        EasyExcel.write(response.getOutputStream())
                .head(OrderData.class)
                .registerWriteHandler(new CustomMergeStrategy(OrderData.class))
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("订单列表")
                .doWrite(orderDataList);
    }

    /**
     * 设置excel下载响应头属性
     */
    private void setExcelRespProp(HttpServletResponse response, String rawFileName) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }

    private List<OrderData> convert(List<Order> orderList) {
        List<OrderData> result = new ArrayList<>();
        for (Order order : orderList) {
            List<Product> productList = order.getProductList();
            for (Product product : productList) {
                OrderData orderData = new OrderData();
                BeanUtil.copyProperties(product,orderData);
                BeanUtil.copyProperties(order,orderData);
                result.add(orderData);
            }
        }
        return result;
    }

    private List<Order> getOrderList() {
        List<Order> orderList = LocalJsonUtil.getListFromJson("json/orders.json", Order.class);
        List<Product> productList = LocalJsonUtil.getListFromJson("json/products.json", Product.class);
        List<Member> memberList = LocalJsonUtil.getListFromJson("json/members.json", Member.class);
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            order.setMember(memberList.get(i));
            order.setProductList(productList);
        }
        return orderList;
    }


    @SneakyThrows(IOException.class)
    @ApiOperation(value = "导出SysUser用户列表Excel并上传到云上")
    @RequestMapping(value = "/exportSysUserList", method = RequestMethod.GET)
    public void exportSysUserList(HttpServletResponse response) {
        try{
            String fileName = "用户列表";
            setExcelRespPropByHjt(response, fileName,ExcelTypeEnum.XLSX);
            LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            List<SysUser> sysUsers = sysUserMapper.selectList(lambdaQueryWrapper);
            EasyExcel.write(response.getOutputStream())
                    .autoCloseStream(Boolean.FALSE)
                    .head(SysUser.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("用户列表")
                    .doWrite(sysUsers);
        }catch (Exception e){
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = MapUtils.newHashMap();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSONUtil.toJsonStr(map));
        }

    }



    @SneakyThrows
    @ApiOperation("从Excel导入SysUser表并读取数据")
    @RequestMapping(value = "/importSysUserList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult importSysUserList(@RequestPart("file") MultipartFile file) {
//        //官网例子代码
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "用户列表.xlsx";
//        // 这里默认读取第一个sheet
//        EasyExcel.read(fileName, SysUser.class, new IndexOrNameDataListener()).sheet().doRead();

        List<SysUser> sysUsersList = EasyExcel.read(file.getInputStream(),new IndexOrNameDataListener())
                .head(SysUser.class)
                .sheet()
                .doReadSync();
        return CommonResult.success(sysUsersList);
    }







    /**
     * 设置excel下载响应头属性
     */
    private void setExcelRespPropByHjt(HttpServletResponse response, String rawFileName,ExcelTypeEnum fileType) throws UnsupportedEncodingException {
        //设置文件类型
        String contentType = "";
        if(fileType.getValue().equals(".csv")){
            contentType = "text/csv";
        }
        if(fileType.getValue().equals(".xlxs")){
            contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        if(fileType.getValue().equals(".xls")){
            contentType = "application/vnd.ms-excel";
        }
        response.setContentType(contentType);
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(rawFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + fileType.getValue());
    }



}
