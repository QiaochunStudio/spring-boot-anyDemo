package com.hjt.message;


import lombok.Data;

import java.io.Serializable;

/**
 * @author hjt
 * @date 2019/04/04
 */
@Data
public class MessageTransaction<T> extends Message<T>  implements Serializable  {
    private String aId;
    private String bId;
    private T content;
}
