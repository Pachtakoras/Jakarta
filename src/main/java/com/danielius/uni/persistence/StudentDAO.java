package com.danielius.uni.persistence;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.danielius.uni.entities.Group;
import com.danielius.uni.entities.Student;

import java.util.List;

@ApplicationScoped
public class StudentDAO {
    @Inject
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Student student){
        this.em.persist(student);
    }

    public Student findOne(Long id) {
        return em.find(Student.class, id);
    }

    public Student update(Student student){
        return em.merge(student);
    }

}
