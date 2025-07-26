package com.louisklein.javatraining;

import com.louisklein.javatraining.model.Student;

import java.util.*;
import java.util.stream.Collectors;

public class StudentProcessor {

    public static void filterByGrade(List<Student> students, int grade) {
        students.stream()
                .filter(s -> s.getGrade() == grade)
                .forEach(System.out::println);
    }

    public static void countByGrade(List<Student> students, int grade) {
        long count = students.stream().filter(s -> s.getGrade() == grade).count();
        System.out.println("Number of students in grade " + grade + ": " + count);
    }

    public static List<Student> sortAlphabetically(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    public static void processWithThreads(List<Student> students) {
        int mid = students.size() / 2;
        Thread firstHalf = new Thread(() -> {
            for (int i = 0; i < mid; i++) {
                System.out.println("Thread 1: " + students.get(i));
            }
        });

        Thread secondHalf = new Thread(() -> {
            for (int i = mid; i < students.size(); i++) {
                System.out.println("Thread 2: " + students.get(i));
            }
        });

        firstHalf.start();
        secondHalf.start();

        try {
            firstHalf.join();
            secondHalf.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
