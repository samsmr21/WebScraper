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

    @Test
    public void shouldReturnPriceWithTwoDecimalPlaces() {
        givenAnInvalidInputWithDecimalPlacesOverTwo();
        BigDecimal pricePerUnit = productPriceParser.getProductPrice(input);

        assertThat(pricePerUnit).isEqualTo(BigDecimal.valueOf(1.75));
    }

    @Test(expected = ProductParsingException.class)
    public void shouldThrowAParsingExceptionWhenInputFormatIsWrong() {
        givenAnInvalidInput();

        productPriceParser.getProductPrice(input);
    }

    private void givenAnInvalidInput() {
        String html = "<div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124183\"> "
                +"<div class=\"pricing\"> "
                +"<p class=\"price\"> £1.75<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr> </p> "
                +"<p class=\"pricePerMeasure\">£4.38<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr> </p> "
                +"</div> </div>";
        input = Jsoup.parse(html);
    }

    private void givenAnInvalidInputWithDecimalPlacesOverTwo() {
        String html = "<div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124183\"> "
                +"<div class=\"pricing\"> "
                +"<p class=\"pricePerUnit\"> £1.75678<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr> </p> "
                +"<p class=\"pricePerMeasure\">£4.38<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr> </p> "
                +"</div> </div>";
        input = Jsoup.parse(html);
    }

    private void givenAValidInput() {
        String html = "<div class=\"priceTab priceTabContainer activeContainer addItem\" id=\"addItem_124183\"> "
                +"<div class=\"pricing\"> "
                +"<p class=\"pricePerUnit\"> £1.75<abbr title=\"per\">/</abbr><abbr title=\"unit\"><span class=\"pricePerUnitUnit\">unit</span></abbr> </p> "
                +"<p class=\"pricePerMeasure\">£4.38<abbr title=\"per\">/</abbr><abbr title=\"kilogram\"><span class=\"pricePerMeasureMeasure\">kg</span></abbr> </p> "
                +"</div> </div>";
        input = Jsoup.parse(html);
    }
}