package adminNew.temelEkran.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/school")
@Controller
public class SchoolHomeController {

    @GetMapping("/home")
    public String home(){
        return "school_home";
    }


}
