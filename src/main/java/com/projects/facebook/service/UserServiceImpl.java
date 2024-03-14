package com.projects.facebook.service;

import com.projects.facebook.entity.UserEntity;
import com.projects.facebook.expection.EmailAlreadyExistsException;
import com.projects.facebook.model.User;
import com.projects.facebook.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    public final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User storeUser(User user) {
        // Check if the email already exists
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // If the email doesn't exist, proceed with storing the user
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        userRepo.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepo.findAll();
        List<User> users = userEntities
                .stream()
                .map(userEntity -> new User(
                        userEntity.getFname(),
                        userEntity.getLname(),
                        userEntity.getPhone(),
                        userEntity.getEmail(),
                        userEntity.getPassword()
                ))
                .collect(Collectors.toList());
        return users;
    }

    @Override
    public User showUser(Integer id) {
        UserEntity userEntity = userRepo.findById(id).get();
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        return user;
    }


}
