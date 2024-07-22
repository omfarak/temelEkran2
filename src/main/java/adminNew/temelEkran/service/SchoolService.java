package adminNew.temelEkran.service;


import adminNew.temelEkran.entity.School;
import adminNew.temelEkran.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository sRepo;

    public void save(School s){
        sRepo.save(s);
    }


    public List<School> getAllSchool(){
        return sRepo.findAll();
    }

    public void deleteSchoolById(int id){
        sRepo.deleteById(id);
    }

    public School getSchoolById(int id){
        return sRepo.findById(id).get();

    }


}
