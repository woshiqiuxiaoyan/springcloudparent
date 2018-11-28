package com.yan.springcloudprovide.contorller;

import com.yan.springcloudprovide.service.UserService;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 10:11
 * @Version:
 */
@RestController
@RequestMapping("/testBoot")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{userId}")
    public String GetUser(@PathVariable("userId") String userId){
        return userService.query(userId).toString();
    }

}
