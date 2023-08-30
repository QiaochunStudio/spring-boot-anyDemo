package com.hjt.hutool.http;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 消息模块-公告消息
 * </p>
 *
 * @author hjt
 * @since 2023-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TMessageNotice implements Serializable {

    private static final long serialVersionUID = 1L;



    private String id;


    private String noticeContenet;

    private Date creatTime;

    private Date updateTime;

    private Long createBy;

    private Long updateBy;

    private Integer messageType;

    private Boolean isDelete;

    private Integer isGlobalNotice;


    private String commodityEffect;



}
