package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductDescriptionParserTest {

    public static final String PRODUCT_DESCRIPTION = "by Sainsbury's strawberries";
    private Document input;
    private ProductDescriptionParser productDescriptionParser;

    @Before
    public void setup() {
        productDescriptionParser = new ProductDescriptionParser();
    }

    @Test
    public void shouldReturnProductDescriptionWhenInputInCorrectFormat() {
        givenAValidInput();

        String productDescription = productDescriptionParser.getProductDescription(input);

        assertThat(productDescription).isEqualTo(PRODUCT_DESCRIPTION);
    }

    @Test(expected = ProductParsingException.class)
    public void shouldThrowAParsingExceptionWhenInputFormatIsWrong() {
        givenAnInvalidInput();

        productDescriptionParser.getProductDescription(input);
    }

    private void givenAValidInput() {
        String html = "<h3 class=\"productDataItemHeader\">Description</h3>\n"
                + "<div class=\"productText\"> <p>" + PRODUCT_DESCRIPTION + "</p>\n"
                + "<p></p><p></p><p></p></div>";
        input = Jsoup.parse(html);
    }

    private void givenAnInvalidInput() {
        String html = "<h3 class=\"productDataItemHeader\">Description</h3>\n"
                + "<div class=\"productTextIncorrectFormat\"> <p>" + PRODUCT_DESCRIPTION + "</p>\n"
                + "<p></p><p></p><p></p></div>";
        input = Jsoup.parse(html);
    }

}