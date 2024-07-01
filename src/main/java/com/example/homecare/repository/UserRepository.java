package com.example.homecare.repository;

import com.example.homecare.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByPhone(String phone);
    Optional<User> findByPhoneAndPassword(String phone, String password);
    Long countUsersByTimeAfter(String time);
    List<User> findUsersByNameContainingAndPhoneContainingAndAddressContainingOrderByTimeDesc(String name, String phone, String address);
}
