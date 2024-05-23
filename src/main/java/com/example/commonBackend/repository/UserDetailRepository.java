package com.example.commonBackend.repository;

import com.example.commonBackend.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    Optional<UserDetail> findByUsernameAndStatus(String email, String status);


}
