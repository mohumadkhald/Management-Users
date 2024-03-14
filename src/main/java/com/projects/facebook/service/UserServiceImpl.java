package com.projects.facebook.service;

import com.projects.facebook.entity.UserEntity;
import com.projects.facebook.expection.EmailAlreadyExistsException;
import com.projects.facebook.expection.UserNotFoundException;
import com.projects.facebook.model.User;
import com.projects.facebook.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                        userEntity.getId(),
                        userEntity.getFname(),
                        userEntity.getLname(),
                        userEntity.getPhone(),
                        userEntity.getAddress(),
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

    @Override
    public boolean deleteUser(Integer id) {
        UserEntity userEntity = userRepo.findById(id).get();
        userRepo.delete(userEntity);
        return true;
    }

    @Override
    public User updateUser(Integer id, User user) {
        // Retrieve the user by id
        UserEntity userEntity = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        // Check if the email is being updated
        if (!userEntity.getEmail().equals(user.getEmail())) {
            // If the email is being updated, check if the new email already exists
            Optional<UserEntity> existingUserByEmail = userRepo.findByEmail(user.getEmail());
            if (existingUserByEmail.isPresent() && !existingUserByEmail.get().getId().equals(id)) {
                // If the new email already exists and belongs to a different user, throw an exception
                throw new EmailAlreadyExistsException("Email already exists: " + user.getEmail());
            }
        }

        // Update user fields
        userEntity.setFname(user.getFname());
        userEntity.setLname(user.getLname());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());

        // Save the updated user
        userRepo.save(userEntity);

        return user;
    }

}
