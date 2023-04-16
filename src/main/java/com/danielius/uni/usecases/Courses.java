package com.danielius.uni.usecases;

import com.danielius.uni.entities.OptionalCourses;
import com.danielius.uni.persistence.OptionalCoursesDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Courses {
    @Getter
    private List<OptionalCourses> allCourses;

    @Getter @Setter
    private OptionalCourses courseToCreate = new OptionalCourses();

    @Inject
    private OptionalCoursesDAO optionalCoursesDAO;

    @PostConstruct
    private void init() {
        loadAllOptionalCourses();
    }
    @Transactional
    public void createCourse(){
        this.optionalCoursesDAO.persist(courseToCreate);
    }

    private void loadAllOptionalCourses(){
        this.allCourses = optionalCoursesDAO.loadAll();
    }
}
