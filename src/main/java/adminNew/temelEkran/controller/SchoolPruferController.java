package adminNew.temelEkran.controller;

import adminNew.temelEkran.entity.Prufer;
import adminNew.temelEkran.entity.School;
import adminNew.temelEkran.service.FileService;
import adminNew.temelEkran.service.PruferService;
import adminNew.temelEkran.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RequestMapping("/school")
@Controller
public class SchoolPruferController {


    @Autowired
    PruferService pService;
    @Autowired
    SchoolService sService;
    @Autowired
    FileService fileService;


    @GetMapping("/prufer")
    public ModelAndView getPrufer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String mail = authentication.getName();
        School school = sService.getSchoolByMail(mail);
        List<Prufer> list1 = pService.getPrufersByName(school.getName());
        return new ModelAndView("prufer","prufers",list1);
    }

    @GetMapping("/addPrufer")
    public String addPrufer(){
        return "addPrufer";
    }

    @PostMapping("/prufer/save")
    public String savePrufer(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("birthDate") String birthDate,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("adress") String adress,
            @RequestParam("course") String course,
            @RequestParam("license") MultipartFile licenseFile,
            @RequestParam("photo") MultipartFile photoFile
    ) {
        try {
            Prufer prufer = new Prufer();
            prufer.setFirstName(firstName);
            prufer.setLastName(lastName);
            prufer.setBirthDate(birthDate);
            prufer.setPhone(phone);
            prufer.setEmail(email);
            prufer.setAdress(adress);
            prufer.setCourse(course);
            prufer.setActive(true);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String mail = authentication.getName();
            School school = sService.getSchoolByMail(mail);
            prufer.setSchool(school);

            pService.savePruferWithFiles(prufer, licenseFile, photoFile);

            return "redirect:/school/prufer";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }


    @GetMapping("/prufer-get-photo/{filePath}")
    public ResponseEntity<?> downloadImageFromSystem(@PathVariable String filePath) throws IOException {
        byte[] imageData = fileService.downloadFileFromFileSystem(filePath);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageData);
    }

    @GetMapping("/prufer-get-licence/{licencePath}")
    public ResponseEntity<?> downloadLicenceFromSystem(@PathVariable String licencePath) throws IOException {
        byte[] imageData = fileService.downloadFileFromFileSystem(licencePath);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_PDF)
                .body(imageData);
    }



}
