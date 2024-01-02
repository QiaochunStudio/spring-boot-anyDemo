package com.hjt.netty.udpdemo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;



public class UdpService {
    public static void main(String[] args) {
        try{
            //创建服务端
            DatagramSocket server = new DatagramSocket(8000);
            System.out.println("服务端已开启...");
            //定义接受数组的字节数组
            byte[] b =new byte[1024];
            //创建数据包存入数组中的数据
            DatagramPacket datagramPacket= new DatagramPacket(b,b.length);

            //接受客户端数据
            server.receive(datagramPacket);

            //获取客户端发送的数据，返回带存储数据的字节数组
            byte[] data = datagramPacket.getData();
            //将数组转为字符串
            String s = new String(data);
            //打印客户端发送的消息
            System.out.println("客户端："+s);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

