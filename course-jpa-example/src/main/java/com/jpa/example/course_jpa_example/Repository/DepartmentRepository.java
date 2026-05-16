package com.jpa.example.course_jpa_example.Repository;

import com.jpa.example.course_jpa_example.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department , Integer> {
}
