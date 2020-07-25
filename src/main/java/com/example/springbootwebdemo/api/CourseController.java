package com.example.springbootwebdemo.api;

import com.example.springbootwebdemo.dao.Course;
import com.example.springbootwebdemo.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public void insertCourse(@RequestBody Course course) {
        courseService.insertCourse(course);
    }

    @PutMapping(path = "/{id}/{hours}")
    public void updateHours(@PathVariable("id")UUID uuid,
                            @PathVariable("hours") Integer hours) {
        courseService.updateHoursforCourse(uuid, hours);
    }
}


