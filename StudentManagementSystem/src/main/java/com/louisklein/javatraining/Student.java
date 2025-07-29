package com.louisklein.javatraining;

import java.util.ArrayList;

public class Student extends Person {
    private static int nextId = 1;
    private final int studentId;
    private int grade;
    private final ArrayList<Course> courses;

    public Student(String name, int age, String email, int grade) {
        super(name, age, email);
        this.studentId = nextId++;
        this.grade = grade;
        this.courses = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public boolean addCourse(Course course) {
        if (courses.size() >= 5) return false;
        return courses.add(course);
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                ", ID=" + studentId +
                ", Grade=" + grade +
                ", Courses=" + (courses.isEmpty() ? "None" : courses.toString());
    }
}
