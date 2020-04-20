package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTitleParserTest {

    public static final String SINGLE_LINE_PRODUCT_TITLE = "Sainsbury's Raspberries 225g";
    public static final String DOUBLE_LINE_PRODUCT_TITLE = "Sainsbury's Raspberries, Taste the Difference 150g";
    private Element input;
    private ProductTitleParser productTitleParser;

    @Before
    public void setup() {
        productTitleParser = new ProductTitleParser();
    }

    @Test
    public void shouldReturnTitleWhenInputInCorrectFormat() {
        givenAValidInput(SINGLE_LINE_PRODUCT_TITLE);

        String productTitle = productTitleParser.getProductTitle(input);

        assertThat(productTitle).isEqualTo("Sainsbury's Raspberries 225g");
    }

    @Test
    public void shouldReturnOnlyFirstLineWhenMultiLineTitle() {
        givenAValidInput(DOUBLE_LINE_PRODUCT_TITLE);

        String productTitle = productTitleParser.getProductTitle(input);

        assertThat(productTitle).isEqualTo("Sainsbury's Raspberries");
    }

    @Test(expected = ProductParsingException.class)
    public void shouldThrowAParsingExceptionWhenInputFormatIsWrong() {
        givenAnInvalidInput();

        productTitleParser.getProductTitle(input);
    }

    private void givenAValidInput(String title) {
        String html =  "<div class=\"product \"> <div class=\"productInfo\"> <div class=\"productNameAndPromotions\">\n "
                +"<h3> <a href=\"productpageurl\"> " + title + "\n"
                +"<img src=\"imageurl\" alt=\"\"> </a> </h3></div> </div>";
        input = Jsoup.parse(html);
    }

    private void givenAnInvalidInput() {
        String html = "<div class=\"product \"> <div class=\"productInfo\"> <div class=\"productNameAndPromotions\">\n "
                +"<h3> <p> random product title </p>"
                +"<img src=\"imageurl\" alt=\"\"> </h3></div> </div>";
        input = Jsoup.parse(html);
    }

}