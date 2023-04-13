package com.danielius.uni.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COURSE", nullable = false)
    private Integer course;

    @Column(name = "SPECIALITY", nullable = false)
    private String speciality;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public Group(){

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group grupe = (Group) o;
        return speciality.equals(grupe.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciality);
    }

}
