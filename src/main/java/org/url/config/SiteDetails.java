package org.url.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.url.models.Site;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Class SiteDetails -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 16.05.2020
 */
@Component
public class SiteDetails implements UserDetails {

    private final Logger log = LoggerFactory.getLogger(SiteDetails.class);

    private String siteName;

    private String login;

    private String password;

    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static SiteDetails createsiteDetails(Site site) {
        SiteDetails details = new SiteDetails();
        details.siteName = site.getSite();
        details.login = site.getLogin();
        details.password = site.getPassword();
        details.grantedAuthorities = new ArrayList<>(List.of((GrantedAuthority) () -> "ROLE_ACCEPT"));
        return details;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
