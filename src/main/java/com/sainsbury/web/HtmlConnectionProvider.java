package com.sainsbury.web;

import com.sainsbury.exceptions.InvalidSourcePathException;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class HtmlConnectionProvider {

    UrlValidator defaultValidator = new UrlValidator();
    private final String connectionString;

    public HtmlConnectionProvider(String connectionString) {
        if(!defaultValidator.isValid(connectionString))
            throw new InvalidSourcePathException("Invalid url: " + connectionString);
        this.connectionString = connectionString;
    }

    public Connection getConnection() {
        return Jsoup.connect(connectionString);
    }
}
