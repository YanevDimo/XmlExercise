package com.example.xmlexercise.service;

import com.example.xmlexercise.model.dto.UserSeedDto;
import com.example.xmlexercise.model.dto.UserViewRootDto;
import com.example.xmlexercise.model.entity.User;

import java.util.List;

public interface UserService {
    long getCount();

    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();

    UserViewRootDto findUsersWithMoreThanOneProduct();

}


