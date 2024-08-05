package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.Postal_code;
import adminNew.temelEkran.repository.PostalCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostalCodeService {

    @Autowired
    private PostalCodeRepository pRepo;

    public Postal_code getAdressByPostalCode(int postalCode){
        return pRepo.findPostal_codeByPostalCode(postalCode);
    }
}
