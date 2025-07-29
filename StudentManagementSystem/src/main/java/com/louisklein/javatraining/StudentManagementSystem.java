package com.louisklein.javatraining;

import com.louisklein.javatraining.Course;
import com.louisklein.javatraining.Student;
import com.louisklein.javatraining.Teacher;

import java.util.*;

public class StudentManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Teacher> teachers = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Teacher");
            System.out.println("3. Add Course");
            System.out.println("4. Assign Course to Student");
            System.out.println("5. Assign Course to Teacher");
            System.out.println("6. View Student by ID");
            System.out.println("7. View All Students and Teachers");
            System.out.println("8. View All Courses");
            System.out.println("9. Update Student");
            System.out.println("10. Delete Student");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 : addStudent(); break;
                case 2 : addTeacher(); break;
                case 3 : addCourse(); break;
                case 4 : assignCourseToStudent(); break;
                case 5 : assignCourseToTeacher(); break;
                case 6 : viewStudentById(); break;
                case 7 : viewAll(); break;
                case 8 : viewAllCourses(); break;
                case 9 : updateStudent(); break;
                case 10 : deleteStudent(); break;
                case 11 : running = false; break;
                default : System.out.println("Invalid choice."); break;
            }
        }
    }

    private static void addStudent() {
//        invalid until proper input is made
        String name = "";
        int age = -1;
        String email = "";
        int grade = -1;

        try {
            // Name
            while (true) {
                System.out.print("Name: ");
                name = scanner.nextLine().trim();
                if (!name.trim().isEmpty()) break;
                System.out.println("Name cannot be blank. Please enter a valid name.");
            }

            // Age
            while (true) {
                System.out.print("Age: ");
                String ageInput = scanner.nextLine();
                try {
                    age = Integer.parseInt(ageInput);
                    if (age >= 0) break;
                    else System.out.println("Age must be a positive number.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a valid age.");
                }
            }

            // Email
            while (true) {
                System.out.print("Email: ");
                email = scanner.nextLine().trim();
                if (!email.trim().isEmpty()) break;
                System.out.println("Email cannot be blank. Please enter a valid email.");
            }

            // Grade
            while (true) {
                System.out.print("Grade: ");
                String gradeInput = scanner.nextLine();
                try {
                    grade = Integer.parseInt(gradeInput);
                    if (grade >= 0) break;
                    else System.out.println("Grade must be a positive number.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a valid grade.");
                }
            }

            // Create and add student
            Student student = new Student(name, age, email, grade);
            students.add(student);
            System.out.println("Student added with ID: " + student.getStudentId());

        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add student: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void addTeacher() {
//        invalid until proper input is made
        String name = "";
        int age = -1;
        String email = "";
        String subject = "";

        try {
            // Name
            while (true) {
                System.out.print("Name: ");
                name = scanner.nextLine().trim();
                if (!name.trim().isEmpty()) break;
                System.out.println("Name cannot be blank. Please enter a valid name.");
            }

            // Age
            while (true) {
                System.out.print("Age: ");
                String ageInput = scanner.nextLine();
                try {
                    age = Integer.parseInt(ageInput);
                    if (age >= 0) break;
                    else System.out.println("Age must be a positive number.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Please enter a valid age.");
                }
            }

            // Email
            while (true) {
                System.out.print("Email: ");
                email = scanner.nextLine().trim();
                if (!email.trim().isEmpty()) break;
                System.out.println("Email cannot be blank. Please enter a valid email.");
            }

            // Subject
            while (true) {
                System.out.print("Subject: ");
                subject = scanner.nextLine().trim();
                if (!subject.trim().isEmpty()) break;
                System.out.println("Subject cannot be blank. Please enter a valid subject.");
            }

            // Attempt to create the teacher
            Teacher teacher = new Teacher(name, age, email, subject);
            teachers.add(teacher);
            System.out.println("Teacher added with ID: " + teacher.getTeacherId());

        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add teacher: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void addCourse() {
        System.out.print("Course Name: ");
        String name = scanner.nextLine();
        Course course = new Course(name);
        courses.add(course);
        System.out.println("Course added with ID: " + course.getCourseId());
    }

    private static void assignCourseToStudent() {
//        view students to check ids
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Student student = students.stream()
                .filter(s -> s.getStudentId() == id)
                .findFirst().orElse(null);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        Course course = chooseCourse();
        if (course != null) {
            if (student.getCourses().contains(course)) {  //course already assigned check
                System.out.println("This course is already assigned to this student.");
            } else if (student.addCourse(course)) {
                System.out.println("Course assigned.");
            } else {
                System.out.println("Student already has 5 courses.");
            }
        }
    }

    private static void assignCourseToTeacher() {
        System.out.print("Enter Teacher ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        Teacher teacher = teachers.stream()
                .filter(t -> t.getTeacherId() == id)
                .findFirst().orElse(null);

        if (teacher == null) {
            System.out.println("Teacher not found.");
            return;
        }

        Course course = chooseCourse();
        if (course != null) {
            if (teacher.getCourses().contains(course)) { //course already assigned check
                System.out.println("This course is already assigned to this teacher.");
            } else if (teacher.addCourse(course)) {
                System.out.println("Course assigned to teacher.");
            } else {
                System.out.println("Teacher already has 3 courses.");
            }
        }
    }

    private static Course chooseCourse() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return null;
        }
        System.out.println("Available Courses:");
        for (Course c : courses)
            System.out.println(c);

        System.out.print("Enter Course ID to assign: ");
        int id = scanner.nextInt(); scanner.nextLine();
        return courses.stream()
                .filter(c -> c.getCourseId() == id)
                .findFirst().orElse(null);
    }

    private static void viewStudentById() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt(); scanner.nextLine();

        Student student = students.stream()
                .filter(s -> s.getStudentId() == id)
                .findFirst().orElse(null);

        if (student != null) {
            System.out.println(student);
            System.out.println("Courses:");
            student.getCourses().forEach(System.out::println);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void viewAll() {
        if(!students.isEmpty()){
            System.out.println("All Students:");
            students.forEach(System.out::println);
        }else{
            System.out.println("No Students Registered.");
        }

        if(!teachers.isEmpty()){
            System.out.println("All Teachers:");
            teachers.forEach(System.out::println);
        }else{
            System.out.println("No Teachers Registered.");
        }
    }

    private static void viewAllCourses() {
        if(!courses.isEmpty()) {
            System.out.println("Courses:");
            courses.forEach(System.out::println);
        }else{
            System.out.println("No Courses");
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt(); scanner.nextLine();

        for (Student s : students) {
            if (s.getStudentId() == id) {
                System.out.print("New Name: "); s.setName(scanner.nextLine());
                System.out.print("New Age: "); s.setAge(scanner.nextInt()); scanner.nextLine();
                System.out.print("New Email: "); s.setEmail(scanner.nextLine());
                System.out.print("New Grade: "); s.setGrade(scanner.nextInt()); scanner.nextLine();
                System.out.println("Student updated.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (students.stream().anyMatch(s -> s.getStudentId() == id)) {
            students.removeIf(s -> s.getStudentId() == id);
            System.out.println("Student removed.");
        } else {
            System.out.println("Student not found.");
        }
    }
}
