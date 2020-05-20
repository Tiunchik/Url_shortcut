/**
 * Package org.url.controllers for
 *
 * @author Maksim Tiunchik
 */
package org.url.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.HttpResource;
import org.springframework.web.servlet.view.RedirectView;
import org.url.dataservice.DataBase;
import org.url.models.Site;
import org.url.models.Url;
import org.url.utils.StringCreator;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * Class UrlController -
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.05.2020
 */
@RestController
public class UrlController {

    @Autowired
    private StringCreator creator;

    @Autowired
    DataBase repository;

    private final JsonMapper mapper = new JsonMapper();

    @PostMapping(value = "/convert")
    public ResponseEntity<String> addUrl(@RequestBody Url url, Principal principal) {
        if (repository.findUrlByUrl(url.getUrl()) != null) {
            return new ResponseEntity<>("This site has benn alredy registered", HttpStatus.BAD_REQUEST);
        }
        String login = principal.getName();
        url.setSite(repository.findByLoginOrSite(login, login));
        url.setCode(login + creator.generateStringCode());
        repository.save(url);
        ObjectNode answer = mapper.createObjectNode();
        answer.put("code", url.getCode());
        return new ResponseEntity<>(answer.toString(), HttpStatus.OK);
    }

    @GetMapping(value = "/redirect/{code}")
    @ResponseStatus(HttpStatus.FOUND)
    public RedirectView getUrl(@PathVariable String code, HttpServletRequest request) {
        Url url = repository.findByCode(code);
        repository.upCounter(url.getCode());
        return new RedirectView(url.getUrl());
    }

    @PostMapping(value = "/statistic")
    public ResponseEntity<String> getStatistic(Principal principal) {
        String login = principal.getName();
        Site site = repository.findByLoginOrSite(login, login);
        List<Url> urls = repository.findBySite(site);
        ObjectNode temp = mapper.createObjectNode();
        temp.put("url", "URL").put("total", 0);
        ArrayNode answer = mapper.createArrayNode();
        answer.add(temp);
        for (var ex : urls) {
            temp = mapper.createObjectNode();
            temp.put("url", ex.getUrl()).put("total", ex.getStatistic());
            answer.add(temp);
        }
        return new ResponseEntity<>(answer.toString(), HttpStatus.OK);
    }
}
