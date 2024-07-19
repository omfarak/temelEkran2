/*package adminNew.temelEkran.controller;


import adminNew.temelEkran.repository.UserRepository;
import adminNew.temelEkran.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import adminNew.temelEkran.entity.User;


import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String getAllUsers(){
        return "";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User u){
        userService.save(u);
        return "redirect:/";
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable int userId){

        return "userById";

    }
}
*/