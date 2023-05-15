package com.danielius.uni.usecases.mybatis;


import com.danielius.uni.mybatis.dao.OptionalCoursesMapper;
import com.danielius.uni.mybatis.model.OptionalCourses;
import com.danielius.uni.mybatis.model.Student;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model
public class CourseStudentsMyBatis {
    @Inject
    private OptionalCoursesMapper optionalCoursesMapper;

    @Getter
    private OptionalCourses course;
    @Getter
    private List<Student> allCourseStudents;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long courseId = (long) Integer.parseInt(requestParameters.get("courseId"));
        course = optionalCoursesMapper.selectByPrimaryKey(courseId);
        this.loadAllCourseStudents(courseId);
    }

    private void loadAllCourseStudents(Long courseId) {
        allCourseStudents = optionalCoursesMapper.selectCourseStudents(courseId);
    }

}
