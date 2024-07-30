package adminNew.temelEkran.service;

import adminNew.temelEkran.entity.Prufer;
import adminNew.temelEkran.entity.School;
import adminNew.temelEkran.repository.PruferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PruferService {

    @Autowired
    private PruferRepository pRepo;
    @Autowired
    private FileService fileService;
    @Autowired
    private SchoolService sService;

    // Method to get the username of the logged-in user
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String mail = authentication.getName();
            School school = sService.getSchoolByMail(mail);
            return school != null ? school.getName() : "Unknown School";

    }

    public List<Prufer> getAllPrufer() {
        return pRepo.findAll();
    }

    public List<Prufer> getPrufersByName(String schoolName){
        return pRepo.findPrufersBySchoolName(schoolName);
    }

    public Prufer savePruferWithFiles(
            String firstName,
            String lastName,
            String birthDate,
            String phone,
            String email,
            String address,
            MultipartFile licenseFile,
            MultipartFile photoFile
    ) throws IOException {
        // Save files
        String licensePath = fileService.uploadFileToSystem(licenseFile);
        String photoPath = fileService.uploadFileToSystem(photoFile);

        // Create and save Prufer
        Prufer prufer = new Prufer();
        prufer.setFirstName(firstName);
        prufer.setLastName(lastName);
        prufer.setBirthDate(birthDate);
        prufer.setPhone(phone);
        prufer.setEmail(email);
        prufer.setAdress(address);
        prufer.setLicence(licensePath);
        prufer.setPhoto(photoPath);
        prufer.setActive(true);
        prufer.setSchoolName(getUsername());

        // Save prufer to repository
        pRepo.save(prufer);
        return prufer;
    }

    public void savePruferWithFiles(
            Prufer prufer,
            MultipartFile licenseFile,
            MultipartFile photoFile
    ) throws IOException {
        // Save files and get file paths
        String licensePath = fileService.uploadFileToSystem(licenseFile);
        String licenceName = licenseFile.getOriginalFilename();
        String photoPath = fileService.uploadFileToSystem(photoFile);
        String photoName = photoFile.getOriginalFilename();

        prufer.setLicence(licensePath);
        prufer.setPhoto(photoPath);
        prufer.setPhotoName("/" + photoName);
        prufer.setLicenceName("/" + licenceName);
        prufer.setSchoolName(getUsername());

        pRepo.save(prufer);
    }


    public void deletePrufer(int id){
        pRepo.deleteById(id);
    }

    public Prufer getPruferById(int id){
        return pRepo.findById(id).get();
    }
}
