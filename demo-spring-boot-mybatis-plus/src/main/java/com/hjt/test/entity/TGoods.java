package com.hjt.test.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author hjt
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TGoods对象", description="商品表")
//public class TGoods extends Model<TGoods> {
public class TGoods  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDateTime;

    @ApiModelProperty(value = "创建者ID")
    private Long createUserId;

    @ApiModelProperty(value = "创建者名字")
    private String createUserName;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateDateTime;

    @ApiModelProperty(value = "更新者ID")
    private Long updateUserId;

    @ApiModelProperty(value = "更新者名字")
    private String updateUserName;

    @ApiModelProperty(value = "启用状态：0-不启用；1-启用")
    private Boolean enabledState;

    @ApiModelProperty(value = "逻辑删除状态：0-不删除；1-删除")
    private Boolean deletedState;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;




}
