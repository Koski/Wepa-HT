package wad.timetables.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.timetables.domain.BusStop;
import wad.timetables.domain.Departure;
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
    
//    @PreAuthorize("hasRole('user')")
    @RequestMapping(value="login", method = RequestMethod.GET) 
    public String getLoginPage(@ModelAttribute("user") User user){
        return "login";
    }
    
    @RequestMapping(value="create", method = RequestMethod.POST)
    public String createUser(RedirectAttributes attrs, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "register";
        }       
        userService.create(user);
        attrs.addFlashAttribute("userCreated", "User " + user.getName() + " created!");
        return "redirect:login";
    } 
    
    @RequestMapping(value="login", method = RequestMethod.POST)
    public String authenticateUser(Model model, @ModelAttribute("user") User user, HttpSession session){
        if(!userService.authenticateUser(user)) {
            model.addAttribute("loginError", "Username or password was incorrect!");
            return "login";
        }
        user = userService.getUserByName(user.getName());
        session.setAttribute("user", user);
        
        session.setAttribute("stopsOfUser", user);
        return "redirect:menu";
    }
    
//    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "stops/addStop", method = RequestMethod.POST)
    public String addFavStop(RedirectAttributes attrs, @RequestParam("stopCode") Long stopCode, HttpSession session) {
        if (session.getAttribute("user")==null){
            return "stop";
        }
        User user = (User)session.getAttribute("user");
        userService.addFavStop(user, stopCode);
        attrs.addFlashAttribute("user", user);
        attrs.addFlashAttribute("stopList", userService.getCurrentStopInfo(user));
        
        return "redirect:/app/menu";
    }
}
