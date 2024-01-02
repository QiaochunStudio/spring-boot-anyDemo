package com.hjt.netty.udpdemo;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author 欧欧
 * @version 1.0
 */
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1. Create DatagramSocket, ready for receiving data from port 9999
        DatagramSocket socket = new DatagramSocket(9999);
        //2. Constructor DatagramPacket, (UDP - max size 64k)
        byte[] buf = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
        //3. call receive
        System.out.println("Receiver A is waiting...");
        socket.receive(datagramPacket);

        //4. extract data from packet
        int length = datagramPacket.getLength(); // actual data length
        byte[] data = datagramPacket.getData();

        String s = new String(data, 0, length);
        System.out.println(s);

        byte[] reply = "好的, 明天见".getBytes();
        DatagramPacket replyPacket = new DatagramPacket(reply, reply.length, InetAddress.getByName("127.0.0.1"), 8888);
        socket.send(replyPacket);
        //5. Close
        socket.close();
        System.out.println("A exit");

    }
}