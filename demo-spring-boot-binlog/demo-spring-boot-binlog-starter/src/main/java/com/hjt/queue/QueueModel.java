package com.hjt.queue;

import com.hjt.annotation.RowListenerModel;
import com.hjt.event.CustomEvent;
import lombok.Data;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/13
 */
@Data
public class QueueModel {

    private RowListenerModel rowListenerModel;
    private CustomEvent customEvent;

}
