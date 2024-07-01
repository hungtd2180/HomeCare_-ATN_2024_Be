package com.example.homecare.service;

import com.example.homecare.model.dto.CustomerSearchForm;
import com.example.homecare.model.entity.User;
import com.example.homecare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.homecare.util.DateUtil.getCurrentTime;
import static com.example.homecare.util.DateUtil.getFirstDayOfMonth;
import static com.example.homecare.util.EncoderUtil.createId;
import static com.example.homecare.util.EncoderUtil.encodeBase64Password;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }
    public Optional<User> loginUser(String userId, String password){
        return userRepository.findByPhoneAndPassword(userId, encodeBase64Password(password));
    }
    public List<User> findUser(CustomerSearchForm searchForm){
        return userRepository
                .findUsersByNameContainingAndPhoneContainingAndAddressContainingOrderByTimeDesc
                        (searchForm.getName(), searchForm.getPhone(), searchForm.getAddress());
    }
    public User saveUser(User user) {
        if (user.getUserId() == null || user.getUserId().isEmpty()) {
            user.setUserId(createId("user"));
            user.setTime(getCurrentTime());
            Optional<User> existingUser = userRepository.findByPhone(user.getPhone());
            if (existingUser.isPresent()) {
                throw new IllegalArgumentException("Phone number already exists");
            }
            user.setPassword(encodeBase64Password(user.getPassword()));
        }

        return userRepository.save(user);
    }
    public Long countUser(){
        return userRepository.countUsersByTimeAfter(getFirstDayOfMonth());
    }
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}