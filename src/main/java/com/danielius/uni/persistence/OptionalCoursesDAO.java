package com.danielius.uni.persistence;

import com.danielius.uni.entities.Group;
import com.danielius.uni.entities.OptionalCourses;
import com.danielius.uni.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class OptionalCoursesDAO {
    @Inject
    private EntityManager em;
    public void persist(OptionalCourses course){
        this.em.persist(course);
    }

    public List<OptionalCourses> loadAll(){
        return em.createQuery("SELECT s FROM OptionalCourses s", OptionalCourses.class)
                .getResultList();
    }

    public OptionalCourses findOne(Long id) {
        return em.find(OptionalCourses.class, id);
    }
}
