package com.danielius.uni.usecases.mybatis;


import com.danielius.uni.mybatis.dao.OptionalCoursesMapper;
import com.danielius.uni.mybatis.model.OptionalCourses;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class CoursesMyBatis {

    @Inject
    private OptionalCoursesMapper optionalCoursesMapper;

    @Getter
    private List<OptionalCourses> allCourses;

    @Getter @Setter
    private OptionalCourses courseToCreate = new OptionalCourses();

    @PostConstruct
    public void init() {
        this.loadAllCourses();
    }

    private void loadAllCourses() {
        allCourses = optionalCoursesMapper.selectAll();
    }

    @Transactional
    public void createCourse() {
        optionalCoursesMapper.insert(courseToCreate);
    }


}
