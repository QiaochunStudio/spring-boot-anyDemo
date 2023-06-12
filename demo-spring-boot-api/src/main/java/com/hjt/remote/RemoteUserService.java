package com.hjt.remote;

import com.hjt.constant.CacheConstants;
import com.hjt.constant.ServiceNameConstants;
import com.hjt.domain.LoginUser;
import com.hjt.domain.R;
import com.hjt.domain.SysUser;
import com.hjt.factory.RemoteUserFallbackFactory;
import com.hjt.myException.AjaxResult;
import com.hjt.vo.DeptUserVO;
import com.hjt.vo.UserInfoListVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户服务
 *
 * @author hjt
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.HJT_PLATFORM_SYSTEM, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param userName 用户名
     * @return 结果
     */
    @GetMapping(value = "/userInfo/api/v1/sys-user/getInfoByUserName/{userName}")
    public R<LoginUser> getUserInfo(@PathVariable("userName") String userName);

    /**
     * 通过用户id查询用户信息
     *
     * @param userId 用户id
     * @return 结果
     */
    @PostMapping("/user/getInfoById")
    public R<LoginUser> getUserInfo(@RequestParam(value = "userId") Long userId);

    /**
     * 获取系统全部用户id
     *
     * @return 结果
     */
    @GetMapping("/user/getAllUserId")
    public R<Set<Long>> getAllUserId();

    /**
     * 通过用户id获取用户信息
     *
     * @return 结果
     */
    @PostMapping("/user/listByUseridIn")
    public R<Map<Long, UserInfoListVO>> listByUseridIn(@RequestBody List<Long> useridList);

    /**
     * 注册用户信息
     *
     * @param sysUser 用户信息
     * @param source 请求来源
     * @return 结果
     */
    @PostMapping("/user/register")
    public R<Boolean> registerUserInfo(@RequestBody SysUser sysUser, @RequestHeader(CacheConstants.FROM_SOURCE) String source);

    /**
     * 通过用户名查询用户信息
     *
     * @param deptIds 部门ID
     * @return 结果
     */
    @GetMapping(value = "/user/deptUserList/{deptIds}")
    public List<DeptUserVO> deptUserList(@PathVariable("deptIds") String deptIds, @RequestHeader(CacheConstants.FROM_SOURCE) String source);

    /***
     * 通过用户查岗位
     * @param username
     * @return
     */
    @GetMapping("/user/queryUser/{username}")
    public AjaxResult queryByUserName(@PathVariable("username") String username);

    /***
     * 状态修改
     * @param user
     * @return
     */
    @PutMapping("/user/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user);


    @PostMapping("/user/getUserIdInfoByUserName")
    public R<SysUser> getUserIdInfoByUserName(@RequestParam("userName") String userName);


    /***
     * 根据用户id获取秘钥
     * @param userId
     * @return
     */
    @PostMapping("/login/api/v1/sys-user/getSecretByUserId")
    public  AjaxResult getSecretByUserId(@RequestParam("userId") String userId);

}
