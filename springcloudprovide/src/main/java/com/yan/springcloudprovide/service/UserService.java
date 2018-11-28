package com.yan.springcloudprovide.service;

import com.yan.springcloudprovide.entity.User;
import com.yan.springcloudprovide.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 10:08
 * @Version:
 */
@Service("userService")
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User Sel(int id){
        return userMapper.Sel(id);
    }

}
