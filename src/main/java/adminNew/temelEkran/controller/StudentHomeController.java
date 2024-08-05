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

import java.util.ArrayList;
import java.util.List;


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

    @GetMapping("/studentHome")
    public ModelAndView home(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Student s = sService.getStudentByEmail(mail);
        return new ModelAndView("studentHome","student",s);
    }
    @PostMapping("/studentHome/save")
    public ModelAndView savePostalCode(@RequestParam Integer postalCode, @RequestParam Integer distance, @RequestParam String course,RedirectAttributes redirectAttributes) {
        List<School> schools = ssService.getCloseSchools(distance, postalCode);
        System.out.println("Bulunan okullar:");
        for (School school : schools) {
            System.out.println(school.getName());
        }

        List<Exam> exams = new ArrayList<>();

        for (School school : schools) {
            List<Exam> schoolExams = eService.getExamsBySchoolNameAndCourse(school.getName(), course);
            if (schoolExams != null && !schoolExams.isEmpty()) {
                exams.addAll(schoolExams);
                System.out.println("Okul: " + school.getName() + " için bulunan sınavlar:");
                for (Exam exam : schoolExams) {
                    System.out.println("Sınav Adı: " + exam.getName() + ", Tarih: " + exam.getDate());
                }
            } else {
                System.out.println("No exams found for school: " + school.getName() + " and course: " + course);
            }
        }
        if (!exams.isEmpty()) {
            System.out.println("Tüm bulunan sınavlar:");
            for (Exam exam : exams) {
                System.out.println("Sınav Adı: " + exam.getName() + ", Okul: " + exam.getSchoolName() + ", Tarih: " + exam.getDate());
            }
        } else {
            System.out.println("Hiçbir sınav bulunamadı.");
        }
        redirectAttributes.addFlashAttribute("exams", exams);
        return new ModelAndView("examsFound","exams",exams);
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
        return "redirect:/student/studentHome";
    }

}
