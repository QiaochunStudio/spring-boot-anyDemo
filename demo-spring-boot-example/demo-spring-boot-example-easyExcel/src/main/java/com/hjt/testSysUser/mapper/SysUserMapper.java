package com.hjt.testSysUser.mapper;

import com.hjt.testSysUser.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
*  Mapper 接口
* </p>
*
* @author hjt
* @since 2022-09-05
*/
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

}
