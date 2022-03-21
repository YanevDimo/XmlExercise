package com.example.xmlexercise.service.impl;

import com.example.xmlexercise.model.dto.UserSeedDto;
import com.example.xmlexercise.model.dto.UserViewRootDto;
import com.example.xmlexercise.model.dto.UserWithProductsDto;
import com.example.xmlexercise.model.entity.User;
import com.example.xmlexercise.repository.UserRepository;
import com.example.xmlexercise.service.UserService;
import com.example.xmlexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public long getCount() {
        return userRepository.count();
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {
        users
                .stream()
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long randomId = ThreadLocalRandom.current().nextLong(1, userRepository.count() + 1);

        return userRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public UserViewRootDto findUsersWithMoreThanOneProduct() {

        UserViewRootDto userViewRootDto = new UserViewRootDto();

        userViewRootDto
                .setProducts(userRepository
                        .findAllUsersWihMoreThanOneProduct()
                        .stream()
                        .map(user -> modelMapper.map(user, UserWithProductsDto.class))
                        .collect(Collectors.toList()));

        return userViewRootDto;
    }
}
