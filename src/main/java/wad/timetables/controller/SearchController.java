package wad.timetables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.timetables.domain.BusStop;
import wad.timetables.service.RESTStopService;

@Controller
public class SearchController {

    @Autowired
    private RESTStopService stopService;

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchStops(Model model, @RequestParam(value = "busStop") String searchedStop) {        
        model.addAttribute("resultText", "Search results: "); 
        model.addAttribute("stops", stopService.searchStops(searchedStop)); 
        return "menu";
    }
    
    @RequestMapping(value = "stops/{code}", method = RequestMethod.GET)
    public String showStop(Model model, @PathVariable Long code) { 
        BusStop stop = stopService.getStop(code);
        model.addAttribute("stop", stop);
        model.addAttribute("departures", stopService.getDepartures(stop));
        return "stop";
    }
}
