package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.User;
import adminNew.temelEkran.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository uRepo;

    public void save(User u){uRepo.save(u);}

    public List<User> getAllStudent(){return uRepo.findAll();}

    public User getStudentByID(int id){return uRepo.findById(id).get();}

    public void deleteById(int id){ uRepo.deleteById(id);}


}
