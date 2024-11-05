/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.studentmanagementsystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author josef
 */
public class StudentManagementSystem {

    private static Map<Integer, Student> students = new HashMap<>();
    private static List<Subject> subjects = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // CREATING SUBJECTS :D
        subjects.add(new Subject("Math"));
        subjects.add(new Subject("Science"));
        subjects.add(new Subject("English"));
        subjects.add(new Subject("History"));
        subjects.add(new Subject("Java Programming"));

        boolean exit = false;
        while (!exit) {
            System.out.println("Select an option: ");
            System.out.println("1. Add new Student");
            System.out.println("2. Enroll Student in Subject");
            System.out.println("3. Create or Update Student Grade");
            System.out.println("4. View Student Grades");
            System.out.println("5. Exit");
            int option = scanner.nextInt();
            scanner.nextLine();  

            switch (option) {
                case 1 -> addStudent();
                case 2 -> enrollStudentInSubject();
                case 3 -> addOrUpdateGradeForStudent();
                case 4 -> viewStudentGrades();
                case 5 -> exit = true;
                default -> System.out.println("ERROR: Input did not match.");
            }
        }
        scanner.close();
    }
    //Method allows student to be added
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        students.put(id, new Student(name, id));
        System.out.println("Student added: " + name);
    }
    //enrolls a student into a subject
    private static void enrollStudentInSubject() {
        Student student = getStudentById();
        if (student != null) {
            System.out.println("Available subjects:");
            for (int i = 0; i < subjects.size(); i++) {
                System.out.println((i + 1) + ". " + subjects.get(i).getName());
            }
            System.out.print("Select a subject to enroll in (by number): ");
            int subjectIndex = scanner.nextInt() - 1;
            if (subjectIndex >= 0 && subjectIndex < subjects.size()) {
                student.enrollInSubject(subjects.get(subjectIndex));
                System.out.println("Enrolled in " + subjects.get(subjectIndex).getName());
            } else {
                System.out.println("Invalid subject choice.");
            }
        }
    }
    //Grading input method
    private static void addOrUpdateGradeForStudent() {
        Student student = getStudentById();
        if (student != null) {
            System.out.println("Enrolled subjects:");
            List<Subject> enrolledSubjects = new ArrayList<>(student.getGrades().keySet());
            for (int i = 0; i < enrolledSubjects.size(); i++) {
                System.out.println((i + 1) + ". " + enrolledSubjects.get(i).getName());
            }
            System.out.print("Select a subject to add or update a grade (by number): ");
            int subjectIndex = scanner.nextInt() - 1;
            if (subjectIndex >= 0 && subjectIndex < enrolledSubjects.size()) {
                System.out.print("Enter grade (0-100): ");
                int grade = scanner.nextInt();
                if (grade >= 0 && grade <= 100) {
                    student.addGrade(enrolledSubjects.get(subjectIndex), grade);
                    System.out.println("Grade added/updated: " + grade);
                } else {
                    System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                }
            } else {
                System.out.println("Invalid subject choice.");
            }
        }
    }
    
    
    //Beta report feature, feel free to add onto this however you need
    private static void viewStudentGrades() {
        Student student = getStudentById();
        if (student != null) {
            System.out.println("Grades for " + student.getName() + ":");
            for (Map.Entry<Subject, Integer> entry : student.getGrades().entrySet()) {
                System.out.println(entry.getKey().getName() + ": " + (entry.getValue() != null ? entry.getValue() : "No grade assigned"));
            }
        }
    }

    private static Student getStudentById() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        Student student = students.get(id);
        if (student == null) {
            System.out.println("No student found with ID " + id);
        }
        return student;
    }
}
