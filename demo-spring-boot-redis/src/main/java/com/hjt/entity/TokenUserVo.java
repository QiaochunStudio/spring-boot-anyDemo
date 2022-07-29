package com.hjt.entity;


import lombok.Data;

import java.io.Serializable;

/***
 * @author hjt
 * redis存放的用户信息Vo 后续可根据需要自己添加
 */
@Data
public class TokenUserVo implements Serializable {
    private Long userId;
    private String userName;
}