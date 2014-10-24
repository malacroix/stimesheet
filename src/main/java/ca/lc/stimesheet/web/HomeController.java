package ca.lc.stimesheet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    
    @RequestMapping({"/", "/welcome"})
    public String welcome(Model model) {

    	model.addAttribute("name", "Hardcoded Name");

        return "thymeleaf/welcome";
    }
    
}
