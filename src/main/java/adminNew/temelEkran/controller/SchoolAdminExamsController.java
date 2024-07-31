package adminNew.temelEkran.controller;


import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.entity.School;
import adminNew.temelEkran.service.ExamService;
import adminNew.temelEkran.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/schoolADMIN")
@Controller
public class SchoolAdminExamsController {

    @Autowired
    private ExamService eService;
    @Autowired
    private SchoolService sService;

    @GetMapping("/addExamsToMyList")
    public ModelAndView exams(){
        List<Exam> list = eService.getAllDraftExam();
        ModelAndView modelAndView = new ModelAndView("addExamsToMyList");
        modelAndView.addObject("draftExams", list);
        return modelAndView;
    }


    @PostMapping("/addExamsToMyList/save")
    public String save(@ModelAttribute Exam e){
        e.setActive(true);
        eService.save(e);
        return "redirect:/schoolADMIN/addExamsToMyList";
    }

    @RequestMapping("/addExamToTheList/{id}")
    public String toTheMyList(@PathVariable("id") int id, Model model){
        Exam e = eService.getExamById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        School school = sService.getSchoolByMail(mail);
        String schoolName = school.getName();
        e.setSchoolName(schoolName);
        model.addAttribute("exam",e);
        return "addExamToTheList";
    }



}
