package com.hjt.hutool.json.jsonArray;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :hjt
 * @date : 2022/9/14
 */
public class JsonStrArray {
    public static void main(String[] args) {
        List<ProductDto> products = new ArrayList<>();
        for(int i = 0;i<3;i++){
            ProductDto productDto = new ProductDto();
            productDto.setId(1L);
            productDto.setStock(222L);
            productDto.setStock(333L);
            productDto.setUserId(3L);
            products.add(productDto);
        }


//        String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
//        JSONArray array = JSONUtil.parseArray(jsonArr);
//
//        List<Dict> list = JSONUtil.toList(array, Dict.class);
//        System.out.println(list);


        String jsonArr = "[{\"id\":111,\"name\":\"test1\"},{\"id\":112,\"name\":\"test2\"}]";
        JSONArray array = JSONUtil.parseArray(jsonArr);

        User[] list = array.toArray(new User[0]);
        System.out.println(list);
    }

}
