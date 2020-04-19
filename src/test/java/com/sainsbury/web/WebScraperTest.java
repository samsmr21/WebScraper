package com.sainsbury.web;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WebScraperTest {

    private static final String TEST_URI = "http://testurl";

    private HtmlConnectionProvider htmlConnectionProvider = mock(HtmlConnectionProvider.class);
    private Connection connection = mock(Connection.class);

    private WebScraper webScraper;
    private Document document;

    @Before
    public void setup() {
        webScraper = new WebScraper(htmlConnectionProvider);
        document = new Document(TEST_URI);
        when(htmlConnectionProvider.getConnection()).thenReturn(connection);
    }

    @Test
    public void documentIsReturnForAValidConnection() throws IOException {
        givenAValidConnection();

        Optional<Document> mayBeDocument = webScraper.getDocument();

        assertThat(mayBeDocument).isPresent();
        assertThat(mayBeDocument.get()).isEqualTo(document);
    }

    @Test
    public void emptyResponseIsReturnForAnInvalidConnection() throws IOException {
        givenAnInvalidConnection();

        Optional<Document> mayBeDocument = webScraper.getDocument();

        assertThat(mayBeDocument).isEmpty();
    }

    private void givenAnInvalidConnection() throws IOException {
        when(connection.get()).thenThrow(IOException.class);
    }

    private void givenAValidConnection() throws IOException {
        when(connection.get()).thenReturn(document);
    }
}