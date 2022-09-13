package com.hjt.message;

import lombok.Data;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * @author :hjt
 * @date : 2022/9/13
 */
@Data
public class RqMessage<T> implements Message<T> {
    private T data;
    private Long id;
    @Override
    public T getPayload() {
        return data;
    }

    @Override
    public MessageHeaders getHeaders() {
        return null;
    }
}
