package com.example.springbootwebdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("course-postgres")
public interface CourseRepository extends JpaRepository<Course, UUID> {
}
