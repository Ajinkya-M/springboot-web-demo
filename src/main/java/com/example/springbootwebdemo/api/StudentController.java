package com.example.springbootwebdemo.api;


import com.example.springbootwebdemo.model.Course;
import com.example.springbootwebdemo.model.Student;
import com.example.springbootwebdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/student")
@RestController
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path="/{id}")
    public Student getStudentById(@PathVariable("id") UUID id) {
        return studentService.getStudentById(id)
                .orElse(null);
    }

    @PostMapping()
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @GetMapping(path="/{id}/courses")
    public List<Course> getCoursesByStudentId(@PathVariable("id") UUID uuid) {
        return studentService.getAllCoursesByStudentId(uuid);
    }

    @PutMapping(path = "/{sid}/course/{cid}")
    public void addCourseToStudentByName(@PathVariable("sid") String sname,
                                         @PathVariable("cid") String cname) {
        studentService.addCourseToStudentByName(sname, cname);
    }
}
