package com.capstone.crdm.request;

import com.capstone.crdm.entities.Project;
import com.capstone.crdm.entities.User;

import java.util.List;

public class CreateProjectReq {
    private  Project project;
    private List<User> users;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
