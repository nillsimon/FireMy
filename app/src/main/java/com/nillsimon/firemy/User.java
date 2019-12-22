package com.nillsimon.firemy;

import java.util.HashMap;
import java.util.Map;

public class User {

    String image;
    String text_name;
    String text_job;
    String descFirst;
    int age;

    public User() {
    }

    public User(String image, String text_name, String text_job, String descFirst, int age) {
        this.image = image;
        this.text_name = text_name;
        this.text_job = text_job;
        this.descFirst = descFirst;
        this.age = age;
    }



    public Map<String, Object> toMap(){
        HashMap<String , Object> result = new HashMap<>();
        result.put("image", image);
        result.put("text_name", text_name);
        result.put("descFirst", descFirst);
        result.put("text_job", text_job);
        result.put("age", age);
        return result;
    }
}
