package com.louisklein.javatraining;

import com.louisklein.javatraining.dao.StudentDAO;
import com.louisklein.javatraining.db.DatabaseInitializer;
import com.louisklein.javatraining.io.FileExporter;
import com.louisklein.javatraining.io.FileImporter;
import com.louisklein.javatraining.model.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class App {
    public static void main(String[] args) throws SQLException, IOException {
        DatabaseInitializer.initialize();

        List<Student> students = new ArrayList<>();
        StudentDAO studentDAO = new StudentDAO();

//        FileImporter.importStudents("data.txt", studentDAO);

//        sample data
        Student s1 = new Student("Alice", 20, "alice@example.com", 10);
        Student s2 = new Student("Bob", 21, "bob@example.com", 10);
        Student s3 = new Student("Charlie", 19, "charlie@example.com", 11);
        students.addAll(Arrays.asList(s1, s2, s3));

        studentDAO.addStudent(s1);
        studentDAO.addStudent(s2);
        studentDAO.addStudent(s3);

        FileExporter.exportStudents(students, "students.txt");

        System.out.println("Filter by Grade 10:");
        StudentProcessor.filterByGrade(students, 10);

        StudentProcessor.countByGrade(students, 10);

        System.out.println("Sorted List:");
        StudentProcessor.sortAlphabetically(students).forEach(System.out::println);

        System.out.println("\nMultithreading Output:");
        StudentProcessor.processWithThreads(students);
    }
}
