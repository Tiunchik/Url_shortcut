/**
 * Package org.url.dataservice for
 *
 * @author Maksim Tiunchik
 */
package org.url.dataservice;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.url.models.Site;

/**
 * Interface SiteRepository -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.05.2020
 */
public interface SiteRepository extends PagingAndSortingRepository<Site, Long> {

    Site findByLoginOrSite(String login, String site);

    Site findByLoginAndPassword(String login, String password);

}
