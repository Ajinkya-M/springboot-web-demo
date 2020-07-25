package com.example.springbootwebdemo.dao;

import com.example.springbootwebdemo.model.Course;
import com.example.springbootwebdemo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDao {

    public void insertStudent(UUID uuid, Student student);

    public List<Student> selectAllStudents();

    default void insertStudent(Student student) {
        UUID uuid = UUID.randomUUID();
        insertStudent(uuid, student);
    }

    public Student selectStudentById(UUID uuid);

    public List<Course> selectAllCoursesByStudentId();

    public void addCourseToStudentByName(String sname, String cname);

}
