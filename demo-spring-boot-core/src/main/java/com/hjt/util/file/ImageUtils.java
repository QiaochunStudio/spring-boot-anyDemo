package com.hjt.util.file;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * 图片处理工具类
 *
 * @author hjt
 */
public class ImageUtils
{
    private static final Logger log = LoggerFactory.getLogger(ImageUtils.class);

    public static byte[] getImage(String imagePath)
    {
        InputStream is = getFile(imagePath);
        try
        {
            return IOUtils.toByteArray(is);
        }
        catch (Exception e)
        {
            log.error("图片加载异常 {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(is);
        }
    }

    public static InputStream getFile(String imagePath)
    {
        try
        {
            byte[] result = readFile(imagePath);
            result = Arrays.copyOf(result, result.length);
            return new ByteArrayInputStream(result);
        }
        catch (Exception e)
        {
            log.error("获取图片异常 {}", e);
        }
        return null;
    }

    /**
     * 读取文件为字节数据
     *
     * @param url 地址
     * @return 字节数据
     */
    public static byte[] readFile(String url)
    {
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try
        {
            // 网络地址
            URL urlObj = new URL(url);
            URLConnection urlConnection = urlObj.openConnection();
            urlConnection.setConnectTimeout(30 * 1000);
            urlConnection.setReadTimeout(60 * 1000);
            urlConnection.setDoInput(true);
            in = urlConnection.getInputStream();
            return IOUtils.toByteArray(in);
        }
        catch (Exception e)
        {
            log.error("访问文件异常 {}", e);
            return null;
        }
        finally
        {
            IOUtils.closeQuietly(baos);
        }
    }

    /***
     * 经纬度坐标格式转换
     * @param Gps
     */
    public static double latitude_and_longitude_convert_to_decimal_system(String Gps) {
        String a = Gps.split("°")[0].replace(" ", "");
        String b = Gps.split("°")[1].split("'")[0].replace(" ", "");
        String c = Gps.split("°")[1].split("'")[1].replace(" ", "").replace("\"", "");
        double gps_dou = Double.parseDouble(a)+Double.parseDouble(b)/60 + Double.parseDouble(c)/60/60;
        return gps_dou;
    }

    /*读取照片信息*/
    public static void readPicInfo(String file_path) throws Exception {

        File mFile = new File(file_path);
        Metadata metadata = ImageMetadataReader.readMetadata(mFile);
        for (Directory directory : metadata.getDirectories()) {
            System.out.println(directory.getTags());
            System.out.println(directory.getClass().getSimpleName());
            if("ExifSubIFDDirectory".equalsIgnoreCase( directory.getClass().getSimpleName() )){

                //光圈F值=镜头的焦距/镜头光圈的直径
                System.out.println("光圈值: f/" + directory.getString(ExifSubIFDDirectory.TAG_FNUMBER) );

                System.out.println("曝光时间: " + directory.getString(ExifSubIFDDirectory.TAG_EXPOSURE_TIME)+ "秒" );
                System.out.println("ISO速度: " + directory.getString(ExifSubIFDDirectory.TAG_ISO_EQUIVALENT) );
                System.out.println("焦距: " + directory.getString(ExifSubIFDDirectory.TAG_FOCAL_LENGTH) + "毫米" );

                System.out.println("拍照时间: " + directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL) );
                System.out.println("宽: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_WIDTH) );
                System.out.println("高: " + directory.getString(ExifSubIFDDirectory.TAG_EXIF_IMAGE_HEIGHT) );

            }

            if("ExifIFD0Directory".equalsIgnoreCase( directory.getClass().getSimpleName() )){
                System.out.println("照相机制造商: " + directory.getDescription(ExifIFD0Directory.TAG_MAKE) );
                System.out.println("照相机型号: " + directory.getString(ExifIFD0Directory.TAG_MODEL) );
                System.out.println("水平分辨率: " + directory.getString(ExifIFD0Directory.TAG_X_RESOLUTION) );
                System.out.println("垂直分辨率: " + directory.getString(ExifIFD0Directory.TAG_Y_RESOLUTION) );
            }

            //GPS 信息
            if("GpsDirectory".equalsIgnoreCase( directory.getClass().getSimpleName() )){
                System.out.println("经度: " + latitude_and_longitude_convert_to_decimal_system(directory.getDescription(GpsDirectory.TAG_LONGITUDE)) );
                System.out.println("纬度: " + latitude_and_longitude_convert_to_decimal_system(directory.getDescription(GpsDirectory.TAG_LATITUDE)) );
                System.out.println("海拔高度: " + directory.getDescription(GpsDirectory.TAG_ALTITUDE) );
            }
        }
    }


    public static void main(String[] args) throws Exception {
        //传入照片的绝对路径
        readPicInfo("C:\\Users\\hjt\\Desktop\\123.jpg");
    }
}
