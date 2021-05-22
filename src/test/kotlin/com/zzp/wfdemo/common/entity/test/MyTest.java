package com.zzp.wfdemo.common.entity.test;

import com.zzp.wfdemo.data.User;

/**
 * 测试java 调kts
 */
public class MyTest {
    public static void main(String[] args) {
        var user = new User(123, "男", "zzp", "handsome boy");
        System.out.println(user);
    }
}
