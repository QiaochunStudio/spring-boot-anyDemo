package com.hjt.sms;

/**
 * @author hjt
 * @date:2022/8/21
 */
// This file is auto-generated, don't edit it. Thanks.

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import com.aliyun.tea.*;
import com.aliyun.dysmsapi20170525.*;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.*;
import com.aliyun.teaopenapi.models.*;
import com.aliyun.teautil.*;
import com.aliyun.teautil.models.*;
import com.google.gson.Gson;
import com.sun.media.jfxmedia.logging.Logger;

import java.util.HashMap;

public class Sample {

    /**
     * 这个模板是最新的模板 官方推荐用这个模板
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.dysmsapi20170525.Client client = Sample.createClient("LTAI5tHMtvXWyMrMNvhJ9MJB", "nCBFs8fooDSXhVwviwo9VQ0r11BZtZ");
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        //发送手机号码
        sendSmsRequest.setPhoneNumbers("13380756433");
        //模板名称
        sendSmsRequest.setSignName("ABC商城");
        //模板Code
        sendSmsRequest.setTemplateCode("SMS_197485345");
        //构建验证码
        HashMap<String,Object> map = new HashMap<>();
        map.put("code","520521");
        String strCode = JSONUtil.toJsonStr(map);
        sendSmsRequest.setTemplateParam(strCode);

        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            System.out.println(new Gson().toJson(sendSmsResponse));
        } catch (TeaException error) {
            // 如有需要，请打印 error
            Console.error(new Gson().toJson(com.aliyun.teautil.Common.assertAsString(error.message)));
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            Console.error(new Gson().toJson(com.aliyun.teautil.Common.assertAsString(error.message)));
        }
    }
}
