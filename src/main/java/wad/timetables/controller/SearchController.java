package wad.timetables.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wad.timetables.domain.BusStop;
import wad.timetables.domain.User;
import wad.timetables.service.RESTStopService;
import wad.timetables.service.UserService;

@Controller
public class SearchController {

    @Autowired
    private RESTStopService stopService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchStops(RedirectAttributes attrs, Model model, @RequestParam(value = "busStop") String searchedStop, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        if (user != null) {
            attrs.addFlashAttribute("stopList", userService.getCurrentStopInfo(user));         
        }
        attrs.addFlashAttribute("stops", stopService.searchStops(searchedStop));
        return "redirect:menu";
    }

//    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "stops/{code}", method = RequestMethod.GET)
    public String showStop(Model model, @PathVariable Long code) {
        BusStop stop = stopService.getStop(code);
        model.addAttribute("stop", stop);
        model.addAttribute("departures", stopService.getDepartures(stop));
        return "stop";
    }
}
