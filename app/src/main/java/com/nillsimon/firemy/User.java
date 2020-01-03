package com.nillsimon.firemy;

import java.util.HashMap;
import java.util.Map;

public class User {

    String imageFirst;
    String nameFirst;
    String nameTwo;
    String hotText;
    String descFirst;
    String descTwo;
    int age;

    public User() {
    }

    public User(String imageFirst, String nameFirst, String nameTwo, String  hotText, String descFirst, String descTwo, int age) {
        this.imageFirst = imageFirst;
        this.nameFirst = nameFirst;
        this.nameTwo = nameTwo;
        this.hotText = hotText;
        this.descFirst = descFirst;
        this.descTwo = descTwo;
        this.age = age;
    }

    public Map<String, Object> toMap(){
        HashMap<String , Object> result = new HashMap<>();
        result.put("imageFirst", imageFirst);
        result.put("nameFirst", nameFirst);
        result.put("nameTwo", nameTwo);
        result.put("hotText", hotText);
        result.put("descFirst", descFirst);
        result.put("descTwo", descTwo);
        result.put("age", age);
        return result;
    }
}
