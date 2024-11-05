/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentmanagementsystem;

/**
 *
 * @author josef
 */
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String StudentName; 
    private int id; 
    private Map<Subject, Integer> grades; 

    // Constructor!!!!
    public Student(String name, int id) {
        this.StudentName = StudentName;
        this.id = id;
        this.grades = new HashMap<>(); 
    }

    //Accessors!!!!
    public String getStudentName() {
        return StudentName;
    }
    
    public int getId() {
        return id;
    }
    //Method that enrolls students into a subject
    public void enrollInSubject(Subject subject) {
        if (!grades.containsKey(subject)) {
            grades.put(subject, null); 
        }
    }
    // Method to add or update a grade for a specific subject
    public void addGrade(Subject subject, int grade) {
        if (grades.containsKey(subject)) {
            grades.put(subject, grade); 
        } else { //input validation
            System.out.println("Student not enrolled in " + subject.getName());
        }
    }
    //Accessor for a specific grade
    public Integer getGrade(Subject subject) {
        return grades.get(subject); 
    }
    //Accessor for all grades
    public Map<Subject, Integer> getGrades() {
        return grades; 
    }

    // Ensures a string is returned
    @Override
    public String toString() {
        return "Student{" + "name='" + StudentName + '\'' + ", id=" + id + '}';
    }
}

