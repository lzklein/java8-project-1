package com.louisklein.javatraining.model;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private int age;
    private String email;
    private int grade;

    public Student() {}

    public Student(int id, String name, int age, String email, int grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.grade = grade;
    }

    public Student(String name, int age, String email, int grade) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.grade = grade;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", grade=" + grade +
                '}';
    }
}
