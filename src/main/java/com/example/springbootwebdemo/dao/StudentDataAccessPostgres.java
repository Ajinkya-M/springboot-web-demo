package com.example.springbootwebdemo.dao;

import com.example.springbootwebdemo.model.Course;
import com.example.springbootwebdemo.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("studentPostgres")
public class StudentDataAccessPostgres implements StudentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAccessPostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insertStudent(UUID uuid, Student student) {
        String sql = "INSERT INTO student (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, new Object[] {uuid, student.getName()});
    }

    @Override
    public List<Student> selectAllStudents() {
        String sql = "SELECT * FROM student";
        List<Student> studentList = jdbcTemplate.query(sql,
                (rs, i) ->{
            return Student.builder()
                    .id(UUID.fromString(rs.getString("id")))
                    .name(rs.getString("name"))
                    .build();
        });
        return studentList;
    }

    @Override
    public Student selectStudentById(UUID uuid) {
        String sql = "SELECT * FROM student WHERE id = ?";
        Student student = jdbcTemplate.queryForObject(sql, new Object[] {uuid},
                (rs, i) -> Student.builder()
                            .id(UUID.fromString(rs.getString("id")))
                            .name(rs.getString("name"))
                            .build());
        return student;
    }

    @Override
    public List<Course> selectAllCoursesByStudentId() {
        return null;
    }

    @Override
    public void addCourseToStudentByName(String sname, String cname) {
        // sql for fetching sid from table
        String sql_for_sid = "SELECT id FROM student WHERE name = ? ";

        // sql for fetching cid from table
        String sql_for_cid = "SELECT id FROM course WHERE name = ? ";

        UUID sid = UUID.fromString(jdbcTemplate.queryForObject(sql_for_sid, new Object[] {sname}, String.class));

        UUID cid = UUID.fromString(jdbcTemplate.queryForObject(sql_for_cid, new Object[] {cname}, String.class));


        String sql_for_insert = "INSERT INTO student_course (sid, cid) VALUES (?, ?)";
        jdbcTemplate.update(sql_for_insert, new Object[] {sid, cid});

    }
}
