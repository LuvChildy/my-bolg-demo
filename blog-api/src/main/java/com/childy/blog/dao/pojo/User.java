package com.childy.blog.dao.pojo;

import lombok.Data;

@Data
public class User {
    private long id;
    private String account;
    private String email;
    private long lastLogin;
    private String mobilePhoneNum;
    private String nickName;
    private String password;
    private String salt;
    private String status;
}
