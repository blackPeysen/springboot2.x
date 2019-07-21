package com.org.peysen.bootcontext.entity;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Description: java类作用描述
 * @Author: peysen
 * @CreateDate: 2019/7/21 16:38
 * @UpdateRemark: The modified content
 */
public class User {

    private Long id;

    private String userName;

    @Value("${user.age:18}")
    private Integer age;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
