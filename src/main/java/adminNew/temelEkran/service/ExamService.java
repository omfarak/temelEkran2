package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository eRepo;

    public void save(Exam e){
        eRepo.save(e);
    }

    public List<Exam> getAllExam(){

        return eRepo.findAll();
    }

    public void deleteById(int id){
        eRepo.deleteById(id);
    }

    public Exam getExamById(int id){
        return eRepo.findById(id).get();
    }



}
