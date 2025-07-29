package com.louisklein.javatraining;

import java.util.ArrayList;

public class Teacher extends Person{
    private static int nextId = 1;
    private final int teacherId;
    private String subject;
    private final ArrayList<Course> courses;

    public Teacher(String name, int age, String email, String subject) {
        super(name, age, email);
        this.teacherId = nextId++;
        this.subject = subject;
        this.courses = new ArrayList<>();
    }

    public int getTeacherId() {
        return teacherId;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public boolean addCourse(Course course) {
        if (courses.size() >= 3) return false;
        return courses.add(course);
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                ", ID=" + teacherId +
                ", Subject=" + subject +
                ", Courses=" + (courses.isEmpty() ? "None" : courses.toString());
    }
}
