package com.hjt.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hjt.domain.CommonResult;

/**
 * Created by hjt on 2019/11/7.
 */
public class CustomBlockHandler {

    public static CommonResult handleException(BlockException exception){
        return new CommonResult("自定义限流信息111111111111",200);
    }
}
