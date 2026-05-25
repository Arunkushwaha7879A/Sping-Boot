package com.result.view.result_viewer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="result_viewer_marks")
public class Mark {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;
private String subjectName;
private String marks;
private String maxMarks;
private String feedback;
private String grade;

@ManyToOne
private Student student;

}
