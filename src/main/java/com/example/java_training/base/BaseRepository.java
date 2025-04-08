package com.example.java_training.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.*;

@NoRepositoryBean
public interface BaseRepository<T extends  Auditable>  extends JpaRepository<T, Long> {

}
