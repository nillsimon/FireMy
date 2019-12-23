package com.nillsimon.firemy;

import java.util.HashMap;
import java.util.Map;

public class User {

    String image;
    String text_name;
    String text_job;
    String descText;
    String DescFirst;
    int age;

    public User() {
    }

    public User(String image, String text_name, String text_job, String DescFirst, String descText, int age) {
        this.image = image;
        this.text_name = text_name;
        this.text_job = text_job;
        this.DescFirst = DescFirst;
        this.descText = descText;
        this.age = age;
    }



    public Map<String, Object> toMap(){
        HashMap<String , Object> result = new HashMap<>();
        result.put("image", image);
        result.put("text_name", text_name);
        result.put("DescFirst", DescFirst);
        result.put("descText", descText);
        result.put("text_job", text_job);
        result.put("age", age);
        return result;
    }
}
