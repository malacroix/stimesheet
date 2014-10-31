package ca.lc.stimesheet.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value="/unauthorized", method=RequestMethod.GET)
    public String unauthorized() throws IOException {

        return "thymeleaf/login/unauthorized";
    }
    
}
