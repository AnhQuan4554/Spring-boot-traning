package com.example.java_training.repository;
import com.example.java_training.base.BaseRepository;
import com.example.java_training.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<UserEntity> {
    @Query("SELECT u FROM UserEntity u WHERE u.deleted != 1 OR u.deleted IS NULL")
    List<UserEntity> getListUsers();

    @Query("SELECT u FROM UserEntity u WHERE u.id = :id AND (u.deleted IS NULL OR u.deleted = 0)")
    Optional<UserEntity> findByIdAndDeletedIsNullOrDeletedZero(@Param("id") Long id);
}
