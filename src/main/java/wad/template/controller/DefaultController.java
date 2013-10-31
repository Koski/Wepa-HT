package wad.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {
    
    @RequestMapping("*")
    public String handleDefault() {
        return "menu";
    }
    
    @RequestMapping("menu")
    public String getMenuPage() { 
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
