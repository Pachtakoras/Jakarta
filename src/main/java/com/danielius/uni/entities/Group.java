package com.danielius.uni.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "STUDENT_GROUP")
@Getter @Setter
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1) @Max(6)
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
        Group group = (Group) o;
        return speciality.equals(group.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(speciality);
    }

}
