package com.hjt.domain;


import com.hjt.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 系统文件对象
 *
 * @author hjt
 * @date 2021-06-23
 */
public class SysFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 表id */
    private Long fileId;

    /** 原始文件名 */
    @Excel(name = "原始文件名")
    private String fileOriginalName;

    /** 自动生成的文件名 */
    @Excel(name = "自动生成的文件名")
    private String fileName;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 文件储存路径 */
    @Excel(name = "文件储存路径")
    private String filePath;

    /** 文件访问地址 */
    @Excel(name = "文件访问地址")
    private String fileUrl;

    /** 文件大小，单位：kb */
    @Excel(name = "文件大小，单位：kb")
    private Long fileSize;

    /** 上传人id */
    @Excel(name = "上传人id")
    private Long uploadUserId;

    /** 上传人名字 */
    @Excel(name = "上传人名字")
    private String uploadUserName;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    private String mimeType;

    public void setFileId(Long fileId)
    {
        this.fileId = fileId;
    }

    public Long getFileId()
    {
        return fileId;
    }
    public void setFileOriginalName(String fileOriginalName)
    {
        this.fileOriginalName = fileOriginalName;
    }

    public String getFileOriginalName()
    {
        return fileOriginalName;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getFileType()
    {
        return fileType;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

    public String getFileUrl()
    {
        return fileUrl;
    }
    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize()
    {
        return fileSize;
    }
    public void setUploadUserId(Long uploadUserId)
    {
        this.uploadUserId = uploadUserId;
    }

    public Long getUploadUserId()
    {
        return uploadUserId;
    }
    public void setUploadUserName(String uploadUserName)
    {
        this.uploadUserName = uploadUserName;
    }

    public String getUploadUserName()
    {
        return uploadUserName;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("fileId", getFileId())
                .append("fileOriginalName", getFileOriginalName())
                .append("fileName", getFileName())
                .append("fileType", getFileType())
                .append("filePath", getFilePath())
                .append("fileUrl", getFileUrl())
                .append("fileSize", getFileSize())
                .append("uploadUserId", getUploadUserId())
                .append("uploadUserName", getUploadUserName())
                .append("delFlag", getDelFlag())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("mimeType", getMimeType())
                .toString();
    }
}
