/**
 * Package org.url.models for
 *
 * @author Maksim Tiunchik
 */
package org.url.models;

import org.hibernate.annotations.GenerationTime;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Objects;

/**
 * Class Url -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.05.2020
 */
@Entity
@Component
public class Url {

    @Id
    private String code;

    @ManyToOne
    @JoinColumn(name = "site_id", foreignKey = @ForeignKey(name = "SITE_IF_FK"))
    private Site site;

    @Column(nullable = false)
    private String url;


    private long statistic;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getStatistic() {
        return statistic;
    }

    public void setStatistic(long statistic) {
        this.statistic = statistic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Url url1 = (Url) o;
        return code.equals(url1.code)
                && site.equals(url1.site)
                && url.equals(url1.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, site, url);
    }

    @Override
    public String toString() {
        return "Url{"
                + "code='" + code + '\''
                + ", site=" + site.getSite()
                + ", url='" + url + '\''
                + '}';
    }
}
