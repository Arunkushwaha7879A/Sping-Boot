package com.result.view.result_viewer.controller;

import com.result.view.result_viewer.dto.StudentForm;
import com.result.view.result_viewer.entity.Mark;
import com.result.view.result_viewer.entity.Student;
import com.result.view.result_viewer.repository.StudentRepo;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private StudentRepo studentRepo;
    private ModelMapper modelMapper;

    public AdminController(StudentRepo studentRepo, ModelMapper modelMapper) {
        this.studentRepo = studentRepo;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/result-page")
    public String redirectHandler(){
        return "redirect:/admin/add-result";
    }
    @GetMapping("/add-result")
    public String addResultForm(Principal principal ,Model model){

        String name = principal.getName();

        StudentForm studentForm = new StudentForm();
        List<String> standardOptions = new ArrayList<>();
        standardOptions.add("CLASS 1");
        standardOptions.add("CLASS 2");
        standardOptions.add("CLASS 3");
        standardOptions.add("CLASS 4");
        standardOptions.add("CLASS 5");
        standardOptions.add("CLASS 6");
        model.addAttribute("studentForm" , studentForm);
        model.addAttribute("standardOptions" , standardOptions);
        model.addAttribute("name" , name);
        return "admin/add_result";
    }

    @PostMapping("/add-result-action")
    public String processAddResultForm(@Valid @ModelAttribute StudentForm studentForm ,
                                       BindingResult bindingResult,
                                       Model model){
        System.out.println(bindingResult.hasErrors());

        if(bindingResult.hasErrors()){
            List<String> standardOptions = new ArrayList<>();
            standardOptions.add("CLASS 1");
            standardOptions.add("CLASS 2");
            standardOptions.add("CLASS 3");
            standardOptions.add("CLASS 4");
            standardOptions.add("CLASS 5");
            standardOptions.add("CLASS 6");
            model.addAttribute("standardOptions" , standardOptions);
            model.addAttribute("studentForm" , studentForm);
            return "admin/add_result";
        }
        //convert studentform to student entity
        Student student = modelMapper.map(studentForm, Student.class);

        List<Mark> updatedList = student.getMarks().stream().map(mark -> {
            mark.setStudent(student);
            return mark;
        }).toList();

        student.setMarks(updatedList);


        student.setStudentId(UUID.randomUUID().toString());
        Student savedStudent = studentRepo.save(student);
        return "redirect:/admin/add-result?message=student_added_successfully";
    }
}
