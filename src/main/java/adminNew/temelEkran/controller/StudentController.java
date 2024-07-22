package adminNew.temelEkran.controller;

import adminNew.temelEkran.entity.Student;
import adminNew.temelEkran.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import java.util.List;

@Controller
public class StudentController {


    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public ModelAndView students(){
        List<Student> list = studentService.getAllStudent();
        ModelAndView m = new ModelAndView();
        return new ModelAndView("students","student",list);
    }
    @GetMapping("/addStudent")
    public String addStudent(){
        return "addStudent";
    }



    @PostMapping("students/save")
    public String studentAdd(@ModelAttribute Student o, BindingResult result){
//        if (result.hasErrors()) {
//            return "addStudent";
//        }
        studentService.save(o);
        return "redirect:/students";
    }

    @RequestMapping("/deleteStudent/{id}")
    public String deleteMyList(@PathVariable("id") int id){
        studentService.deleteById(id);
        return "redirect:/students";
    }

    @RequestMapping("/editStudent/{id}")
    public String editMyList(@PathVariable("id") int id, Model model){
        Student o = studentService.getStudentById(id);
        model.addAttribute("student",o);
        return "editStudent";
    }



}
