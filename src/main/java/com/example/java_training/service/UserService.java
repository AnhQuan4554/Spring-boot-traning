package com.example.java_training.service;

import com.example.java_training.dto.request.UserRequest;
import com.example.java_training.entity.UserEntity;
import com.example.java_training.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserEntity> getAllUser() {
        return userRepository.getListUsers();
    }

    // Get user by id
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found"));
    }

    public ResponseEntity<Object> saveUser(UserEntity userEntity) {
        if (StringUtils.isBlank(userEntity.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email không được để trống");

        }
        if (StringUtils.isBlank(userEntity.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Password không được để trống");
        }


        UserEntity savedUser = userRepository.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /*Update user*/
    public ResponseEntity<Object> updateUser(UserRequest userRequest, Long id) {
        System.out.println("id received : " + id);
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        BeanUtils.copyProperties(userRequest, existingUser, "id");
        userRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    // Delete user
    public ResponseEntity<Object> deleteUser(Long id) {
        try {
            Optional<UserEntity> userOptional = userRepository.findByIdAndDeletedIsNullOrDeletedZero(id);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User không tồn tại");
            }
            UserEntity newUser = userOptional.get();
            newUser.setDeleted(1);
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body("Remove success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Remove failed");
        }
    }
}
