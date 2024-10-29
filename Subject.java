/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.studentmanagementsystem;

/**
 *
 * @author josef
 */
public class Subject {
    private String SubjectName;
    private double SubjectGrade;
    //Constructor begins
    public Subject(String SubjectName, double SubjectGrade){
        SubjectName = SubjectName;
        SubjectGrade = SubjectGrade;
                
    }
    //Accessors!!!
    public String getSubjectName(){
        return SubjectName;
    }
    
    public double getSubjectGrade(){
        return SubjectGrade;
    }
    //Mutatators!!!
    public void setSubjectName(){
        this.SubjectName = SubjectName;
    }
    
    public void setSubjectGrade(){
        this.SubjectGrade = SubjectGrade;
    }
}


