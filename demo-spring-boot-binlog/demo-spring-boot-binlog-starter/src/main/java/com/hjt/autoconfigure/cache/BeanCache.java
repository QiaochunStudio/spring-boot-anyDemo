package com.hjt.autoconfigure.cache;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class BeanCache {

    private Object object;
    private List<Method> methodList;

    public Method getMethod(String methodName){
        if(CollectionUtils.isEmpty(methodList)){
            return null;
        }
        return methodList.stream().filter(method -> method.getName().equals(methodName)).findFirst().orElse(null);
    }

    public void addMethod(Method method){
        if(CollectionUtils.isEmpty(methodList)){
            methodList = new ArrayList<>();
            methodList.add(method);
        }else {
            Method filterMethod = methodList.stream().filter(i -> i.equals(method)).findFirst().orElse(null);
            if(Objects.isNull(filterMethod)){
                methodList.add(method);
            }
        }

    }


}
