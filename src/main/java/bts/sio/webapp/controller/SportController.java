package bts.sio.webapp.controller;

import bts.sio.webapp.model.Site;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.model.Pays;
import bts.sio.webapp.service.SportService;
import bts.sio.webapp.service.PaysService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import bts.sio.webapp.service.SiteService;
@Data
@Controller
public class SportController {


    @Autowired
    private SportService sportservice;

    @Autowired
    private PaysService paysService;

    @Autowired
    private SiteService siteService;

    @GetMapping("/sports")
    public String home(Model model) {
        Iterable<Sport> listSports = sportservice.getSports();
        model.addAttribute("sports", listSports);
        return "sport/homeSport";
    }

    @GetMapping("/createSport")
    public String createSport(Model model) {
        Sport a = new Sport();
        model.addAttribute("sport", a);

        Iterable<Site> listSites = siteService.getSites();
        model.addAttribute("listSites", listSites);

        return "sport/formNewSport";
    }

    @GetMapping("/updateSport/{id}")
    public String updateSport(@PathVariable("id") final int id, Model model) {
        Sport a = sportservice.getSport(id);
        model.addAttribute("sport", a);

        Iterable<Site> listSites = siteService.getSites();
        model.addAttribute("listSites", listSites);

        return "sport/formUpdateSport";
    }

    @GetMapping("/deleteSport/{id}")
    public ModelAndView deleteSport(@PathVariable("id") final int id) {
        sportservice.deleteSport(id);
        return new ModelAndView("redirect:/sports");
    }

    @PostMapping("/saveSport")
    public ModelAndView saveSport(@ModelAttribute Sport sport) {
        System.out.println("controller save=" + sport.getNom());
        if(sport.getId() != null) {
            Sport current = sportservice.getSport(sport.getId());
        }
        sportservice.saveSport(sport);
        return new ModelAndView("redirect:/sports");
    }
}
