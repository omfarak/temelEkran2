package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Exam> getAllDraftExam(){
        List<Exam> draftExam = new ArrayList<>();
        int i = 0;
        List<Exam> l = getAllExam();
        while(i < l.size()){
            Exam e = l.get(i);
            if(e.getMaxParticipants() == 0){
                draftExam.add(e);
            }
            i++;
        }
        return draftExam;
    }
    public List<Exam> getAllActivetExam(){
        List<Exam> activeExam = new ArrayList<>();
        int i = 0;
        List<Exam> l = getAllExam();
        while(i < l.size()){
            Exam e = l.get(i);
            if(e.getMaxParticipants() != 0){
                activeExam.add(e);
            }
            i++;
        }
        return activeExam;
    }

}
