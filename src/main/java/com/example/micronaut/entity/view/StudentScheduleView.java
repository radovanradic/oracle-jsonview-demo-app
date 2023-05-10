package com.example.micronaut.entity.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class StudentScheduleView {
        private Long id;
        @JsonProperty("class")
        private StudentScheduleClassView clazz;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public StudentScheduleClassView getClazz() {
                return clazz;
        }

        public void setClazz(StudentScheduleClassView clazz) {
                this.clazz = clazz;
        }

        @Override
        public String toString() {
                return "StudentSchedule{" +
                        "id=" + id +
                        ", class=" + clazz +
                        '}';
        }
}