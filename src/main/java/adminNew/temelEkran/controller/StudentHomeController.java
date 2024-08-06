package adminNew.temelEkran.controller;


import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.entity.ExamStudentRegistration;
import adminNew.temelEkran.entity.School;
import adminNew.temelEkran.entity.Student;
import adminNew.temelEkran.repository.ExamStudentRegistrationRepository;
import adminNew.temelEkran.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;


@RequestMapping("/student")
@Controller
public class StudentHomeController {

    @Autowired
    private StudentSchoolService ssService;
    @Autowired
    private PostalCodeService pService;
    @Autowired
    private ExamService eService;
    @Autowired
    private StudentService sService;
    @Autowired
    private ExamStudentRegistrationService esrService;
    @Autowired
    private SchoolService schoolService;

    @GetMapping("/studentWelcome")
    public ModelAndView firstScreen(){
        List<Exam> list = eService.getAllDraftExam();
        list.sort(Comparator.comparing(Exam::getName));
        return new ModelAndView("studentWelcome","exam",list);
    }

    @PostMapping("/next")
    public ModelAndView secondScreen(@RequestParam int examId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Student s = sService.getStudentByEmail(mail);
        Exam exam = eService.getExamById(examId);
        ModelAndView modelAndView = new ModelAndView("studentHome");
        modelAndView.addObject("exam",exam);
        modelAndView.addObject("student",s);
        return modelAndView;
    }


    @PostMapping("/studentHome/save")
    public ModelAndView savePostalCode(@RequestParam Integer postalCode, @RequestParam Integer distance, @RequestParam String name, RedirectAttributes redirectAttributes) {
        List<School> schools = ssService.getCloseSchools(distance, postalCode);
        List<Exam> exams = new ArrayList<>();
        List<School> examSchools = new ArrayList<>();
        for (School school : schools) {
            List<Exam> temp = eService.getExamsBySchoolNameAndName(school.getName(), name);
            List<Exam> schoolExams = getMyActiveExams(temp);
            if (schoolExams != null && !schoolExams.isEmpty()) {
                exams.addAll(schoolExams);
                examSchools.add(school);
            }
        }
        ModelAndView modelAndView = new ModelAndView("examsFound");
        if (!exams.isEmpty()) {
            modelAndView.addObject("exams", exams);
            modelAndView.addObject("schools", examSchools);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "No exams found.");
            modelAndView.setViewName("redirect:/student/studentHome");
        }

        return modelAndView;
    }

    @PostMapping("/registerExam")
    public String registerExam(@RequestParam("examId") int examId, RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Student s = sService.getStudentByEmail(mail);
        Exam e = eService.getExamById(examId);
        if(e.getRegisteredParticipants() < e.getMaxParticipants()){
            s.setExam(e);
            e.setRegisteredParticipants(e.getRegisteredParticipants() + 1);
            ExamStudentRegistration reg = new ExamStudentRegistration();
            reg.setStudent(s);
            reg.setExam(e);
            esrService.save(reg);
            redirectAttributes.addFlashAttribute("successMessage", "Successfully registered for the exam!");

        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. The exam is full.");
        }
        return "redirect:/student/studentWelcome";
    }

    public List<Exam> getMyActiveExams(List<Exam> exams){
        List<Exam> myListExams = new ArrayList<>();
        int i = 0;
        List<Exam> l = exams;
        while(i < l.size()){
            Exam e = l.get(i);
            if(e.getDate() != null){
                myListExams.add(e);
            }
            i++;
        }
        return myListExams;
    }

}
