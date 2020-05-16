package org.url.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Class StringCreator - generate individual string codes
 *
 * @author Maksim Tiunchik (senebh@gmail.com)
 * @version 0.1
 * @since 11.05.2020
 */
@Component
@Scope("singleton")
public class StringCreator {

    /**
     * inner logger
     */
    private final Logger log = LoggerFactory.getLogger(StringCreator.class);

    /**
     * random
     */
    private final Random random = new Random();

    /**
     * generate individual string code
     *
     * @return string code
     */
    public String generateStringCode() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 8;
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
