package com.nillsimon.firemy;

import java.io.Serializable;

public class UserModel implements Serializable {
    String firstName, lastName, job, key;
    int age;

    public UserModel() {
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

    public UserModel(String firstName, String lastName, String job, int age, String key) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.age = age;
        this.key = key;
    }
}
