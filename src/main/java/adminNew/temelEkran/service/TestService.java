package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.Test;
import adminNew.temelEkran.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestRepository tRepo;

    public void save(Test t){
        tRepo.save(t);
    }

}
