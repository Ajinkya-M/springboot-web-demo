package com.example.springbootwebdemo.dao;

import com.example.springbootwebdemo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessPostgres implements PersonDao {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessPostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person person) {
        String sql = "INSERT INTO person (id, name) VALUES (?, ?)";

        return jdbcTemplate.update(sql, new Object[]{id, person.getName()});
    }

    @Override
    public List<Person> selectAllPeople() {
        String sql = "SELECT * FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            return Person.builder()
                    .id(UUID.fromString(resultSet.getString("id")))
                    .name(resultSet.getString("name"))
                    .build();
        });
    }

    @Override
    public int deletePersonById(UUID id) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(sql,
                            new Object[] {id},
                            (resultSet, i) -> {
                                return Person.builder()
                                        .id(UUID.fromString(resultSet.getString("id")))
                                        .name(resultSet.getString("name"))
                                        .build();
                            });
        return Optional.ofNullable(person);
    }
}
