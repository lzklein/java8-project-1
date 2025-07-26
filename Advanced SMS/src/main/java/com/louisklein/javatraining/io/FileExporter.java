package com.louisklein.javatraining.io;

import com.louisklein.javatraining.model.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExporter {
    public static void exportStudents(List<Student> students, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            for (Student s : students) {
                writer.write(s.getId() + "," + s.getName() + "," + s.getAge() + "," + s.getEmail() + "," + s.getGrade() + "\n");
            }
            System.out.println("Exported students to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
