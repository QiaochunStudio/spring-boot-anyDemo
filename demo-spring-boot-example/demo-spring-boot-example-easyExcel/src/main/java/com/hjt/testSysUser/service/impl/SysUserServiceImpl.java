package com.hjt.testSysUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjt.testSysUser.entity.SysUser;
import com.hjt.testSysUser.mapper.SysUserMapper;
import com.hjt.testSysUser.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
* <p>
*  服务实现类
* </p>
*
* @author hjt
* @since 2022-09-05
*/
@Slf4j
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public Page<SysUser> listSysUsersByPage(int page, int pageSize, SysUser factor) {
        log.info("正在执行分页查询sysUser: page = {} pageSize = {} factor = {}",page,pageSize,factor);
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //TODO 这里需要自定义用于匹配的字段,并把wrapper传入下面的page方法
        //模糊查询例子
       //lambdaQueryWrapper.like(SysUser::getNickName,factor);
        //也可以实体类进行模糊查询
        //QueryWrapper<SysUser>likeQueryWrapper = ConditionsUtil.getLikeQueryWrapper(factor);
        //Page<SysUser> result = super.page(new Page<>(page, pageSize),lambdaQueryWrapper);
        Page<SysUser> result = super.page(new Page<>(page, pageSize));
        log.info("分页查询sysUser完毕: 结果数 = {} ",result.getRecords().size());
        return result;
    }

    @Override
    public SysUser getSysUserById(Long id) {
        log.info("正在查询sysUser中id为{}的数据",id);
        SysUser sysUser = super.getById(id);
        log.info("查询id为{}的sysUser{}",id,(null == sysUser?"无结果":"成功"));
        return sysUser;
    }

    @Override
    public Long insertSysUser(SysUser sysUser) {
        log.info("正在插入sysUser");
            log.info("插入sysUser成功,id为{}",sysUser.getUserId());
            return sysUser.getUserId();
    }

    @Override
    public Long deleteSysUserById(Long id) {
        log.info("正在删除id为{}的sysUser",id);
            super.removeById(id);
            log.info("删除id为{}的sysUser成功",id);
            return id;
    }

    @Override
    public Long updateSysUser(SysUser sysUser) {
        log.info("正在更新id为{}的sysUser",sysUser.getUserId());
        if (super.updateById(sysUser)) {
            log.info("更新d为{}的sysUser成功",sysUser.getUserId());
            return sysUser.getUserId();
        }
        return null;
    }

}
