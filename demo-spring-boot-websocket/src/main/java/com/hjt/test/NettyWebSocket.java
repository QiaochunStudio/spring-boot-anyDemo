package com.hjt.test;//package com.hjt.test;
//
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import io.netty.handler.codec.http.HttpHeaders;
//import io.netty.handler.timeout.IdleStateEvent;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections.map.MultiValueMap;
//import org.apache.commons.lang3.StringUtils;
//import com.hjt.annotation.*;
//import com.hjt.pojo.Session;
//
//import java.io.IOException;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @author leo
// * @date 2021/12/7 10:44
// */
//@ServerEndpoint(path = "imServer/{storeNo}", port = "${ws.port}")
//@Slf4j
//public class NettyWebSocket {
//
//    /**
//     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
//     */
//    private static int onlineCount = 0;
//
//    /**
//     * 接受客户端消息类型，1-心跳检测消息 2-客户端与客户端间的通信
//     */
//    private static final String MESSAGE_CODE = "1";
//
//    /**
//     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
//     */
//    private static ConcurrentHashMap<String, NettyWebSocket> webSocketMap = new ConcurrentHashMap<>();
//
//    /**
//     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
//     */
//    private Session session;
//
//    /**
//     * 接收店铺用户编号
//     */
//    private String storeNo = "";
//
//    @BeforeHandshake
//    public void handshake(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap){
//        session.setSubprotocols("stomp");
//        if (!"ok".equals(req)){
//            System.out.println("Authentication failed!");
//            session.close();
//        }
//    }
//
//
//
//    /**
//     * 连接建立成功调用的方法
//     * @param session 会话
//     */
//    @OnOpen
//    public void onOpen(Session session, HttpHeaders headers, @RequestParam String storeNo) {
//        log.debug("连接 webSocket 用户为:{}",storeNo);
//        this.session = session;
//        this.storeNo= storeNo;
//        if(webSocketMap.containsKey(storeNo)){
//            // 已经存在的连接，提示已经有人登录了
//            try {
//                sendMessage("用户已登录");
//                log.error("用户已登录 : {}", storeNo);
//            } catch (IOException e) {
//                log.error("连接webSocket 重复登录发送报警消息错误 原因:{}",e.getMessage());
//                e.printStackTrace();
//            }
//        }else{
//            //加入set中
//            webSocketMap.put(storeNo,this);
//            //在线数加1
//            addOnlineCount();
//            try {
//                sendMessage("连接成功");
//            } catch (IOException e) {
//                log.error("用户:"+storeNo+",网络异常!!!!!!");
//            }
//            log.info("用户连接:"+storeNo+",当前在线人数为:" + getOnlineCount());
//        }
//    }
//
//    /**
//     * 连接关闭的调用方法
//     */
//    @OnClose
//    public void onClose() {
//        if(webSocketMap.containsKey(storeNo)){
//            webSocketMap.remove(storeNo);
//            //从set中删除
//            subOnlineCount();
//        }
//        log.info("用户退出:"+storeNo+",当前在线人数为:" + getOnlineCount());
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     * @param message 客户端消息
//     * @param session 会话信息
//     */
//    @OnMessage
//    public void onMessage(String message, Session session) {
//        log.debug("用户消息:"+storeNo+",报文:"+message);
//
//        //可以群发消息
//        //消息保存到数据库、redis
//        if(StringUtils.isNotBlank(message)){
//            JSONObject json = (JSONObject) JSON.parse(message);
//            String code = json.get("code").toString();
//            String msg = json.get("msg").toString();
//            String data = json.get("data").toString();
//            try {
//                // code == 1 的时候是心跳检测
//                if (MESSAGE_CODE.equals(code)) {
//                    log.debug("心跳检测输出 code:{} msg:{} data:{}",code,msg,data);
//                    sendMessage("心跳检测回复");
//                } else {
//                    //解析发送的报文
//                    JSONObject jsonObject = JSON.parseObject(message);
//                    //追加发送人(防止串改)
//                    jsonObject.put("fromUserId",this.storeNo);
//                    String toStoreNo=jsonObject.getString("toStoreNo");
//
//                    //传送给对应toUserId用户的websocket
//                    if(StringUtils.isNotBlank(toStoreNo)&&webSocketMap.containsKey(toStoreNo)){
//                        webSocketMap.get(toStoreNo).sendMessage(jsonObject.toJSONString());
//                    }else{
//                        log.error("请求的storeNo:"+toStoreNo+"不在该服务器上");
//                        //否则不在这个服务器上，发送到mysql或者redis
//                    }
//                }
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * @param session 会话消息
//     * @param error 报错信息
//     */
//    @OnError
//    public void onError(Session session, Throwable error) {
//        log.error("用户错误:"+this.storeNo+",原因:"+error.getMessage());
//        error.printStackTrace();
//    }
//
//    /**
//     * 实现服务器主动推送
//     * @param message 消息
//     * @throws IOException 异常类
//     */
//    public void sendMessage(String message)  throws IOException {
//        this.session.sendText(message);
//    }
//
//    /**
//     * 实现服务器主动推送
//     * @param object 消息对象
//     * @throws IOException 异常类
//     */
//    public void sendMessageObject(Object object) throws IOException {
//        String message = JSON.toJSONString(object);
//        this.session.sendText(message);
//    }
//
//    public static void sendInfo(String message,String storeNo) throws IOException {
//        log.debug("发送消息到:"+storeNo+"，报文:"+message);
//        if(StringUtils.isNotBlank(storeNo)&&webSocketMap.containsKey(storeNo)){
//            webSocketMap.get(storeNo).sendMessage(message);
//        }else{
//            log.error("用户"+storeNo+",不在线！");
//        }
//    }
//
//    public static void sendInfoObject(Object object, String storeNo) throws IOException {
//        log.debug("发送消息到:"+storeNo+"，报文:"+object.toString());
//        if(StringUtils.isNotBlank(storeNo)&&webSocketMap.containsKey(storeNo)){
//            webSocketMap.get(storeNo).sendMessageObject(object);
//        }else{
//            log.error("用户"+storeNo+",不在线！");
//        }
//    }
//
//    public static void sendInfoObjectAllUser(Object object, Integer storeNo, Integer userNo) throws IOException {
//        log.debug("当前在线人数为:{}" , getOnlineCount());
//        for (String key:webSocketMap.keySet()) {
//            log.debug("for循环，当前用户:{}",key);
//            String[] wsStoreNo = key.split("_");
//            log.info("商家编号：{}，sockteid：{}", wsStoreNo[0], wsStoreNo[1]);
//            if (storeNo.toString().equals(wsStoreNo[0])) {
//                if (userNo != null && userNo.toString().equals(wsStoreNo[1])){
//                    log.info("发送消息给指定的socket");
//                    webSocketMap.get(key).sendMessageObject(object);
//                    log.debug("发送消息到:{}  ,  报文:{}",key,object.toString());
//                }
//                if (userNo == null){
//                    log.info("发送消息全部socket");
//                    webSocketMap.get(key).sendMessageObject(object);
//                    log.debug("发送消息到:{}  ,  报文:{}",key,object.toString());
//                }
//            }
//
//        }
//    }
//
//    @OnBinary
//    public void onBinary(Session session, byte[] bytes) {
//        for (byte b : bytes) {
//            System.out.println(b);
//        }
//        session.sendBinary(bytes);
//    }
//
//    @OnEvent
//    public void onEvent(Session session, Object evt) {
//        if (evt instanceof IdleStateEvent) {
//            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
//            switch (idleStateEvent.state()) {
//                case READER_IDLE:
//                    System.out.println("read idle");
//                    break;
//                case WRITER_IDLE:
//                    System.out.println("write idle");
//                    break;
//                case ALL_IDLE:
//                    System.out.println("all idle");
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static synchronized void addOnlineCount() {
//        NettyWebSocket.onlineCount++;
//    }
//
//    public static synchronized void subOnlineCount() {
//        NettyWebSocket.onlineCount--;
//    }
//
//    public static String getInspectUser(String storeNo){
//        // 检查如果有人登录了 就生成一个新的号码返回给前端
//        log.info("{}：检查是否存在的连接",storeNo);
//        String storeNoCopy ;
//        int num = 1;
//        while (true){
//            storeNoCopy = storeNo + "_" + num;
//            if(webSocketMap.containsKey(storeNoCopy)){
//                num++;
//            }else{
//                break;
//            }
//        }
//        return storeNoCopy;
//    }
//}
//
