package com.hjt.sms;

/**
 * @author hjt
 * @date:2022/8/21
 */
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.dysmsapi.model.v20170525.*;

public class SendSms {

    public static void main(String[] args) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "", "");
        /** use STS Token
         DefaultProfile profile = DefaultProfile.getProfile(
         "<your-region-id>",           // The region ID
         "<your-access-key-id>",       // The AccessKey ID of the RAM account
         "<your-access-key-secret>",   // The AccessKey Secret of the RAM account
         "<your-sts-token>");          // STS Token
         **/

        IAcsClient client = new DefaultAcsClient(profile);

        SendSmsRequest request = new SendSmsRequest();
//发送手机号码
        request.setPhoneNumbers("");
        //模板名称
        request.setSignName("ABC商城");
        //模板Code
        request.setTemplateCode("");
        //构建验证码
        HashMap<String,Object> map = new HashMap<>();
        map.put("code","1314");
        String s = JSONUtil.toJsonStr(map);
        request.setTemplateParam(s);

        try {
            SendSmsResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }
}
