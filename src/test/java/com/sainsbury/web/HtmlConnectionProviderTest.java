package com.sainsbury.web;

import com.sainsbury.exceptions.InvalidSourcePathException;
import org.jsoup.Connection;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HtmlConnectionProviderTest {

    private HtmlConnectionProvider htmlConnectionProvider;

    private static String VALID_URL = "http://testUrl.com";
    private static String INVALID_URL = "http:/testUrl.com";

    @Test
    public void aConnectionIsReturnedForAValidPath() {
        htmlConnectionProvider = new HtmlConnectionProvider(VALID_URL);
        Connection connection = htmlConnectionProvider.getConnection();

        assertThat(connection).isNotNull();
        assertThat(connection.request().url().toString()).isEqualTo(VALID_URL);
    }

    @Test(expected = InvalidSourcePathException.class)
    public void anExceptionIsReturnedForAnInValidPath() {
        htmlConnectionProvider = new HtmlConnectionProvider(INVALID_URL);
    }
}