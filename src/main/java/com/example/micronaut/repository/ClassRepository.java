package com.example.micronaut.repository;

import com.example.micronaut.entity.Class;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface ClassRepository extends PageableRepository<Class, Long> {

    @Join("teacher")
    Class findByName(String name);

    @Join("teacher")
    List<Class> findByNameIn(List<String> names);
}
