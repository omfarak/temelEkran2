package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.Exam;
import adminNew.temelEkran.entity.Prufer;
import adminNew.temelEkran.repository.ExamRepository;
import adminNew.temelEkran.repository.PruferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepository eRepo;
    @Autowired
    private PruferRepository pRepo;



    public List<Prufer> getAllPruferBySchoolName(String schoolName){
        return pRepo.findPrufersBySchoolName(schoolName);
    }


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
    private static final List<String> LEVELS = List.of("A1", "A2", "B1", "B2", "C1", "C2");

    public List<Prufer> getPruferByExamLevel(String schoolName, String examLevel) {
        List<String> higherLevels = getHigherLevels(examLevel);
        return pRepo.findPrufersBySchoolNameAndCourseIn(schoolName, higherLevels);
    }

    private List<String> getHigherLevels(String currentLevel) {
        int index = LEVELS.indexOf(currentLevel);
        if (index == -1) {
            return new ArrayList<>();
        }
        return LEVELS.subList(index, LEVELS.size());
    }


}
