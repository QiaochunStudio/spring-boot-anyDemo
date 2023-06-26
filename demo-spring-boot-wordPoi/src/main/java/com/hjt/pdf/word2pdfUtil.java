package com.hjt.pdf;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import lombok.Cleanup;

import java.io.*;

/**
 * @author hjt
 * @date 2023/6/25
 **/
public class word2pdfUtil {

    /**
     * 转化成输出流方式
     * @param docxInputStream
     * @return
     */
    public static OutputStream getPdfOutputStream(InputStream docxInputStream) throws RuntimeException {
        OutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            IConverter converter = LocalConverter.builder().build();
            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return outputStream;
    }

    /**
     * 转本地文件
     */
    public static void toFiles() {
        File inputWord = new File("D:\\testWord\\out_test.docx");
        File outputFile = new File("D:\\testWord\\out_test.pdf");
        try  {
            @Cleanup InputStream docxInputStream = new FileInputStream(inputWord);
            @Cleanup OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(docxInputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();
            System.out.println("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        toFiles();
    }
}
