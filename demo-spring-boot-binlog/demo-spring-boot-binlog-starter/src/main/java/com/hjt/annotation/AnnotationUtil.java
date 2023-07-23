package com.hjt.annotation;

import com.alibaba.fastjson.JSON;
import com.hjt.config.BinLogConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/12
 */
@Component
public class AnnotationUtil {

    private String TABLE_NAME = "tableNames";
    private String DS_NAME = "dsName";

    @Autowired
    private ResourceLoader resourceLoader;

    public void getAllAddTagAnnotation(String classPath,Class clazz) throws Exception{
        String canonicalName = clazz.getCanonicalName();
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        CachingMetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        Resource[] resources = resolver.getResources(classPath);
        for (Resource resource : resources) {
            MetadataReader metadataReader = metaReader.getMetadataReader(resource);
            AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
            Set<MethodMetadata> annotatedMethods = annotationMetadata.getAnnotatedMethods(canonicalName);
            if(CollectionUtils.isEmpty(annotatedMethods)){
                return;
            }
            // 类名
            String className = metadataReader.getClassMetadata().getClassName();
            Class<?> aClass = Class.forName(className);
            for (MethodMetadata annotatedMethod : annotatedMethods) {
                Map<String, Object> annotationAttributes = annotatedMethod.getAnnotationAttributes(canonicalName);
                if(CollectionUtils.isEmpty(annotationAttributes)){
                    continue;
                }
                RowListenerModel rowListenerModel = new RowListenerModel();
                Object dsName = annotationAttributes.get(DS_NAME);
                if(Objects.isNull(dsName)){
                    continue;
                }
                rowListenerModel.setDsName(dsName.toString());
                Object tableNames = annotationAttributes.get(TABLE_NAME);
                if(Objects.nonNull(tableNames)){
                    List<String> tableNameList = JSON.parseArray(JSON.toJSONString(tableNames), String.class);
                    rowListenerModel.setTableNames(tableNameList);
                }
                rowListenerModel.setClassName(className);
                rowListenerModel.setMethodName(annotatedMethod.getMethodName());
                rowListenerModel.setClazz(aClass);
                BinLogConfig.getRowListenerModels().add(rowListenerModel);
            }
        }
    }


}
