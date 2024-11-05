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

    //constructor!!!
    public Subject(String name) {
        this.SubjectName = SubjectName;
    }
    //Accessor
    public String getSubjectName() {
        return SubjectName;
    }

    @Override //so that subjects are always referenced as a string
    public String toString() {
        return SubjectName;
    }
}


