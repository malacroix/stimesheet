package ca.lc.stimesheet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.lc.stimesheet.model.User;
import ca.lc.stimesheet.repository.UserRepository;
import ca.lc.stimesheet.service.UserService;

@RestController
@RequestMapping("/user")
public class TestController {
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public String addNewUser(Model model) {

        
        User user = new User();
        user.setUuid("U" + System.nanoTime());
        user.setNickname("testuser");
        user.setFirstName("Test");
        
        userRepository.save(user);

        return "New User created!";
    }
    
    @RequestMapping("/get")
    public String getNewUser(Model model) {

        User user = userService.findByUuid("U285003191230393");
        if (user != null) {
            return user.toString();
        } else {
            return "no user found....";
        }
    }
}
