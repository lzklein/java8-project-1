package com.louisklein.javatraining.io;

import com.louisklein.javatraining.dao.StudentDAO;
import com.louisklein.javatraining.model.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileImporter {

    public static void importStudents(String filePath, StudentDAO studentDAO) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("ID")) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 5) {
                    System.err.println("Skipping bad line: " + line);
                    continue;
                }

                try {
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    String email = parts[3].trim();
                    int grade = Integer.parseInt(parts[4].trim());

                    Student student = new Student(name, age, email, grade);
                    studentDAO.addStudent(student);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping line due to parse error: " + line);
                }
            }

            System.out.println("Imported students from " + filePath);

        } catch (IOException e) {
            System.err.println("Error reading from file: " + filePath);
            e.printStackTrace();
        }
    }
}
