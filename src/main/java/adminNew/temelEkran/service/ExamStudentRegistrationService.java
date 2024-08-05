package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.ExamStudentRegistration;
import adminNew.temelEkran.repository.ExamStudentRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamStudentRegistrationService {

    @Autowired
    private ExamStudentRegistrationRepository esrRepo;


    public void save(ExamStudentRegistration reg){
        esrRepo.save(reg);
    }

    public List<ExamStudentRegistration> getRegistrationsByExamId(int examId){
        return esrRepo.findExamStudentRegistrationsByExam_ExamId(examId);
    }



}
