package com.hjt.netty.udpdemo;



import java.io.IOException;
import java.net.*;

/**
 * @author 欧欧
 * @version 1.0
 */
public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        //1. create DatagramSocket
        DatagramSocket datagramSocket = new DatagramSocket(8888);

        //2. encapsulate data to DatagramPacket
        byte[] data = "hello, 明天吃火锅".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 9999);

        //3. send data
        datagramSocket.send(datagramPacket);

        // reeive data
        byte[] buf = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
        //3. call receive
        System.out.println("Receiver B is waiting...");
        datagramSocket.receive(receivePacket);

        //4. extract data from packet
        int length = receivePacket.getLength(); // actual data length
        byte[] receive = receivePacket.getData();

        String s = new String(receive, 0, length);
        System.out.println(s);

        //4. close
        datagramSocket.close();
        System.out.println("B exit");

    }
}
