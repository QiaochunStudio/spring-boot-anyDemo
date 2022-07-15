package com.hjt.util;


import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;
import java.util.Arrays;
/**
 * 条件模糊查询
 * */
public class ConditionsUtil {
    public static <T> QueryWrapper<T> getLikeQueryWrapper(T entity) {
        QueryWrapper<T> queryWrapper = new QueryWrapper(entity);
        Field[] fields = ReflectUtil.getFields(entity.getClass());
        Arrays.stream(fields).forEach((e) -> {
            if (e.getType().getName().equals(String.class.getName())) {
                String val = (String)ReflectUtil.invoke(entity, "get".concat(cn.hutool.core.util.StrUtil.upperFirst(e.getName())), new Object[0]);
                if (cn.hutool.core.util.StrUtil.isNotBlank(val)) {
                    queryWrapper.like(cn.hutool.core.util.StrUtil.toUnderlineCase(e.getName()), val);
                    ReflectUtil.setFieldValue(entity, e.getName(), (Object)null);
                }
            }

        });
        return queryWrapper;
    }
}
