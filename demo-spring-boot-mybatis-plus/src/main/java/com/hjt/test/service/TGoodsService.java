package com.hjt.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjt.test.entity.TGoods;
import java.util.List;
/**
* <p>
* 商品表 服务类
* </p>
*
* @author hjt
* @since 2022-06-23
*/
public interface TGoodsService  {

    /**
    * 分页查询TGoods
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param tGoods  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author hjt
    * @since 2022-06-23
    */
    Page<TGoods> listTGoodssByPage(int page, int pageSize, TGoods tGoods);

    /**
    * 根据id查询TGoods
    *
    * @param id 需要查询的TGoods的id
    * @return 返回对应id的TGoods对象
    * @author hjt
    * @since 2022-06-23
    */
    TGoods getTGoodsById(Long id);

    /**
    * 插入TGoods
    *
    * @param tGoods 需要插入的TGoods对象
    * @return 返回插入成功之后TGoods对象的id
    * @author hjt
    * @since 2022-06-23
    */
    Long insertTGoods(TGoods tGoods);

    /**
     * 批量插入
     *
     * */
    Long insertList(List<TGoods> tGoods);

    /***
     * 批量修改
     * @param tGoods
     * @return
     */
    Long updateList(List<TGoods> tGoods);

    /***
     * 批量删除
     * @param ids
     * @return
     */
    Long deleteList(List<Long> ids);

    /**
    * 根据id删除TGoods
    *
    * @param id 需要删除的TGoods对象的id
    * @return 返回被删除的TGoods对象的id
    * @author hjt
    * @since 2022-06-23
    */
    Long deleteTGoodsById(Long id);

    /**
    * 根据id更新TGoods
    *
    * @param tGoods 需要更新的TGoods对象
    * @return 返回被更新的TGoods对象的id
    * @author hjt
    * @since 2022-06-23
    */
    Long updateTGoods(TGoods tGoods);

    /***
     * 根据条件修改
     * @param tGoods
     * @return
     */
    Long updateByCondition(TGoods tGoods);

}
