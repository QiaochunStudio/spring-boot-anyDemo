package com.hjt.netty.udp;

/***
 * 非阻塞udp
 */




import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;



@Slf4j
public class NonBlockingUDPClient {
//    private static final String SERVER_HOST = "192.168.1.80";
//    private static final int SERVER_PORT = 8000;



//    @Scheduled(cron = "*/30 * * * * ?")
    public void uploadOil(){
        try {
            System.out.println("====开始进来数据===");
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(false);

            Selector selector = Selector.open();
            datagramChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            Thread.sleep(2000);
            InetSocketAddress serverAddress = new InetSocketAddress("192.168.1.80", 8000);

            // 发送数据
            byte[] data = getChan(1);
            ByteBuffer sendBuffer = ByteBuffer.wrap(data);
            datagramChannel.send(sendBuffer, serverAddress);
            // 设置为非阻塞模式，以便接收数据
            datagramChannel.configureBlocking(false);

            selector.select();
            Thread.sleep(3000);
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                // 接收数据
                ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
                InetSocketAddress senderAddress = (InetSocketAddress) datagramChannel.receive(receiveBuffer);
                receiveBuffer.flip();

                byte[] receivedData = new byte[receiveBuffer.remaining()];
                receiveBuffer.get(receivedData);

                //将数组转为字符串
                String bufHexStr = getBufHexStr(receivedData);
//                        String receivedMessage = new String(receivedData);
                System.out.println("Received from " + bufHexStr);

            }

        } catch (Exception e) {
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


    public static String getBufHexStr(byte[] bytes) {
        String HEXES = "0123456789ABCDEF";
        if (bytes == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        final StringBuilder hex = new StringBuilder(2 * bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            final byte b = bytes[i];
            sb.append(b);
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
            if (i + 1 != bytes.length) {
                sb.append(" ");
                hex.append(" ");
            }
        }
        /*for (final byte b : bytes) {
            sb.append(b);
            sb.append(" ");
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F))).append(" ");
        }*/
        return hex.toString();
    }


    public static void main(String[] args) {
        new NonBlockingUDPClient().uploadOil();
    }
}
