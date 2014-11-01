package ca.lc.stimesheet.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/timesheet")
public class PrivateTimesheetController {

    @RequestMapping(value="/manage", method=RequestMethod.GET)
    public String manage() throws IOException {

        return "thymeleaf/timesheet/manage";
    }
    
}
