package adminNew.temelEkran.controller;


import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.entity.Prufer;
import adminNew.temelEkran.entity.School;
import adminNew.temelEkran.service.ExamService;
import adminNew.temelEkran.service.PruferService;
import adminNew.temelEkran.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/school")
@Controller
public class SchoolExamsController {


    @Autowired
    private ExamService eService;

    @Autowired
    private SchoolService sService;

    @Autowired
    private PruferService pService;





    @GetMapping("/exams")
    public ModelAndView exams(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        School s = sService.getSchoolByMail(username);
        List<Exam> list = eService.getAllDraftExam();
        List<Exam> list2 = eService.getAllActivetExam();
        List<Exam> list3 = eService.getMyListExams(s.getName());
        ModelAndView modelAndView = new ModelAndView("school_exams");
        modelAndView.addObject("draftExams", list);
        modelAndView.addObject("activeExams", list2);
        modelAndView.addObject("listExam",list3);
        return modelAndView;
    }

    /*
    @RequestMapping("/createExam/{id}")
    public String crateExam(@PathVariable("id") int id, Model model){
        Exam e = eService.getExamById(id);
        model.addAttribute("exam",e);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        //e.setSchool(sService.getSchoolByName(username));

        if (authentication != null) {
            model.addAttribute("username", username);
            e.setSchoolName(username);
        }
        return "createExam";
    }*/

    /*
    @RequestMapping("/createExam/{id}")
    public String createExam(@PathVariable("id") int id, Model model) {
        Exam e = eService.getExamById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        School school = sService.getSchoolByMail(mail);
        String schoolName = school.getName();

        // Get prüfer list by school name
        List<Prufer> prufers = eService.getAllPruferBySchoolName(schoolName);

        if (authentication != null) {
            model.addAttribute("username", schoolName);
            e.setSchoolName(schoolName);
        }

        model.addAttribute("exam", e);
        model.addAttribute("prufers", prufers);
        return "createExam";
    }

*/
    @RequestMapping("/createExam/{id}")
    public String createExam(@PathVariable("id") int id, Model model) {
        Exam e = eService.getExamById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        School school = sService.getSchoolByMail(mail);
        String schoolName = school.getName();

        // Get prüfer list by school name and exam level
        List<Prufer> prufers = eService.getPruferByExamLevel(schoolName, e.getCourse());

        if (authentication != null) {
            model.addAttribute("username", schoolName);
            e.setSchoolName(schoolName);
        }

        model.addAttribute("exam", e);
        model.addAttribute("prufers", prufers);
        return "createExam";
    }

    @PostMapping("/exams/save")
    public String save(Exam e,@RequestParam("pruferId") int pruferId){
        Prufer prufer = pService.getPruferById(pruferId);
        e.setPrufer(prufer);
        eService.save(e);
        return "redirect:/school/exams";
    }



}
