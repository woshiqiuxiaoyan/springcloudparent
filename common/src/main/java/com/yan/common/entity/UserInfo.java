package com.yan.common.entity;


import java.io.Serializable;
import lombok.Data;

/**
 * <p>类名:UserInfo </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/9
 * 创建时间: 18:24
 */
@Data
public class UserInfo implements Serializable {
    private String name;
    private String goodAt;
    private int age;

}
