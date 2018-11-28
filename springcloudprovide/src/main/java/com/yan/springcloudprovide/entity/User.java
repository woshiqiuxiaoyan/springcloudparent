package com.yan.springcloudprovide.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName User
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 10:06
 * @Version:
 */
@Data
@ToString
public class User {
    private String userId;
    private String userName;
    private String passWord;
    private String realName;


}
