package com.jpa.example.course_jpa_example.Repository;

import com.jpa.example.course_jpa_example.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepo extends JpaRepository<Laptop , Integer > {
}
