package adminNew.temelEkran.controller;


import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.entity.ExamStudentRegistration;
import adminNew.temelEkran.entity.School;
import adminNew.temelEkran.entity.Student;
import adminNew.temelEkran.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ExamStudentRegistrationService esrService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private StudentService sService;

    @GetMapping("/studentWelcome")
    public ModelAndView firstScreen(){
        List<Exam> list = eService.getAllDraftExam();
        list.sort(Comparator.comparing(Exam::getName));
        return new ModelAndView("studentWelcome","exam",list);
    }

    @PostMapping("/next")
    public ModelAndView secondScreen(@RequestParam int examId){

        Exam exam = eService.getExamById(examId);
        ModelAndView modelAndView = new ModelAndView("studentHome");
        modelAndView.addObject("exam",exam);
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

    @RequestMapping("/registerExam/{id}")
    public ModelAndView registerExam(
            @PathVariable("id") int id,
            @RequestParam("examId") int examId,
            @RequestParam("examType") int examType,
            RedirectAttributes redirectAttributes){
        Exam e = eService.getExamById(examId);
        ModelAndView modelAndView = new ModelAndView("saveStudent");
        modelAndView.addObject("exam",e);
        modelAndView.addObject("examType",examType);

        return modelAndView;
       /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        Student s = sService.getStudentByEmail(mail);
        Exam e = eService.getExamById(examId);
        System.out.println("kanka daha girmedim");
        if(e.getRegisteredParticipants() < e.getMaxParticipants()){
            System.out.println("kanka kaydetmek içi n geldim valla!");
            s.setExam(e);
            e.setRegisteredParticipants(e.getRegisteredParticipants() + 1);
            ExamStudentRegistration reg = new ExamStudentRegistration();
            reg.setStudent(s);
            reg.setExam(e);
            esrService.save(reg);
            redirectAttributes.addFlashAttribute("successMessage", "Successfully registered for the exam!");

        }
        else {
            System.out.println("kanka else e geldim haberin olsun");
            redirectAttributes.addFlashAttribute("errorMessage", "Registration failed. The exam is full.");
        }
        return "redirect:student/deneme";
        */
    }

    @PostMapping("/studentSave")
    public ModelAndView save(@RequestParam("id") int examId,
                       @RequestParam("firstName") String firstName,
                       @RequestParam("lastName") String lastName,
                       @RequestParam("birthDate") String birthDate,
                       @RequestParam("phone") String phone,
                       @RequestParam("email") String email,
                       @RequestParam("country") String country,
                       @RequestParam("examType") int examType,
                       RedirectAttributes redirectAttributes){

        Exam e = eService.getExamById(examId);
        Student s = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .phone(phone)
                .email(email)
                .country(country)
                .build();
        sService.save(s);
        ExamStudentRegistration esr = new ExamStudentRegistration();
        esr.setExam(e);
        esr.setStudent(s);
        esrService.save(esr);
        ModelAndView modelAndView = new ModelAndView("lastVerification");
        modelAndView.addObject("exam",e);
        modelAndView.addObject("student",s);
        modelAndView.addObject("examType",examType);
        return modelAndView;
    }

    @GetMapping("/lastVerification")
    public String publicScreen(){
        return "/lastVerification";
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
    @GetMapping("/payment")
    public String payment(){
        return "/payment";
    }

}
