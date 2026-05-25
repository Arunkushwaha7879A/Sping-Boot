package com.result.view.result_viewer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.result.view.result_viewer.entity.Mark;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentForm {
    @NotBlank(message = "Name is required !!")
    private String name;
    @NotBlank(message = "Roll Number is required !!")
    private String rollNumber;
    @Email(message = "Email is required !!")
    private String email;
    @NotBlank(message = "Address is required !!")
    private String address;
    @NotBlank(message = "School is required !!")
    private String schoolName;
//    @NotBlank(message = "Dob is required !!")
     @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String standard;
    private String fatherName;
    private String gender;

    private List<MarkForm> marks = new ArrayList<>();
}
