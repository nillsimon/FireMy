package com.nillsimon.firemy;

import java.util.HashMap;
import java.util.Map;

public class User {
    String text_name;
    String text_job;
    int age;

    public User() {

    }

    public User(String text_name, String text_job, int age) {
        this.text_name = text_name;
        this.text_job = text_job;
        this.age = age;
    }
/*
    @Override
    public String toString() {
        return "User{" +
                "text_name='" + text_name + '\'' +
                ", age=" + age +
                '}';
    }

 */

    public Map<String, Object> toMap(){
        HashMap<String , Object> result = new HashMap<>();
        result.put("text_name", text_name);
        result.put("text_job", text_job);
        result.put("age", age);
        return result;
    }
}
