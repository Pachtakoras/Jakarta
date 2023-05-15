package com.danielius.uni.usecases;

import com.danielius.uni.entities.Group;
import com.danielius.uni.persistence.GroupDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Model
public class Groups {
    @Inject
    private GroupDAO groupDAO;
    @Getter @Setter
    private Group groupToCreate = new Group();

    @Getter
    private List<Group> allGroups;
    @PostConstruct
    public void init(){
        loadAllGroups();
    }

    public String sayHello() {
        return "Labass " + new Date() + " " + toString();
    }

    @Transactional
    public void createGroup(){
        this.groupDAO.persist(groupToCreate);
    }

    private void loadAllGroups(){
        System.out.println("I was called");
        this.allGroups = groupDAO.loadAll();
    }

    public String sakykLabas() {
        return "Labas " + new Date() + " " + toString();
    }
}
