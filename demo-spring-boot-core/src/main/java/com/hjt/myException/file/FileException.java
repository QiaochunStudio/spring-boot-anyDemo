package com.hjt.myException.file;


import com.hjt.myException.BaseException;

/**
 * 文件信息异常类
 *
 * @author hjt
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
