package com.example.springbootwebdemo.service;

import com.example.springbootwebdemo.dao.Course;
import com.example.springbootwebdemo.dao.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void insertCourse(Course course) {
        course.setId(UUID.randomUUID());
        courseRepository.save(course);
    }

    public void updateHoursforCourse(UUID uuid, Integer hours) {
        Optional<Course> course = courseRepository.findById(uuid);
        course.ifPresent(course1 -> {
            course1.setHours(hours);
            courseRepository.save(course1);
        });
    }
}
