package bts.sio.webapp.service;

import bts.sio.webapp.model.Site;
import bts.sio.webapp.repository.SiteProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SiteService {

    @Autowired
    private SiteProxy siteProxy;

    public Site getSite(final int id) {
        return siteProxy.getSite(id);
    }

    public Iterable<Site> getSites() {
        return siteProxy.getSites();
    }

    public void deleteSite(final int id) {
        siteProxy.deleteSite(id);
    }

    public Site saveSite(Site site) {
        Site savedSite;

        // Functional rule : Last name must be capitalized.
        site.setNom(site.getNom().toUpperCase());

        if(site.getId() == null) {
            // If id is null, then it is a new employee.
            savedSite = siteProxy.createSite(site);
        } else {
            savedSite = siteProxy.updateSite(site);
        }

        return savedSite;
    }
}
