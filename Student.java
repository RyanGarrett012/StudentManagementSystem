package com.mycompany.mavenproject1;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Josef's PC
 */
public class Student {
    private String StudentName;
    private int id;
    private Map<Subject, Integer> grades;
    //Constructor
    public Student(String StudentName, int id) {
        this.StudentName = StudentName;
        this.id = id;
        this.grades = new HashMap<>();
    }
    //Accessors!!!
    public String getStudentName() {
        return StudentName;
    }

    public int getId() {
        return id;
    }
    //Method to assign a SUBJECT to a given Student
    public void enrollInSubject(Subject subject) {
        if (!grades.containsKey(subject)) {
            grades.put(subject, null);
        }
    }
    //Can give a grade to a student for a specific Subject
    public void addGrade(Subject subject, int grade) {
        if (grades.containsKey(subject)) {
            grades.put(subject, grade);
        } else {
            System.out.println("Student not enrolled in " + subject.getSubjectName());
        }
    }
    //Accessor for a specific subject grade, it is special
    public Integer getGrade(Subject subject) {
        return grades.get(subject);
    }
    //Accessor for all grades
    public Map<Subject, Integer> getGrades() {
        return grades;
    }
    //ensures a string is returned
    @Override
    public String toString() {
        return "Student{" + "StudentName='" + StudentName + '\'' + ", id=" + id + '}';
    }
}
