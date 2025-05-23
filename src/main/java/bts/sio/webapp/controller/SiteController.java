package bts.sio.webapp.controller;

import bts.sio.webapp.model.Site;
import bts.sio.webapp.model.Pays;
import bts.sio.webapp.service.SiteService;
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

@Data
@Controller
public class SiteController {


    @Autowired
    private SiteService siteservice;

    @Autowired
    private PaysService paysService;

    @GetMapping("/sites")
    public String home(Model model) {
        Iterable<Site> listSites = siteservice.getSites();
        model.addAttribute("sites", listSites);
        return "site/homeSite";
    }

    @GetMapping("/createSite")
    public String createSite(Model model) {
        Site a = new Site();
        model.addAttribute("site", a);

        Iterable<Pays> listPays = paysService.getLesPays();
        model.addAttribute("listPays", listPays);

        return "site/formNewSite";
    }

    @GetMapping("/updateSite/{id}")
    public String updateSite(@PathVariable("id") final int id, Model model) {
        Site a = siteservice.getSite(id);
        model.addAttribute("site", a);
        return "site/formUpdateSite";
    }

    @GetMapping("/deleteSite/{id}")
    public ModelAndView deleteSite(@PathVariable("id") final int id) {
        siteservice.deleteSite(id);
        return new ModelAndView("redirect:/sites");
    }

    @PostMapping("/saveSite")
    public ModelAndView saveSite(@ModelAttribute Site site) {
        System.out.println("controller save=" + site.getNom());
//        if(site.getId() != null) {
//            Site current = siteservice.getSite(site.getId());
//            site.setNom(current.getNom());
//        }
        siteservice.saveSite(site);
        return new ModelAndView("redirect:/sites");
    }
}
