package com.hjt.bytetest;

public class ByteDemo {

    /***
     * 转换一个字节的方法
     * @param res
     * @return
     */
    public static byte[] int1byte(int res) {
        byte[] targets = new byte[1];
        targets[0] = (byte) (res & 0xff);// 最低位
        return targets;
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
    public static void main(String[] args) {
        // 创建一个byte数组
        byte[] chan = getChan(1);
        // 将byte数组转换为字符串
        String result = new String(chan);
        System.out.println(result);


    }
}
