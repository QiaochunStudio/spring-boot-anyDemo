package com.hjt.netty.udpdemo;

import java.io.IOException;
import java.net.*;




public class UdpClient {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            System.out.println("客户端已创建");

            //要发送的数据
            String str = "hello,服务端--";
            //将字符串转为字节数组
            byte[] b = getChan(1);
//            byte[] b = str.getBytes();
            //创建连接的ip地址
            InetAddress inetAddress = InetAddress.getByName("192.168.1.80");

            //创建存储数据的包，传入数组数据，网络连接的IP地址,端口号
            DatagramPacket datagramPacket = new DatagramPacket(b, b.length,inetAddress,8000);
            //发送数据
            client.send(datagramPacket);
            System.out.println("数据发送成功");

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static byte[] getChan(Integer chan) {
        byte[] bytes = new byte[8];
        if (chan == 1) {
            //01 03 00 00 00 02 C4 0B
            bytes[0] = int1byte(1)[0];
            bytes[1] = int1byte(3)[0];
            bytes[2] = int1byte(0)[0];
            bytes[3] = int1byte(0)[0];
            bytes[4] = int1byte(0)[0];
            bytes[5] = int1byte(2)[0];
            bytes[6] = int1byte(196)[0];
            bytes[7] = int1byte(11)[0];
        }
        if (chan == 2) {
            //02 03 00 00 00 02 C4 38
            bytes[0] = int1byte(2)[0];
            bytes[1] = int1byte(3)[0];
            bytes[2] = int1byte(0)[0];
            bytes[3] = int1byte(0)[0];
            bytes[4] = int1byte(0)[0];
            bytes[5] = int1byte(2)[0];
            bytes[6] = int1byte(196)[0];
            bytes[7] = int1byte(56)[0];
        }
        if (chan == 3) {
            //03 03 00 00 00 02 C5 E9
            bytes[0] = int1byte(3)[0];
            bytes[1] = int1byte(3)[0];
            bytes[2] = int1byte(0)[0];
            bytes[3] = int1byte(0)[0];
            bytes[4] = int1byte(0)[0];
            bytes[5] = int1byte(2)[0];
            bytes[6] = int1byte(197)[0];
            bytes[7] = int1byte(233)[0];
        }
        if (chan == 4) {
            //04 03 00 00 00 02 C4 5E
            bytes[0] = int1byte(4)[0];
            bytes[1] = int1byte(3)[0];
            bytes[2] = int1byte(0)[0];
            bytes[3] = int1byte(0)[0];
            bytes[4] = int1byte(0)[0];
            bytes[5] = int1byte(2)[0];
            bytes[6] = int1byte(196)[0];
            bytes[7] = int1byte(94)[0];
        }
        return bytes;
    }

    /**
     * 转换1个字节的方法
     *
     * @param res
     * @return
     */
    public static byte[] int1byte(int res) {
        byte[] targets = new byte[1];
        targets[0] = (byte) (res & 0xff);// 最低位
        return targets;
    }
}
