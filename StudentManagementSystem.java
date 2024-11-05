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
        //Creating Subjects :D!!!
        subjects.add(new Subject("Math"));
        subjects.add(new Subject("English"));
        subjects.add(new Subject("Science"));
        subjects.add(new Subject("History"));
        subjects.add(new Subject("Java Programming"));

        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Enroll Student in Subject");
            System.out.println("3. Add or Update Grade");
            System.out.println("4. View Student Grades");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1 -> addStudent();
                case 2 -> enrollStudentInSubject();
                case 3 -> addOrUpdateGradeForStudent();
                case 4 -> viewStudentGrades();
                case 5 -> exit = true;
                default -> System.out.println("ERROR: Input is invalid. Try again."); //input validation
            }
        }
        scanner.close();
    }
    //Method for case 2
    private static void addStudent() {
        System.out.print("Enter student name: ");
        String StudentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  
        students.put(id, new Student(StudentName, id));
        System.out.println("Student added: " + StudentName);
    }
    //method for case 2
    private static void enrollStudentInSubject() {
        Student student = getStudentById();
        if (student != null) {
            System.out.println("Available subjects:");
            for (int i = 0; i < subjects.size(); i++) {
                System.out.println((i + 1) + ". " + subjects.get(i).getSubjectName());
            }
            System.out.print("Select a subject to enroll in (by number): ");
            int subjectIndex = scanner.nextInt() - 1;
            if (subjectIndex >= 0 && subjectIndex < subjects.size()) {
                student.enrollInSubject(subjects.get(subjectIndex));
                System.out.println("Enrolled in " + subjects.get(subjectIndex).getSubjectName());
            } else {
                System.out.println("Invalid subject choice.");
            }
        }
    }
    //Method for case 3
    private static void addOrUpdateGradeForStudent() {
        Student student = getStudentById();
        if (student != null) {
            System.out.println("Enrolled subjects:");
            List<Subject> enrolledSubjects = new ArrayList<>(student.getGrades().keySet());
            for (int i = 0; i < enrolledSubjects.size(); i++) {
                System.out.println((i + 1) + ". " + enrolledSubjects.get(i).getSubjectName());
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
    //method for case 4
    private static void viewStudentGrades() {
        Student student = getStudentById();
        if (student != null) {
            System.out.println("Grades for " + student.getStudentName() + ":");
            for (Map.Entry<Subject, Integer> entry : student.getGrades().entrySet()) {
                System.out.println(entry.getKey().getSubjectName() + ": " + (entry.getValue() != null ? entry.getValue() : "No grade assigned"));
            }
        }
    }
    //connects students to their assigned ID for easier access (database rules!!)
    //we love primary keys!!!!!
    private static Student getStudentById() {
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Student student = students.get(id);
        if (student == null) {
            System.out.println("No student exists with the ID - " + id);//input validation
        }
        return student;
    }
}
