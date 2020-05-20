/**
 * Package org.url.dataservice for
 *
 * @author Maksim Tiunchik
 */
package org.url.dataservice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.url.models.Site;
import org.url.models.Url;

import java.util.List;

/**
 * Interface UrlRepository -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.05.2020
 */
public interface UrlRepository extends PagingAndSortingRepository<Url, String> {

    List<Url> findBySite(Site site);

    Url findUrlByUrl(String url);

    @Query("UPDATE Url AS t set t.statistic = t.statistic + 1 WHERE t.code = :code")
    Url upCounter(@Param("code") String id);

}
