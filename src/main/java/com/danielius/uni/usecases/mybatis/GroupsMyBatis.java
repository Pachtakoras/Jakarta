package com.danielius.uni.usecases.mybatis;

import com.danielius.uni.entities.Group;
import com.danielius.uni.mybatis.dao.StudentGroupMapper;
import com.danielius.uni.mybatis.model.StudentGroup;
import lombok.Getter;
import lombok.Setter;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class GroupsMyBatis {
    @Inject
    private StudentGroupMapper groupMapper;

    @Getter
    private List<StudentGroup> allGroups;

    @Getter @Setter
    private StudentGroup groupToCreate = new StudentGroup();

    @PostConstruct
    public void init() {
        this.loadAllGroups();
    }

    private void loadAllGroups() {
        this.allGroups = groupMapper.selectAll();
    }

    @Transactional
    public String createGroup() {
        groupMapper.insert(groupToCreate);
        return "/myBatis/groups?faces-redirect=true";
    }
}
