package adminNew.temelEkran.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminHomeController {

    @GetMapping("/home")
    public String home(){
        return "homeAdmin";
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/home/admin";
    }
}
