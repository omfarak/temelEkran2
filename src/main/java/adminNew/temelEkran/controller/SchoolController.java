package adminNew.temelEkran.controller;

import adminNew.temelEkran.entity.School;
import adminNew.temelEkran.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SchoolController {


    @Autowired
    private SchoolService sService;

    @GetMapping("/schools")
    public ModelAndView schools(){
        List<School> list = sService.getAllSchool();
        return new ModelAndView("schools","school",list);
    }

    @GetMapping("addSchool")
    public String addSchool(){
        return "addSchool";
    }

    @PostMapping("schools/save")
    public String save(@ModelAttribute School s){
        sService.save(s);
        return "redirect:/schools";
    }

    @RequestMapping("deleteSchool/{id}")
    public String deleteSchool(@PathVariable("id") int id){
        sService.deleteSchoolById(id);
        return "redirect:/schools";
    }

    @RequestMapping("editSchool/{id}")
    public String editSchool(@PathVariable("id") int id, Model model){
        School e = sService.getSchoolById(id);
        model.addAttribute("school",e);
        return "editSchool";
    }

}
