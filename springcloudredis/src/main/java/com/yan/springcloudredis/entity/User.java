package com.yan.springcloudredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName User
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 10:06
 * @Version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String userId;
    private String userName;
    private String passWord;
    private String phone;
}
