package com.mycompany.StudentManagementSystem;

/**
 *
 * @author Josef's PC
 */
public class Subject {
    private String SubjectName;
    //Constructor
    public Subject(String SubjectName) {
        this.SubjectName = SubjectName;
    }
    //Accessor
    public String getSubjectName() {
        return SubjectName;
    }
    //Ensures a string is returned
    @Override
    public String toString() {
        return SubjectName;
    }
}

