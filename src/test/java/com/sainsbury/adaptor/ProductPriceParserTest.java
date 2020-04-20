package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPriceParserTest {

    private Element input;
    private ProductPriceParser productPriceParser;

    @Before
    public void setup() {
        productPriceParser = new ProductPriceParser();
    }

    @Test
    public void shouldReturnPriceWhenInputInCorrectFormat() {
        givenAValidInput();
        BigDecimal pricePerUnit = productPriceParser.getProductPrice(input);

        assertThat(pricePerUnit).isEqualTo(BigDecimal.valueOf(1.75));
    }

    @Test(expected = ProductParsingException.class)
    public void shouldThrowAParsingExceptionWhenInputFormatIsWrong() {
        givenAnInvalidInput();

        productPriceParser.getProductPrice(input);
    }

    private void givenAnInvalidInput() {
        StringBuffer html = new StringBuffer();
        html.append("<div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124183\"> " );
        html.append("<div class=\"pricing\"> ");
        html.append("<p class=\"price\"> £1.75<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr> </p> ");
        html.append("<p class=\"pricePerMeasure\">£4.38<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr> </p> ");
        html.append("</div> </div>");
        input = Jsoup.parse(html.toString());
    }

    private void givenAValidInput() {
        StringBuffer html = new StringBuffer();
        html.append("<div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124183\"> " );
        html.append("<div class=\"pricing\"> ");
        html.append("<p class=\"pricePerUnit\"> £1.75<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr> </p> ");
        html.append("<p class=\"pricePerMeasure\">£4.38<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr> </p> ");
        html.append("</div> </div>");
        input = Jsoup.parse(html.toString());
    }
}