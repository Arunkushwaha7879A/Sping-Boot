package com.result.view.result_viewer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestResultForm {

    @NotBlank(message = "Enter rollNumber !!")
    private String rollNumber;

//    @NotBlank(message = "Select date of birth !!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
}
