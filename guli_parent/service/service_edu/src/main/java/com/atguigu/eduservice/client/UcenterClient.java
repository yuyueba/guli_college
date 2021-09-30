package com.atguigu.eduservice.client;



import com.atguigu.commonutils.R;
import com.atguigu.eduservice.config.EduConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@FeignClient(name = "service-ucenter", fallback = UcenterClientFeignClient.class) //调用的服务名称
@Component
public interface UcenterClient {
    @GetMapping("/educenter/member/getMemberById/{id}")
    public HashMap<String, String> getMemberInfoById(@PathVariable("id") String id);
}
