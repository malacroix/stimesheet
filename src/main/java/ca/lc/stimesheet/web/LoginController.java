package ca.lc.stimesheet.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.lc.stimesheet.service.UserSubscriptionService;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserSubscriptionService userSubscriptionService;

    @RequestMapping(value="/unauthorized", method=RequestMethod.GET)
    public String unauthorized() throws IOException {

        return "thymeleaf/login/unauthorized";
    }
    
    @RequestMapping(value="", method=RequestMethod.GET)
    public String login(Model model) throws IOException {
        
        model.addAttribute("marketplaces", userSubscriptionService.findAllPartnerMarketplaces());

        return "thymeleaf/login/login";
    }

}
