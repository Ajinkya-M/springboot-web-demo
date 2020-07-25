package com.example.springbootwebdemo.api;

import com.example.springbootwebdemo.model.Person;
import com.example.springbootwebdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @DeleteMapping ("/{id}")
    public void deletePersonById(@NonNull @PathVariable("id") UUID id) {
        personService.deletePersonById(id);
    }

    @GetMapping ("/{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id)
                .orElse(null);
    }

    @PutMapping ("/{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @Valid @RequestBody Person person) {
        personService.updatePersonById(id, person);
    }
}
