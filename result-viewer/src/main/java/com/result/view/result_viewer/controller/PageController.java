package com.result.view.result_viewer.controller;

import com.result.view.result_viewer.dto.RequestResultForm;
import com.result.view.result_viewer.entity.Mark;
import com.result.view.result_viewer.entity.Student;
import com.result.view.result_viewer.repository.StudentRepo;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class PageController {
    private StudentRepo studentRepo;

    public PageController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/view-result")
    public String viewResultForm(Model model){
        RequestResultForm requestResultForm = new RequestResultForm();
        model.addAttribute("requestResultForm" , requestResultForm);

        return "view_result_form";
    }

    @RequestMapping("/help")
    public String help(){
        return "help";
    }

    @PostMapping("/view-result-action")
    public String viewResult(
            @Valid @ModelAttribute RequestResultForm requestResultForm,
            BindingResult bindingResult ,
            Model model
    ){
        if(bindingResult.hasErrors()){
            return "view_result_form";
        }

        Optional<Student> optionalStudent = studentRepo.findByRollNumberAndDateOfBirth(requestResultForm.getRollNumber(), requestResultForm.getDateOfBirth());
         if(optionalStudent.isEmpty()){
             return "redirect:/view-result?message=student not found";
         }
        Student student = optionalStudent.get();
        List<Mark> marks = student.getMarks();

        //calculate total of the marks result

        AtomicReference<Double> totalMarks= new AtomicReference<>(0.0);
        AtomicReference<Double> totalMaxMarks= new AtomicReference<>(0.0);
        marks.forEach(mark->{
            totalMarks.set(totalMarks.get()+Double.parseDouble(mark.getMarks()));
            totalMaxMarks.set(totalMaxMarks.get()+Double.parseDouble(mark.getMaxMarks()));
        });

        double percantage = totalMarks.get()/totalMaxMarks.get()*100;

        boolean passed = percantage>60? true : false;

        model.addAttribute("student" , student);
        model.addAttribute("marks" , marks);
        model.addAttribute("totalMarks" , totalMarks);
        model.addAttribute("totalMaxMarks" , totalMaxMarks);
        model.addAttribute("percantage" ,percantage );
        model.addAttribute("passed" ,passed);
        model.addAttribute("currentDate" , LocalDate.now().toString());




        return "view_result";
    }


}
