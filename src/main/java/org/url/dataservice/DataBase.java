/**
 * Package org.url.dataservice for
 *
 * @author Maksim Tiunchik
 */
package org.url.dataservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.url.models.Site;
import org.url.models.Url;

import java.util.LinkedList;
import java.util.List;

/**
 * Class DataBase -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.05.2020
 */
@Repository
@Scope("singleton")
public class DataBase {

    @Autowired
    private SiteRepository siteRep;

    @Autowired
    private UrlRepository urlRep;

    public Url save(Url url) {
        return urlRep.save(url);
    }

    public Site save(Site site) {
        return siteRep.save(site);
    }

    public Site findByLoginOrSite(String login, String site) {
        return siteRep.findByLoginOrSite(login, site);
    }

    public Url findByCode(String code) {
        return urlRep.findById(code).get();
    }

    public List<Url> findAll() {
        List<Url> responseList = new LinkedList<>();
        urlRep.findAll().forEach(responseList::add);
        return responseList;
    }

    public Site findByLoginAndPassword(String login, String password) {
        return siteRep.findByLoginAndPassword(login, password);
    }

    public List<Url> findBySite(Site site) {
        return urlRep.findBySite(site);
    }
}
