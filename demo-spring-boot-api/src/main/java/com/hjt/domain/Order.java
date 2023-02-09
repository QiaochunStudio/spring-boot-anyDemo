package com.hjt.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单信息
 * </p>
 *
 * @author hjt
 * @since 2022-09-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Order对象", description="订单信息")
@TableName(value = "tb_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    //@ApiModelProperty(value = "订单ID")
    @TableId(value = "order_id")
    private Long orderId;

    //@ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    //@ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


    //@ApiModelProperty(value = "用户ID")
    private Long userId;

    //@ApiModelProperty(value = "配送类型：无需快递")
    private Integer deliveryType;


    //@ApiModelProperty(value = "总值")
    private BigDecimal total;

    //@ApiModelProperty(value = "订单状态 1:待付款 2:待发货 3:待收货(已发货) 5:成功 6:失败")
    private Integer status;

    //@ApiModelProperty(value = "订单商品总数")
    private Long allCount;

    //@ApiModelProperty(value = "付款时间")
    private LocalDateTime payTime;

    //@ApiModelProperty(value = "发货时间")
    private LocalDateTime deliveryTime;

    //@ApiModelProperty(value = "完成时间")
    private LocalDateTime finallyTime;

    //@ApiModelProperty(value = "结算时间")
    private LocalDateTime settledTime;

    //@ApiModelProperty(value = "取消时间")
    private LocalDateTime cancelTime;

    //@ApiModelProperty(value = "是否已支付，1.已支付0.未支付")
    private Boolean isPayed;

    //@ApiModelProperty(value = "订单关闭原因 1-超时未支付 4-买家取消 15-已通过货到付款交易")
    private Integer closeType;

    //@ApiModelProperty(value = "用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除")
    private Integer deleteStatus;

    //@ApiModelProperty(value = "订单版本号，每处理一次订单，版本号+1")
    private Integer version;

    //@ApiModelProperty(value = "用户订单地址id")
    private Long orderAddrId;

    //@ApiModelProperty(value = "商品id")
    private String proId;

    //@ApiModelProperty(value = "是否是购物车生成订单，1.是 0否（默认是0）")
    private Boolean isShopCar;


    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

}
