package com.louisklein.javatraining;

public class Course {
    private static int nextId = 1;
    private final int courseId;
    private final String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
        this.courseId = nextId++;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "ID: " + courseId +
                ", Course: " + courseName;}
}
