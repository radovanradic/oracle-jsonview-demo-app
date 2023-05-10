package com.example.micronaut.domain;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class CreateStudentViewDto {

    private String student;

    private Double averageGrade;
    private List<String> classes;

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }
}
