package com.danielius.uni.usecases;

import com.danielius.uni.entities.Group;
import com.danielius.uni.entities.Student;
import com.danielius.uni.persistence.GroupDAO;
import com.danielius.uni.persistence.StudentDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class GroupStudent {
    @Inject
    private GroupDAO groupDAO;

    @Inject
    private StudentDAO playersDAO;

    @Getter
    @Setter
    private Group group;

    @Getter @Setter
    private Student studentToCreate = new Student();
    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long groupId = (long) Integer.parseInt(requestParameters.get("groupId"));
        this.group = groupDAO.findOne(groupId);
    }
    @Transactional
    public void createStudent() {
        studentToCreate.setGroup(this.group);
        playersDAO.persist(studentToCreate);
    }


}
