package com.example.springbootwebdemo.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    private UUID id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "hours")
    @JsonProperty("hours")
    private Integer hours;

}
