package com.danielius.uni.usecases.mybatis;

import com.danielius.uni.mybatis.dao.StudentGroupMapper;
import com.danielius.uni.mybatis.dao.StudentMapper;
import com.danielius.uni.mybatis.model.Student;
import com.danielius.uni.mybatis.model.StudentGroup;
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
public class StudentsMyBatis {
    @Inject
    private StudentMapper studentMapper;

    @Inject
    private StudentGroupMapper studentGroupMapper;

    @Getter
    private List<Student> allStudents;

    @Getter
    private StudentGroup group;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long groupId = (long) Integer.parseInt(requestParameters.get("groupId"));
        group = studentGroupMapper.selectByPrimaryKey(groupId);
        this.loadAllStudents(groupId);
    }

    private void loadAllStudents(Long groupId) {
        this.allStudents = studentMapper.selectGroupStudents(groupId);
    }

    @Transactional
    public String createStudent() {
        studentToCreate.setGroupId(group.getId());
        studentMapper.insert(studentToCreate);
        return "/myBatis/studentsmybatis?faces-redirect=true/groupId=" + group.getId();
    }
}
