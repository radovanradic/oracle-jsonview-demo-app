package com.example.micronaut.repository.view;

import com.example.micronaut.entity.view.StudentView;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface StudentViewRepository extends PageableRepository<StudentView, Long> {

    Optional<StudentView> findByStudent(String student);

    @Query("UPDATE STUDENT_SCHEDULE ss SET ss.data = :data WHERE ss.DATA.student = :student")
    void updateByName(StudentView data, String student);

    void updateStudentByStudentId(@Id Long studentId, String student);

    void updateAverageGrade(@Id Long id, Double averageGrade);

    Optional<Double> findMaxAverageGrade();
}
