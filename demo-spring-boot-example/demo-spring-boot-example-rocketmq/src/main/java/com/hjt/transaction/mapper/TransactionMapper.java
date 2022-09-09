package com.hjt.transaction.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TransactionMapper {

    /***
     * 插入数据
     * @param id
     * @param content
     */
    @Insert("INSERT into t_transaction(id,content)VALUES(#{id},#{content})")
    void insertData(@Param("id") String id,@Param("content")String content);
}
