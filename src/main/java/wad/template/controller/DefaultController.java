package wad.template.controller;

import javax.servlet.http.HttpSession;
import org.eclipse.persistence.sessions.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wad.timetables.domain.User;
import wad.timetables.service.UserService;

@Controller
public class DefaultController {
    
@Autowired
UserService userService;
    @RequestMapping("*")
    public String handleDefault(Model model, HttpSession session) {
       model.addAttribute("userA", userService.getUserById((Integer) session.getAttribute("user")));
        return "menu";
    }
    
    @RequestMapping("menu")
    public String getMenuPage(Model model, HttpSession session) { 
        Integer id = (Integer) session.getAttribute("user");
        User user = null;
        if (id != null) {
            user = userService.getUserById(id);
        }
        model.addAttribute("userA", user);
        return "menu";
    }
    
    @RequestMapping(value = "map", method = RequestMethod.GET)
    public String showMap(){
        return "mapstest";   
    }
    @RequestMapping(value = "geo", method = RequestMethod.GET)
    public String showGeo(){
        return "geocode";   
    }
}
