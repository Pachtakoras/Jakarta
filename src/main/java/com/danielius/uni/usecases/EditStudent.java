package com.danielius.uni.usecases;


import com.danielius.uni.entities.OptionalCourses;
import com.danielius.uni.entities.Student;
import com.danielius.uni.persistence.OptionalCoursesDAO;
import com.danielius.uni.persistence.StudentDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class EditStudent implements Serializable {
    @Getter
    @Setter
    private Student student;
    @Inject
    private StudentDAO studentDAO;
    @Inject
    private OptionalCoursesDAO optionalCoursesDAO;
    @Getter
    private List<OptionalCourses> allOptionalCourses;
    @Getter @Setter
    private OptionalCourses optionalCourse = new OptionalCourses();

    @Getter @Setter
    private OptionalCourses courseToAdd;

    @PostConstruct
    private void init() {
        System.out.println("Update student INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long studentId = (long) Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentDAO.findOne(studentId);
        loadAllOptionalCourses();
    }

    private void loadAllOptionalCourses() {
        this.allOptionalCourses = optionalCoursesDAO.loadAll();
    }

    @Transactional
    public String setCourseToStudent() {
        try {
            studentDAO.update(this.student);
        } catch (OptimisticLockException e) {
            return "/studentDetails.xhtml?faces-redirect=true&playerId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }
        return "students.xhtml?groupId=" + this.student.getGroup().getId() + "&faces-redirect=true";
    }

    @Transactional
    public void setCourse() {
        List<OptionalCourses> optionalCourses = student.getOptionalCourses();
        courseToAdd = optionalCoursesDAO.findOne(optionalCourse.getId());
        optionalCourses.add(courseToAdd);
        student.setOptionalCourses(optionalCourses);
        studentDAO.update(student);
    }
}
