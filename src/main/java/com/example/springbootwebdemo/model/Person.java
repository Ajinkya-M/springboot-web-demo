package com.example.springbootwebdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
public class Person {

    @JsonProperty("id")
    private final UUID id;

    @NotBlank
    @JsonProperty("name")
    private final String name;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Person(UUID id,
                  String name) {
        this.id = id;
        this.name = name;
    }
}
