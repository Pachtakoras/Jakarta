package com.danielius.uni.usecases.mybatis;

import com.danielius.uni.mybatis.dao.OptionalCoursesMapper;
import com.danielius.uni.mybatis.dao.StudentMapper;
import com.danielius.uni.mybatis.model.OptionalCourses;
import com.danielius.uni.mybatis.model.Student;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class EditStudentMyBatis {

    @Inject
    private OptionalCoursesMapper optionalCoursesMapper;

    @Inject
    private StudentMapper studentMapper;

    @Getter
    List<OptionalCourses> optionalCourses;

    @Getter
    private Student student;

    @Getter @Setter
    private OptionalCourses courseToAdd = new OptionalCourses();

    @PostConstruct
    private void init() {
        System.out.println("Update student INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long studentId = (long) Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentMapper.selectByPrimaryKey(studentId);
        loadAllOptionalCourses();
    }

    private void loadAllOptionalCourses() {
        optionalCourses = optionalCoursesMapper.selectAll();
    }

    @Transactional
    public void updateStudent(){
        optionalCoursesMapper.insertStudents(student.getId(), courseToAdd.getId());
    }
}
