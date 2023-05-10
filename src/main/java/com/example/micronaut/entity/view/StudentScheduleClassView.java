package com.example.micronaut.entity.view;

import com.example.micronaut.entity.Class;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalTime;

@Serdeable
public class StudentScheduleClassView {
    private Long classID;
    private String name;
    private TeacherView teacher;
    private String room;
    private LocalTime time;

    public Long getClassID() {
        return classID;
    }

    public void setClassID(Long classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherView getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherView teacher) {
        this.teacher = teacher;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public static StudentScheduleClassView fromClass(Class clazz) {
        StudentScheduleClassView studentScheduleClassView = new StudentScheduleClassView();
        studentScheduleClassView.setClassID(clazz.getId());
        studentScheduleClassView.setName(clazz.getName());
        studentScheduleClassView.setRoom(clazz.getRoom());
        studentScheduleClassView.setTime(clazz.getTime());
        TeacherView teacherView = new TeacherView();
        teacherView.setTeacher(clazz.getTeacher().getName());
        teacherView.setTeachID(clazz.getTeacher().getId());
        studentScheduleClassView.setTeacher(teacherView);
        return studentScheduleClassView;
    }

    @Override
    public String toString() {
        return "StudentScheduleClass{" +
                "classID=" + classID +
                ", teacher=" + teacher +
                ", room='" + room + '\'' +
                ", time=" + time +
                '}';
    }
}