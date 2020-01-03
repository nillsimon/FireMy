package com.nillsimon.firemy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserModel implements Serializable {
    String imageFirst, nameFirst, nameTwo, hotText, descFirst, descTwo, key;
    int age;

    public String getImageFirst() {
        return imageFirst;
    }

    public void setImageFirst(String imageFirst) {
        this.imageFirst = imageFirst;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameTwo() {
        return nameTwo;
    }

    public void setNameTwo(String nameTwo) {
        this.nameTwo = nameTwo;
    }

    public String getHotText() {
        return hotText;
    }

    public void setHotText(String hotText) {
        this.hotText = hotText;
    }

    public String getDescFirst() {
        return descFirst;
    }

    public void setDescFirst(String descFirst) {
        this.descFirst = descFirst;
    }

    public String getDescTwo() {
        return descTwo;
    }

    public void setDescTwo(String descTwo) {
        this.descTwo = descTwo;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String , Object> toMap(){
        HashMap<String ,Object> result = new HashMap<>();
        result.put("imageFirst", imageFirst);
        result.put("nameFirst", nameFirst);
        result.put("nameTwo", nameTwo);
        result.put("hotText", hotText);
        result.put("descFirst", descFirst);
        result.put("descTwo", descTwo);
        result.put("age", age);
        result.put("key", key);

        return result;
    }
}
