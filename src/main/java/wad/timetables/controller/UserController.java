package wad.timetables.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.timetables.domain.User;
import wad.timetables.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    UserService userService;
    
    
    @RequestMapping(value="register", method = RequestMethod.GET) 
    public String getRegisterationPage(@ModelAttribute("user") User user){
        return "register";
    }
    
    @RequestMapping(value="login", method = RequestMethod.GET) 
    public String getLoginPage(@ModelAttribute("user") User user){
        return "login";
    }
    
    @RequestMapping(value="create", method = RequestMethod.POST)
    public String createUser(Model model,@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }       
        userService.create(user);
        model.addAttribute("userCreated", "User " + user.getName() + " created!");
        return "menu";
    } 
    
    @RequestMapping(value="login", method = RequestMethod.POST)
    public String authenticateUser(Model model, @ModelAttribute("user") User user){
        if(!userService.authenticateUser(user)) {
            model.addAttribute("loginError", "Username or password was incorrect!");
            return "login";
        }
        model.addAttribute("user", userService.getUserByName(user.getName()));
        return "menu";
    }
}
