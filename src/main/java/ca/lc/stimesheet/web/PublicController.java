package ca.lc.stimesheet.web;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PublicController {
    
    @RequestMapping({"/", "/welcome"})
    public String welcome(Model model, @RequestParam(value = "name", required = false) String name) {

        if (StringUtils.isNotBlank(name)) {
            model.addAttribute("name", name);
        } else {
            model.addAttribute("name", "Hardcoded Name");
        }
    	

        return "thymeleaf/welcome";
    }
    
    @RequestMapping(value="/login/openid", method=RequestMethod.GET)
    public String login() throws IOException {

        return "thymeleaf/welcome";
    }
    
}
