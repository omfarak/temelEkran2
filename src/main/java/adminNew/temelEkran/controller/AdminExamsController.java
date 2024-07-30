package adminNew.temelEkran.controller;

import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminExamsController {

    @Autowired
    private ExamService eService;

    @GetMapping("/exams")
    public ModelAndView exams(){
        List<Exam> list = eService.getAllDraftExam();
        List<Exam> list2 = eService.getAllActivetExam();
        ModelAndView modelAndView = new ModelAndView("exams");
        modelAndView.addObject("exams", list);
        modelAndView.addObject("activeExams", list2);
        return modelAndView;
    }

    @GetMapping("/addExam")
    public String addExam(){
        return "addExam";
    }

    @PostMapping("/exams/save")
    public String save(@ModelAttribute Exam e){
        eService.save(e);
        return "redirect:/admin/exams";
    }

    @RequestMapping("/deleteExam/{id}")
    public String deleteExam(@PathVariable("id") int id){
        eService.deleteById(id);
        return "redirect:/admin/exams";
    }

    @RequestMapping("/editExam/{id}")
    public String editExam(@PathVariable("id") int id, Model model){
        Exam e = eService.getExamById(id);
        model.addAttribute("exam",e);
        return "editExam";
    }

    @RequestMapping("editActiveExam/{id}")
    public String editActiveExam(@PathVariable("id") int id, Model model){
        Exam e = eService.getExamById(id);
        model.addAttribute("exam",e);
        return "editActiveExam";
    }



}
