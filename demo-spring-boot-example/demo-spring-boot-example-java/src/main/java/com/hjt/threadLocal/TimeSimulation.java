package com.hjt.threadLocal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSimulation {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        String startTimeStr = "07:51:05";
        String endTimeStr = "09:06:27";

        try {
            Date startTime = sdf.parse(startTimeStr);
            Date endTime = sdf.parse(endTimeStr);

            long timeDifference = endTime.getTime() - startTime.getTime();
            long currentTime = startTime.getTime();

            while (currentTime <= endTime.getTime()) {
                Date currentDateTime = new Date(currentTime);
                String formattedTime = sdf.format(currentDateTime);
                System.out.println(formattedTime);

                Thread.sleep(1000); // 休眠1秒

                currentTime += 1000; // 增加1秒
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

