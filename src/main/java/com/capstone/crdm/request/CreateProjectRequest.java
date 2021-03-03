package com.capstone.crdm.request;

import com.capstone.crdm.entities.ProjectEntity;
import com.capstone.crdm.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProjectRequest {

    private ProjectEntity project;

    private List<UserEntity> users;

}
