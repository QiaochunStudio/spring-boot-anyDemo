package com.hjt;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.util.MultiValueMap;
import com.hjt.annotation.*;
import com.hjt.pojo.Session;

import java.io.IOException;
import java.util.Map;

/***
 * @author hjt
 * 注解描述@ServerEndpoint
 * 在Spring配置中声明ServerEndpointExporter，
 * 它将扫描带有ServerEndpoint注释的WebSocket端点。
 * 使用ServerEndpoint标注的bean将被注册为WebSocket端点。
 * 所有配置都在这个注释中(例如@ServerEndpoint("/ws"))
 */
//@ServerEndpoint(path = "/ws/{arg}")
@ServerEndpoint(host = "${ws.host}",port = "${ws.port}",path = "/test")
public class MyWebSocket {

    /***
     * 翻译结果
     * 当有连接被接受时，使用@BeforeHandshake 注解的方法将被调用
     * 注入方法的类有：Session、HttpHeaders...
     * @param session
     * @param headers
     * @param req
     * @param reqMap
     * @param arg
     * @param pathMap
     */
    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap){
        session.setSubprotocols("stomp");
//        if (!"ok".equals(req)){
//            System.out.println("Authentication failed!");
//            session.close();
//        }
    }

    /***
     * 当 WebSocket 连接完成时，会调用带有 @OnOpen 注解的方法
     * 注入方法的类有：Session、HttpHeaders...
     * @param session
     * @param headers
     * @param req
     * @param reqMap
     * @param arg
     * @param pathMap
     */
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String req, @RequestParam MultiValueMap reqMap, @PathVariable String arg, @PathVariable Map pathMap){
        System.out.println("new connection");
        System.out.println(req);
    }

    /***
     * 当 WebSocket 连接关闭时，使用 @OnClose 注解的方法将被调用，注入到该方法的类是：Session
     * @param session
     * @throws IOException
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
    }

    /***
     * 当 WebSocket 连接抛出 Throwable 时，被 @OnError 注解的方法会被调用，注入到方法中的类有：Session、Throwable
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    /***
     * 当 WebSocket 连接收到消息时，使用 @OnMessage 注解的方法将被调用，注入到方法中的类有：Session、String
     * @param session
     * @param message
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
        session.sendText("Hello Netty!");
    }

    /***
     * 当 WebSocket 连接接收到二进制文件时，使用@OnBinary 注解的方法将被调用，注入方法的类有：Session,byte[]
     * @param session
     * @param bytes
     */
    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    /***
     * 当一个WebSocket连接接收到Netty的事件时，被@OnEvent注解的方法会被调用，注入到方法中的类有：Session、Object
     * @param session
     * @param evt
     */
    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

}
