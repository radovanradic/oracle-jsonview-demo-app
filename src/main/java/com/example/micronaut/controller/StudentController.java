
package com.example.micronaut.controller;

import com.example.micronaut.domain.CreateStudentViewDto;
import com.example.micronaut.entity.view.StudentScheduleClassView;
import com.example.micronaut.entity.view.StudentScheduleView;
import com.example.micronaut.entity.view.StudentView;
import com.example.micronaut.entity.view.TeacherView;
import com.example.micronaut.repository.ClassRepository;
import com.example.micronaut.repository.view.StudentViewRepository;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.core.util.StringUtils;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExecuteOn(TaskExecutors.IO)
@Controller("/students")
public class StudentController {

    private final StudentViewRepository studentViewRepository;
    private final ClassRepository classRepository;


    public StudentController(StudentViewRepository studentViewRepository, ClassRepository classRepository) {
        this.studentViewRepository = studentViewRepository;
        this.classRepository = classRepository;
    }

    @Get("/{id}")
    public Optional<StudentView> findById(Long id) {
        return studentViewRepository.findById(id);
    }

    @Get("/")
    public Iterable<StudentView> findAll() {
        return studentViewRepository.findAll();
    }

    @Get("/student/{student}")
    public Optional<StudentView> findByStudent(@NonNull String student) {
        return studentViewRepository.findByStudent(student);
    }

    @Put("/{id}/average_grade/{averageGrade}")
    public Optional<StudentView> updateAverageGrade(Long id, @NonNull Double averageGrade) {
        Optional<StudentView> optionalStudentView = studentViewRepository.findById(id);
        if (!optionalStudentView.isPresent()) {
            return Optional.empty();
        }
        studentViewRepository.updateAverageGrade(id, averageGrade);
        return studentViewRepository.findById(id);
    }

    @Put("/{id}/student/{student}")
    public Optional<StudentView> updateStudent(Long id, @NonNull String student) {
        Optional<StudentView> optionalStudentView = studentViewRepository.findById(id);
        if (!optionalStudentView.isPresent()) {
            return Optional.empty();
        }
        studentViewRepository.updateStudentByStudentId(id, student);
        return studentViewRepository.findById(id);
    }

    @Post("/")
    public Optional<StudentView> create(@NonNull @Body CreateStudentViewDto createDto) {
        StudentView studentView = new StudentView();
        studentView.setStudent(createDto.getStudent());
        studentView.setAverageGrade(createDto.getAverageGrade());
        List<StudentScheduleView> studentScheduleViews = new ArrayList<>(createDto.getClasses().size());
        classRepository.findByNameIn(createDto.getClasses()).forEach(c -> {
            StudentScheduleView studentScheduleView = new StudentScheduleView();
            studentScheduleView.setClazz(StudentScheduleClassView.fromClass(c));
            studentScheduleViews.add(studentScheduleView);
        });
        studentView.setSchedule(studentScheduleViews);
        studentView = studentViewRepository.save(studentView);
        return Optional.of(studentView);
    }

    @Delete("/{id}")
    void delete(Long id) {
        studentViewRepository.deleteById(id);
    }

    @Get("/max_average_grade")
    Optional<Double> findMaxAverageGrade() {
        return studentViewRepository.findMaxAverageGrade();
    }
}
