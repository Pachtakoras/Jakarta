package com.danielius.uni.usecases;

import com.danielius.uni.entities.OptionalCourses;
import com.danielius.uni.entities.Student;
import com.danielius.uni.persistence.OptionalCoursesDAO;
import com.danielius.uni.persistence.StudentDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Model
public class CourseStudent {
    @Inject
    private OptionalCoursesDAO optionalCoursesDAO;

    @Getter
    private OptionalCourses optionalCourse;

    @Inject
    private StudentDAO studentDAO;
    @Getter
    private List<Student> students;
    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long groupId = (long) Integer.parseInt(requestParameters.get("courseId"));
        this.optionalCourse = optionalCoursesDAO.findOne(groupId);
        loadAllStudents();
    }
    private void loadAllStudents() {
        this.students = optionalCourse.getStudents();
    }
}
