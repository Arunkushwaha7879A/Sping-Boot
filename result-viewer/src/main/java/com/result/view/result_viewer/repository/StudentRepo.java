package com.result.view.result_viewer.repository;

import com.result.view.result_viewer.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    Optional<Student> findByRollNumber(String rollNumber);
    Optional<Student> findByRollNumberAndDateOfBirth(String rollNumber , LocalDate dateOfBirth);
}
