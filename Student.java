/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentmanagementsystem;

/**
 *
 * @author josef
 */
public class Student {
    private int StudentID;
    private String StudentName;
    private double StudentGrade;
    
    /**
     *
     * @param StudentID
     * @param StudentName
     * @param StudentGrade
     */
    //constructor begins
    public Student(int StudentID, String StudentName, double StudentGrade){
        StudentID = StudentID;
        StudentName = StudentName;
        StudentGrade = StudentGrade;
    }       
    //Accessors!!!
    public int getStudentID(){
        return StudentID;
    }
    public String getStudentName(){
        return StudentName;
    }   
    public double getStudentGrade(){
        return StudentGrade;
    }
    
    //Mutators!!!
    public void setStudentID(int StudentID){
        this.StudentID = StudentID;   
    }
    
    public void setStudentName(String StudentName){
        this.StudentName = StudentName;
    }
    
    public void setStudentGrade(int StudentGrade){
        this.StudentGrade = StudentGrade;
    }
    
}
