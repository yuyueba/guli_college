package com.atguigu.msmservice.service.impl;


import com.atguigu.msmservice.service.MsmService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {
    //发送短信
    @Override
    public boolean send(String code, String phone) {

        if(StringUtils.isEmpty(phone)) return false;


        Credential cred = new Credential("AKID1qQjmY3IGxNXeV5NQBfsqWw4wXrugBQN","iALFsFLAh38wTjiSv72g38xWooX0C7k7");//自己的腾讯key

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        SmsClient client = new SmsClient(cred, "", clientProfile);

        SendSmsRequest req = new SendSmsRequest();


        String[] phoneNumberSet1 = {"+86"+phone};//电话
        String[] templateParamSet1 = {code,"5"};//验证码
        req.set("PhoneNumberSet",phoneNumberSet1);
        req.set("TemplateParamSet", templateParamSet1);

        req.set("TemplateID","1135929");//模板
        req.set("SmsSdkAppid","1400575828");//在添加应用看生成的实际SdkAppid
        req.set("Sign","code天下");//签名


        try {
            SendSmsResponse resp = client.SendSms(req);

            System.out.println("1");
            System.out.println(SendSmsResponse.toJsonString(resp));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
