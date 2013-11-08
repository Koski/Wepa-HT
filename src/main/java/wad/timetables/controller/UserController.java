package wad.timetables.controller;

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
import wad.timetables.domain.User;
import wad.timetables.service.UserAuthenticationService;
import wad.timetables.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserAuthenticationService authService;

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String getRegisterationPage(@ModelAttribute("user") User user) {
        return "register";
    }

//    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createUser(RedirectAttributes attrs, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User created = userService.create(user);
        if (created==null) {
            attrs.addFlashAttribute("userExistsAlready", user.getName() + " already exists. Try a different name");
            return "redirect:register";
        }
        return "redirect:login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String authenticateUser(Model model, @ModelAttribute("user") User user, HttpSession session) {
        if (!userService.authenticateUser(user)) {
            model.addAttribute("loginError", "Username or password was incorrect!");
            return "login";
        }
        user = userService.getUserByName(user.getName());
        if (user != null) {
            session.setAttribute("userId", user.getId());
        }

        return "redirect:menu";
    }

//    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "stops/addStop", method = RequestMethod.POST)
    public String addFavStop(RedirectAttributes attrs, @RequestParam("stopCode") Long stopCode, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "stop";
        }
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userService.getUserById(userId);
        if (user != null) {
            userService.addFavStop(user, stopCode);
            attrs.addFlashAttribute("user", user);
            attrs.addFlashAttribute("stopList", userService.getCurrentStopInfo(user));
        }
        return "redirect:/app/menu";
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:menu";
    }
}
