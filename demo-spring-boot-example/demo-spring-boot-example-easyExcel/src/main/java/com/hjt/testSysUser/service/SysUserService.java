package com.hjt.testSysUser.service;

import com.hjt.testSysUser.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
* <p>
*  服务类
* </p>
*
* @author hjt
* @since 2022-09-05
*/
public interface SysUserService {

    /**
    * 分页查询SysUser
    *
    * @param page     当前页数
    * @param pageSize 页的大小
    * @param factor  搜索关键词
    * @return 返回mybatis-plus的Page对象,其中records字段为符合条件的查询结果
    * @author hjt
    * @since 2022-09-05
    */
    Page<SysUser> listSysUsersByPage(int page, int pageSize, SysUser factor);

    /**
    * 根据id查询SysUser
    *
    * @param id 需要查询的SysUser的id
    * @return 返回对应id的SysUser对象
    * @author hjt
    * @since 2022-09-05
    */
    SysUser getSysUserById(Long id);

    /**
    * 插入SysUser
    *
    * @param sysUser 需要插入的SysUser对象
    * @return 返回插入成功之后SysUser对象的id
    * @author hjt
    * @since 2022-09-05
    */
    Long insertSysUser(SysUser sysUser);

    /**
    * 根据id删除SysUser
    *
    * @param id 需要删除的SysUser对象的id
    * @return 返回被删除的SysUser对象的id
    * @author hjt
    * @since 2022-09-05
    */
    Long deleteSysUserById(Long id);

    /**
    * 根据id更新SysUser
    *
    * @param sysUser 需要更新的SysUser对象
    * @return 返回被更新的SysUser对象的id
    * @author hjt
    * @since 2022-09-05
    */
    Long updateSysUser(SysUser sysUser);

}
