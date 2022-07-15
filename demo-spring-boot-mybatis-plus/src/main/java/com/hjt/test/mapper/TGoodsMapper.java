package com.hjt.test.mapper;

import com.hjt.test.entity.TGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* 商品表 Mapper 接口
* </p>
*
* @author hjt
* @since 2022-06-23
*/
@Mapper
public interface TGoodsMapper extends BaseMapper<TGoods> {

}
