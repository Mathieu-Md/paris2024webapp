package bts.sio.webapp.repository;


import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Site;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SiteProxy {
    @Autowired
    private CustomProperties props;

    /**
     * Get all sites
     * @return An iterable of all site
     */
    public Iterable<Site> getSites() {

        String baseApiUrl = props.getApiUrl();
        String getSitesUrl = baseApiUrl + "/sites";
        System.out.println("url=" + getSitesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Site>> response = restTemplate.exchange(
                getSitesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Site>>() {}
        );

        log.debug("Get Sites call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an site by the id
     * @param id The id of the site
     * @return The site which matches the id
     */
    public Site getSite(int id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/site/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Site> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Site.class
        );

        log.debug("Get Site call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new site
     * @param a A new site (without an id)
     * @return The site full filled (with an id)
     */
    public Site createSite(Site a) {

        String baseApiUrl = props.getApiUrl();
        String createSiteUrl = baseApiUrl + "/site";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Site> request = new HttpEntity<Site>(a);
        ResponseEntity<Site> response = restTemplate.exchange(
                createSiteUrl,
                HttpMethod.POST,
                request,
                Site.class);

        log.debug("Create Site call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an site - using the PUT HTTP Method.
     * @param e Existing site to update
     */
    public Site updateSite(Site e) {
        String baseApiUrl = props.getApiUrl();
        String updateSiteUrl = baseApiUrl + "/site/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Site> request = new HttpEntity<Site>(e);
        ResponseEntity<Site> response = restTemplate.exchange(
                updateSiteUrl,
                HttpMethod.PUT,
                request,
                Site.class);

        log.debug("Update Site call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /*
     * Delete an site using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The site to delete
     */
    public void deleteSite(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteSiteUrl = baseApiUrl + "/site/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteSiteUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Site call " + response.getStatusCode().toString());
    }
}
