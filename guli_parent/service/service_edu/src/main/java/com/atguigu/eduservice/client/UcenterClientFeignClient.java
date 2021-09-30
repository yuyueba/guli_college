package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Component
public class UcenterClientFeignClient implements UcenterClient{


    @Override
    public HashMap getMemberInfoById(String id) {

        return (HashMap) new HashMap<>().put("message","错误");
    }
}
