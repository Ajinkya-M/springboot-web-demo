package com.example.springbootwebdemo.service;

import com.example.springbootwebdemo.dao.StudentDao;
import com.example.springbootwebdemo.model.Course;
import com.example.springbootwebdemo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentService(@Qualifier("studentPostgres") StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    public Optional<Student> getStudentById(UUID uuid) {
        return Optional.ofNullable(studentDao.selectStudentById(uuid));
    }

    public void addStudent(Student student) {
        studentDao.insertStudent(student);
    }

    public List<Course> getAllCoursesByStudentId(UUID uuid) {
        return studentDao.selectAllCoursesByStudentId();
    }

    public void addCourseToStudentByName(String sname, String cname) {
        studentDao.addCourseToStudentByName(sname, cname);
    }

}
