package com.hjt.hutool.json.jsonArray;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author :hjt
 * @date : 2022/9/13
 */
@Data
public class ProductDto implements Serializable {
    private Long id;

    private String proTitle;


    private Long stock;

    private Long orderId;

    private Long userId;






}
