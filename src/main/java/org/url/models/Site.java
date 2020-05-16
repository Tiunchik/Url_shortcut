/**
 * Package org.url.models for
 *
 * @author Maksim Tiunchik
 */
package org.url.models;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class Site -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.05.2020
 */
@Entity
@Component
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(unique = true)
    private String site;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    private Timestamp created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Site site1 = (Site) o;
        return id == site1.id
                && site.equals(site1.site)
                && login.equals(site1.login)
                && created.equals(site1.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, site, login, created);
    }

    @Override
    public String toString() {
        return "Site{"
                + "id=" + id
                + ", site='" + site + '\''
                + ", login='" + login + '\''
                + ", created=" + created
                + '}';
    }
}
