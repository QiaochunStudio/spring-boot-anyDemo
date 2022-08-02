package com.hjt.transaction;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hjt
 * @date 2019/8/20
 */
@Slf4j
//@RocketMQTransactionListener(txProducerGroup = "tx-group")  2.0.3的版本有这个
@RocketMQTransactionListener
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    /***
     * 存放事务的状态 支持并发的场景
     */
    private ConcurrentHashMap<String, Integer> localTrans = new ConcurrentHashMap<>();

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        log.info("==============进到这里说明 Half Message 发送成功");
        //获取队列中的事务id
        String rocketmqTransactionId = getRocketmqTransactionId(msg);
        try{
            //模拟 执行A服务-100元操作
            int redMoneyByA = -100;
            //定义
            // 0 是中间状态  1 是提交事务状态 2是回滚事务
            localTrans.put(rocketmqTransactionId,1);
            //模拟 执行A服务-100元操作失败
            int redMoneyExceptionByA = 100/0;
            return RocketMQLocalTransactionState.UNKNOWN;
        }catch (Exception e){
            // 执行A服务-100元操作出现异常就  事务回查 调用下面的checkLocalTransaction方法
            localTrans.put(rocketmqTransactionId,2);
            log.error("插入数据库失败,原因为：{}",e.getMessage());
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("============== 模拟回查本地事务 checkLocalTransaction");
        Object payload = msg.getPayload();
        MessageHeaders headers = msg.getHeaders();
        System.out.println("输出：" + payload);
        System.out.println("输出：" + headers);
        String rocketmqTransactionId = getRocketmqTransactionId(msg);
        //查rocketmqTransactionId的事务状态
        Integer status = localTrans.get(rocketmqTransactionId);
        if(null!=status){
            switch (status){
                case 0:
                    log.info("============== 模拟回查本地事务结束 提交状态为：UNKNOWN");
                    return RocketMQLocalTransactionState.UNKNOWN;
                case 1:
                    log.info("============== 模拟回查本地事务结束 提交状态为：COMMIT");
                    return RocketMQLocalTransactionState.COMMIT;
                case 2:
                    log.info("============== 模拟回查本地事务结束 提交状态为：ROLLBACK");
                    return RocketMQLocalTransactionState.ROLLBACK;
            }
        }
        log.info("============== 模拟回查本地事务结束,提交状态为 ROLLBACK");
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    /***
     * 获取事务id
     * @param msg
     * @return
     */
    public  String getRocketmqTransactionId(Message msg){
        JSONObject json = JSONUtil.parseObj(msg.getHeaders(), false, true);
        String rocketmqTransactionId = (String)json.get("rocketmq_TRANSACTION_ID");
        String topic = (String)json.get("rocketmq_TOPIC");
        log.info("=======事务id========{}",rocketmqTransactionId);
        log.info("=======topic========{}",topic);
        if(!StringUtils.isEmpty(rocketmqTransactionId)){
            return rocketmqTransactionId;
        }
        return "";
    }
}
