package com.hjt.ioDemo.byteDemo.outputStream;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author :hjt
 * @date : 2022/9/6
 */
public class OutputStreamDemo {

    //根目录下的template目录
    public static final String template = System.getProperty("user.dir").concat(File.separator + "template" + File.separator);
    //测试hutool删除
    public static void deleteByHutool(){
        boolean del = FileUtil.del("E:/github/new-hjt-code/用户列表.xlsx");
        if(del){
            System.out.println("删除成功");
        }
        else{
            System.out.println("删除失败");
        }
    }
    public static void writeData() throws IOException{
        {
            // 第1步、使用File类找到一个文件
            // 声明File对象
            File f = new File("E:\\io\\test.txt");
            // 第2步、通过子类实例化父类对象  准备好一个输出的对象
            OutputStream out = null;
            try {
                // 通过对象多态性，进行实例化
                out = new FileOutputStream(f);
                // 第3步、进行写操作 准备一个字符串
                String str = "欢迎来到www.51gjie.com";
                // 只能输出byte数组，所以将字符串变为byte数组
                byte b[] = str.getBytes();
                // 将内容输出，保存文件
                out.write(b);
            } catch (IOException e) {
            } finally {
                // 关闭输出流
                out.close();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        deleteByHutool();
    }
}
