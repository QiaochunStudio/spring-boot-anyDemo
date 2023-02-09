package com.hjt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 用户购买商品信息表
 * </p>
 *
 * @author hjt
 * @since 2022-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderProductInfo对象", description="用户购买商品信息表")
@TableName("tb_order_product_info")
public class OrderProductInfo extends Model<OrderProductInfo> {

    private static final long serialVersionUID = 1L;

    //@ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //@ApiModelProperty(value = "订单id")
    private Long orderId;


    //@ApiModelProperty(value = "用户id")
    private Long userId;

    //@ApiModelProperty(value = "商品id")
    private Long productId;

    //@ApiModelProperty(value = "数量")
    private Long count;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
