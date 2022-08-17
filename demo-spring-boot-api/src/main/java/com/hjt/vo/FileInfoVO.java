package com.hjt.vo;

/**
 * @Description: 系统文件信息返回值封装
 * @Author: hjt  <lin652210786@163.com>
 * @Date: 2021/06/24
 */
public class FileInfoVO {
    private String fileId;

    private String fileOriginalName;

    /** 自动生成的文件名 */
    private String fileName;

    /** 文件MD5值 */
    private String filemd5;

    /** 文件类型 */
    private String fileType;

    /** 文件访问地址 */
    private String fileUrl;

    /** 文件大小，单位：kb */
    private Long fileSize;

    /** 上传人id */
    private Long uploadUserId;

    /** 上传人名字 */
    private String uploadUserName;

    /** 当前文件块 序号 */
    private Integer chunk;

    /** 总的文件块 */
    private Integer chunks;

    public String getFileOriginalName() {
        return fileOriginalName;
    }

    public void setFileOriginalName(String fileOriginalName) {
        this.fileOriginalName = fileOriginalName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilemd5() {
        return filemd5;
    }

    public void setFilemd5(String filemd5) {
        this.filemd5 = filemd5;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Long uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }

    public Integer getChunks() {
        return chunks;
    }

    public void setChunks(Integer chunks) {
        this.chunks = chunks;
    }
    public String getFileId() {
        return this.fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
