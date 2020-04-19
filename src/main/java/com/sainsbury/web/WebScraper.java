package com.sainsbury.web;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class WebScraper {

    private static final Logger logger = Logger.getLogger(WebScraper.class.getName());

    private final HtmlConnectionProvider htmlConnectionProvider;

    public WebScraper(HtmlConnectionProvider htmlConnectionProvider) {
        this.htmlConnectionProvider = htmlConnectionProvider;
    }

    public Optional<Document> getDocument() {
        try {
            return Optional.of(htmlConnectionProvider.getConnection().get());
        } catch (IOException e) {
            logger.severe("Invalid connection: " + e.getMessage());
            return Optional.empty();
        }
    }
}
