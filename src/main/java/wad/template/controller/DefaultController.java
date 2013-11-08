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
        return "menu";
    }

//    @RequestMapping
//    public String    
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

    @RequestMapping(value = "map", method = RequestMethod.GET)
    public String showMap() {
        return "mapstest";
    }

    @RequestMapping(value = "geo", method = RequestMethod.GET)
    public String showGeo() {
        return "geocode";
    }
}
//<c:if test="${not empty user}">${user.name} ${user.id}</c:if>          
//                <c:if test="${empty user}">You haven't logged in!</c:if>