package com.hjt.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hjt.test.entity.TGoods;
import com.hjt.test.mapper.TGoodsMapper;
import com.hjt.test.service.TGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjt.util.ConditionsUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.hjt.exception.bizException.BizException;

import java.util.List;
import java.util.ArrayList;


/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author hjt
 * @since 2022-06-23
 */
@Slf4j
@Service
public class TGoodsServiceImpl extends ServiceImpl<TGoodsMapper, TGoods> implements TGoodsService {

    @Override
    public Page<TGoods> listTGoodssByPage(int page, int pageSize, TGoods tGoods) {
        log.info("正在执行分页查询tGoods: page = {} pageSize = {} factor = {}", page, pageSize, tGoods);
//        QueryWrapper<TGoods> queryWrapper =  new QueryWrapper<TGoods>().like("", factor);
        QueryWrapper<TGoods> queryWrapper = ConditionsUtil.getLikeQueryWrapper(tGoods);
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        Page<TGoods> result = super.page(new Page<>(page, pageSize), queryWrapper);
        log.info("分页查询tGoods完毕: 结果数 = {} ", result.getRecords().size());
        return result;
    }

    @Override
    public TGoods getTGoodsById(Long id) {
        log.info("正在查询tGoods中id为{}的数据", id);
        TGoods tGoods = super.getById(id);
        log.info("查询id为{}的tGoods{}", id, (null == tGoods ? "无结果" : "成功"));
        QueryWrapper<TGoods> objectQueryWrapper = new QueryWrapper<>();
        // 根据 Wrapper，查询一条记录
        LambdaQueryWrapper<TGoods> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(TGoods::getEnabledState,0);
//        TGoods oneData = super.getOne(objectLambdaQueryWrapper, true);
        return tGoods;
    }

    @Override
    public Long insertTGoods(TGoods tGoods) {
        log.info("正在插入tGoods");
        if (super.save(tGoods)) {
            log.info("插入tGoods成功,id为{}", tGoods.getId());
            return tGoods.getId();
        } else {
            log.error("插入tGoods失败");
            throw new BizException("添加失败");
        }

    }

    @Override
    public Long insertList(List<TGoods> tGoods) {
        boolean b = super.saveBatch(tGoods);
        if (b) {
            return (long) tGoods.size();
        } else {
            throw new BizException("批量插入失败");
        }

    }

    @Override
    public Long updateList(List<TGoods> tGoods) {
        super.saveOrUpdateBatch(tGoods);
        return (long) tGoods.size();
    }

    @Override
    public Long deleteList(List<Long> ids) {
        boolean b = super.removeByIds(ids);
        if (b) {
            return (long) ids.size();
        }
        throw new BizException("批量刪除失败");
    }

    @Override
    public Long deleteTGoodsById(Long id) {
        log.info("正在删除id为{}的tGoods", id);
        if (super.removeById(id)) {
            log.info("删除id为{}的tGoods成功", id);
            return id;
        } else {
            log.error("删除id为{}的tGoods失败", id);
            throw new BizException("删除失败[id=" + id + "]");
        }
    }

    @Override
    public Long updateTGoods(TGoods tGoods) {
        log.info("正在更新id为{}的tGoods", tGoods.getId());
        if (super.updateById(tGoods)) {
            log.info("更新d为{}的tGoods成功", tGoods.getId());
            return tGoods.getId();
        } else {
            log.error("更新id为{}的tGoods失败", tGoods.getId());
            throw new BizException("更新失败[id=" + tGoods.getId() + "]");
        }
    }

    @Override
    public Long updateByCondition(TGoods tGoods) {
//       QueryWrapper<TGoods> objectLambdaQueryWrapper = new QueryWrapper<>();
//        objectLambdaQueryWrapper.eq("deleted_state",0);
        LambdaQueryWrapper<TGoods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TGoods::getDeletedState, 0)
                          .eq(TGoods::getEnabledState, 0);
        super.update(tGoods, lambdaQueryWrapper);

        List<TGoods> ids = new ArrayList<>();
        // 根据ID 批量更新
         super.updateBatchById(ids, 8);

        return null;
    }

}
