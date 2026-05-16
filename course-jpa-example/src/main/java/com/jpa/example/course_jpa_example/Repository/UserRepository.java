package com.jpa.example.course_jpa_example.Repository;

import com.jpa.example.course_jpa_example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Integer> {
}
