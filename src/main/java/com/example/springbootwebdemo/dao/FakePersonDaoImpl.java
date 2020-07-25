package com.example.springbootwebdemo.dao;

import com.example.springbootwebdemo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDaoImpl implements PersonDao {

    static private List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectPersonById(id);
        if(person.isPresent()) {
            DB.remove(person);
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        if(deletePersonById(id) == 0) return 0;
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {

        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }
}
