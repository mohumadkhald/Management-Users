package com.projects.facebook.repo;

import com.projects.facebook.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
