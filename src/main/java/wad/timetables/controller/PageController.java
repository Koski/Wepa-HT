package wad.timetables.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.timetables.domain.User;
import wad.timetables.service.UserService;


@Controller
public class PageController {
    
    @Autowired
    UserService userService;

    @RequestMapping("*")
    public String handleDefault(Model model, HttpSession session) {
        return "menu";
    }
  
    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public String getMenuPage(Model model, HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        if (user != null) {
            model.addAttribute("stopList", userService.getCurrentStopInfo(user));
            model.addAttribute("user", user);
        }
        return "menu";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String getRegisterationPage(@ModelAttribute("user") User user) {
        return "register";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }
}
