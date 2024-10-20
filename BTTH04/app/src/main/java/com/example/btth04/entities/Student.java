package com.example.btth04.entities;


public class Student {
    private String id;
    private String name;
    private String studentClass;
    private double gpa;

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String id, String name, String studentClass ,double gpa) {
        this.gpa = gpa;
        this.studentClass = studentClass;
        this.name = name;
        this.id = id;
    }

}