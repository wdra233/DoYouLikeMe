package com.eric.projects.userclient;

import com.eric.projects.user.common.UserInfoForComments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user")
public interface UserClient {

    @GetMapping("/user/get-avatar")
    UserInfoForComments getAvatarByUserId(@RequestParam("userId") String userId);

}
