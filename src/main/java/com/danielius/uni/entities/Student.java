package com.danielius.uni.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "STUDENT")
@Getter @Setter
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "COURSES")
    private List<OptionalCourses> optionalCourses;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Student(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name) && surname.equals(student.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
