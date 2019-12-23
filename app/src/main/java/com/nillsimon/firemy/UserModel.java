package com.nillsimon.firemy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserModel implements Serializable {
    String image, firstName, lastName, DescFirst, descText, job, key;
    int age;

    public String getDescFirst() {
        return DescFirst;
    }

    public void setDescFirst(String descFirst) {
        this.DescFirst = descFirst;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
        result.put("image", image);
        result.put("firstName", firstName);
        result.put("lastName", lastName);
        result.put("DescFirst", DescFirst);
        result.put("descText", descText);
        result.put("job", job);
        result.put("age", age);
        result.put("key", key);

        return result;
    }
}
