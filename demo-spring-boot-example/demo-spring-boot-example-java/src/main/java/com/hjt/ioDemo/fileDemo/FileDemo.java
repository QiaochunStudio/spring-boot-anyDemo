package com.hjt.ioDemo.fileDemo;

import java.io.*;

import static org.apache.catalina.startup.ExpandWar.delete;

/**
 * @author :hjt
 * @date : 2022/9/6
 */
public class FileDemo {
    //建立文件
    public static void testFile(){
        File f=new File("E:\\io\\hello.txt");
        try{
            f.createNewFile();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除文件
    public static void deleteFileTxt(){
        //删除文件
        String fileName="D:"+File.separator+"hello.txt";
        File f=new File(fileName);
        if(f.exists()){
            f.delete();
        }else{
            System.out.println("文件不存在");
        }
    }

    //创建文件夹
    public static void create(){
        String fileName="E:"+File.separator+"io"+File.separator+"hello";
        File f=new File(fileName);
        f.mkdir();
    }

    public static void insertData() throws IOException {
        String fileName="E:"+File.separator+"io"+File.separator+"hello.txt";
        File f=new File(fileName);
        OutputStream out =new FileOutputStream(f);
        String str="你好";
        byte[] b=str.getBytes();
        out.write(b);
        out.close();
    }

    // 向文件中追加新内容
    public static void insertExtraData() throws IOException {
        String fileName="E:"+File.separator+"io"+File.separator+"hello.txt";
        File f=new File(fileName);
        OutputStream out =new FileOutputStream(f,true);
        String str="Rollen";
        //String str="\r\nRollen";  可以换行
        byte[] b=str.getBytes();
        for (int i = 0; i < b.length; i++) {
            out.write(b[i]);
        }
        out.close();
    }

    // 读取文件内容
    public static void readData() throws IOException{
        String fileName="E:"+File.separator+"io"+File.separator+"hello.txt";
        File f=new File(fileName);
        InputStream in=new FileInputStream(f);
        byte[] b=new byte[1024];
        int count =0;
        int temp=0;
        while((temp=in.read())!=(-1)){
            b[count++]=(byte)temp;
        }
        in.close();
        System.out.println(new String(b));
    }

    //删除文件
    public static void deleteFile() throws IOException{
        String fileName="E:/github/new-hjt-code/用户列表.xlsx";
        File file = new File(fileName);
        File[] listFiles = file.listFiles();
        if(listFiles != null)
        {
            for(File f: listFiles)
            {
                if(f.isDirectory())
                {
                    delete(f);
                }
                else
                {
                    f.delete();
                }
            }
        }
        file.delete();
    }

    public static void main(String[] args) throws Exception {
        deleteFile();


    }
}
