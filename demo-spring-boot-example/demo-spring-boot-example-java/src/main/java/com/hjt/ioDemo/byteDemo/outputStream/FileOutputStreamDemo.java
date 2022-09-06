package com.hjt.ioDemo.byteDemo.outputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author :hjt
 * @date : 2022/9/6
 *  实现复制功能
 */
public class FileOutputStreamDemo {
    public static void main(String[] args) {
        int b = 0;
        FileInputStream in =null;
        FileOutputStream out = null;
        try { in =new FileInputStream("E:\\io\\test.txt");
            //下面的若是不存在的话会自动建立
            out = new FileOutputStream("E:\\io\\copyTest.txt");
            while ((b = in.read()) != -1) {
                out.write(b);
            } in .close();
            out.close();
        } catch(FileNotFoundException e) {
            System.out.println("找不到指定文件");
            System.exit( - 1);
        } catch(IOException e1) {
            System.out.println("文件复制错误");
            System.exit( - 1);

        }
        System.out.println("文件已复制");
    }
}
