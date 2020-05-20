/**
 * Package org.url.dataservice for
 *
 * @author Maksim Tiunchik
 */
package org.url.dataservice;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.url.models.Site;
import org.url.models.Url;

import javax.transaction.Transactional;
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

    @Modifying
    @Transactional
    @Query(value = "UPDATE url SET statistic = statistic + 1 WHERE code = :code", nativeQuery = true)
    void upCounter(@Param("code") String code);

}
