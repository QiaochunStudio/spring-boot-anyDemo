package com.hjt.testSysUser.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hjt
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysUser", description="")
public class SysUser extends Model<SysUser> {

    //@ApiModelProperty(value = "用户ID")
    @ExcelProperty("用户ID")
    @ColumnWidth(10)
    private Long userId;

    //@ApiModelProperty(value = "部门ID")
    @ExcelProperty("部门ID")
    @ColumnWidth(10)
    private Long deptId;

    //@ApiModelProperty(value = "用户账号")
    @ExcelProperty("用户账号")
    @ColumnWidth(10)
    private String userName;

    //@ApiModelProperty(value = "用户昵称")
    @ExcelProperty("用户昵称")
    @ColumnWidth(10)
    private String nickName;

    @ExcelProperty("用户类型（00系统用户）")
    @ColumnWidth(10)
    private String userType;

    @ExcelProperty("用户邮箱")
    @ColumnWidth(10)
    private String email;

    @ExcelProperty("手机号码")
    @ColumnWidth(10)
    private String phonenumber;

    @ExcelProperty("用户性别（0男 1女 2未知）")
    @ColumnWidth(10)
    private String sex;

    @ExcelProperty("头像地址")
    @ColumnWidth(10)
    private String avatar;

    @ExcelProperty("密码")
    @ColumnWidth(10)
    private String password;

    @ExcelProperty("帐号状态（0正常 1停用）")
    @ColumnWidth(10)
    private String status;

    @ExcelProperty("删除标志（0代表存在 2代表删除）")
    @ColumnWidth(10)
    private String delFlag;

    @ExcelProperty("最后登录IP")
    @ColumnWidth(10)
    private String loginIp;

    @ExcelProperty("最后登录时间")
    @ColumnWidth(10)
    private LocalDateTime loginDate;

    @ExcelProperty("创建者")
    @ColumnWidth(10)
    private String createBy;

    @ExcelProperty("创建时间")
    @ColumnWidth(10)
    private LocalDateTime createTime;

    @ExcelProperty("更新者")
    @ColumnWidth(10)
    private String updateBy;

    @ExcelProperty("更新时间")
    @ColumnWidth(10)
    private LocalDateTime updateTime;

    @ExcelProperty("备注")
    @ColumnWidth(10)
    private String remark;

    @ExcelProperty("主页")
    @ColumnWidth(10)
    private String homePage;

    @ExcelProperty("兴趣")
    @ColumnWidth(10)
    private String interest;

    @ExcelProperty("所属组ids")
    @ColumnWidth(10)
    private String groupIds;

    @ExcelProperty("粤证易openId")
    @ColumnWidth(10)
    private String yzyOpenId;

    @ExcelProperty("入职时间")
    @ColumnWidth(10)
    private LocalDateTime inductionTime;

    @ExcelProperty("用户token")
    @ColumnWidth(10)
    private String loginToken;

    @ExcelProperty("token秘钥")
    @ColumnWidth(10)
    private String secretKey;

    @ExcelProperty("盐")
    @ColumnWidth(10)
    private String salt;



}
