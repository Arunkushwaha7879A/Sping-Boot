package com.result.view.result_viewer.dto;

import com.result.view.result_viewer.entity.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarkForm {


    private String subjectName;
    private String marks;
    private String maxMarks;
    private String feedback;
    private String grade;

    private Student student;
}
