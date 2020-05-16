/**
 * Package org.url.controllers for
 *
 * @author Maksim Tiunchik
 */
package org.url.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.url.config.JwtProvider;
import org.url.dataservice.DataBase;
import org.url.models.Site;
import org.url.utils.StringCreator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Class RegController -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.05.2020
 */
@RestController
public class RegController {

    @Autowired
    private StringCreator creator;

    @Autowired
    private DataBase repository;

    @Autowired
    private JwtProvider jwtProvider;

    private final JsonMapper mapper = new JsonMapper();

    @PostMapping(value = "/registration")
    public String registration(@RequestBody String siteName) {
        Site site = repository.findByLoginOrSite("none", siteName);
        ObjectNode jsonAnswer = mapper.createObjectNode();
        if (site == null) {
            site = createSiteInfo(siteName);
            site = repository.save(site);
            if (site.getId() != 0) {
                jsonAnswer.put("registration", true);
            }
        } else {
            jsonAnswer.put("registration", false);
        }
        jsonAnswer.put("login", site.getLogin());
        jsonAnswer.put("password", site.getPassword());
        return jsonAnswer.toString();
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Site site) {
        site = repository.findByLoginAndPassword(site.getLogin(), site.getPassword());
        String token = jwtProvider.generateJwtToken(site.getLogin());
        ObjectNode answer = mapper.createObjectNode();
        answer.put("authorization", token);
        return answer.toString();
    }

    public Site createSiteInfo(String siteName) {
        Site site = new Site();
        site.setSite(siteName);
        site.setLogin(creator.generateStringCode());
        site.setPassword(creator.generateStringCode());
        return site;
    }

}
