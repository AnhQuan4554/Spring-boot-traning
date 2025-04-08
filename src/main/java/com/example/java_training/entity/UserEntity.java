package com.example.java_training.entity;

import com.example.java_training.base.Auditable;
import com.example.java_training.dto.request.UserRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email ;

    @Column(nullable = false)
    private String password ;

    @Column(nullable = false)
    private String role = "customer";

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int old;

    @Column()
    private Integer deleted;

    public UserEntity(UserRequest userRequest) {
        BeanUtils.copyProperties(userRequest, this);
        this.role = "customer";
    }

    public UserEntity() {

    }
}
