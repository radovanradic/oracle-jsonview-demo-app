package com.example.micronaut.entity.view;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class TeacherView {

    private Long teachID;
    private String teacher;

    public Long getTeachID() {
        return teachID;
    }

    public void setTeachID(Long teachID) {
        this.teachID = teachID;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teachID=" + teachID +
                ", teacher='" + teacher + '\'' +
                '}';
    }
}