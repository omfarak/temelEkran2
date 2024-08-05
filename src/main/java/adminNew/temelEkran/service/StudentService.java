package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.Student;
import adminNew.temelEkran.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository oRepo;

    public void save(Student o){
        oRepo.save(o);
    }

    public List<Student> getAllStudent(){
        return oRepo.findAll();
    }

    public void deleteById(int id){
        oRepo.deleteById(id);
    }

    public Student getStudentByEmail(String email){
        return oRepo.findStudentByEmail(email);
    }

    public Student getStudentById(int id){
        return oRepo.findById(id).get();
    }
}
