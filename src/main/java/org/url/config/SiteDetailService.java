package org.url.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.url.dataservice.DataBase;
import org.url.models.Site;

/**
 * Class SiteDetailService -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 16.05.2020
 */
@Component
public class SiteDetailService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(SiteDetailService.class);

    @Autowired
    private DataBase repository;

    @Override
    public SiteDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Site site = repository.findByLoginOrSite(s, s);
        return SiteDetails.createsiteDetails(site);
    }

}
