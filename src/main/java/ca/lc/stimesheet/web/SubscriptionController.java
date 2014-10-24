package ca.lc.stimesheet.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/subscription")
public class SubscriptionController {
    

    @RequestMapping("/create")
    public String create(Model model) {
    	throw new IllegalStateException("not implemented");
    }
    
    @RequestMapping("/change")
    public String change(Model model) {
        throw new IllegalStateException("not implemented");
    }
    
    @RequestMapping("/cancel")
    public String cancel(Model model) {
        throw new IllegalStateException("not implemented");
    }
    
    @RequestMapping("/status")
    public String status(Model model) {
        throw new IllegalStateException("not implemented");
    }
}
