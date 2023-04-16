package com.danielius.uni.persistence;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.danielius.uni.entities.Group;

import java.util.List;

@ApplicationScoped
public class GroupDAO {
    @Inject
    private EntityManager em;

    public List<Group> loadAll() {
        return em.createQuery("SELECT s FROM Group s", Group.class)
                .getResultList();
    }
    public void persist(Group group){
        this.em.persist(group);
    }

    public Group findOne(Long id) {
        return em.find(Group.class, id);
    }

}
