package com.dingjinlin.jersey.bean;

/**
 * Created by DingJinlin on 2016/11/25 21:57.
 * Study
 */
public class Student {
    String name;
    int age;
    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringBuffer(" First Name : ").append("MY FIRST NAME").toString();

    }
}
