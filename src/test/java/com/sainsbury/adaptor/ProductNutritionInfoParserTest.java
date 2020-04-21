package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductNutritionInfoParserTest {

    public static final String NUTRITION_VALUE = "33";
    private Document input;
    private ProductNutritionInfoParser productNutritionInfoParser;

    @Before
    public void setup() {
        productNutritionInfoParser = new ProductNutritionInfoParser();
    }

    @Test
    public void shouldReturnNutritionValueWhenTableWithSingleRow() {
        givenAValidInputWithSingleSpanRow();

        Integer nutritionValue = productNutritionInfoParser.getProductNutritionValue(input);

        assertThat(nutritionValue).isEqualTo(Integer.parseInt(NUTRITION_VALUE));
    }

    @Test
    public void shouldReturnNutritionValueWhenTableWithDoubleRow() {
        givenAValidInputWithDoubleSpanRow();

        Integer nutritionValue = productNutritionInfoParser.getProductNutritionValue(input);

        assertThat(nutritionValue).isEqualTo(Integer.parseInt(NUTRITION_VALUE));
    }

    @Test
    public void shouldReturnNullNutritionValueWhenNoTableFound() {
        givenInputWithoutNutritionTable();

        Integer nutritionValue = productNutritionInfoParser.getProductNutritionValue(input);

        assertThat(nutritionValue).isNull();
    }

    @Test(expected = ProductParsingException.class)
    public void shouldThrowAParsingExceptionWhenInputFormatIsWrong() {
        givenAnInvalidInput();

        productNutritionInfoParser.getProductNutritionValue(input);
    }

    private void givenAValidInputWithDoubleSpanRow() {
        String html =  "<div class=\"tableWrapper\">\n" +
                "<table class=\"nutritionTable\">\n" +
                "<thead>\n" +
                "<tr class=\"tableTitleRow\">\n" +
                "<th scope=\"col\">Typical Values</th><th scope=\"col\">Per 100g&nbsp;</th><th scope=\"col\">% based on RI for Average Adult</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody><tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\" rowspan=\"2\">Energy</th><td class=\"tableRow1\">140kJ</td><td class=\"tableRow1\">-</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow0\">\n" +
                "<td class=\"nutritionLevel1\">" + NUTRITION_VALUE + "kcal</td><td class=\"nutritionLevel1\">2%</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Fat</th><td class=\"nutritionLevel1\">&lt;0.5g</td><td class=\"nutritionLevel1\">-</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow0\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Saturates</th><td class=\"nutritionLevel1\">&lt;0.1g</td><td class=\"nutritionLevel1\">-</td>\n" +
                "</tr></tbody></table>";
        input = Jsoup.parse(html);
    }

    private void givenAValidInputWithSingleSpanRow() {

        String html = "<div class=\"textualNutrition\">\n"
                + "<h3>Nutrition</h3>\n"
                + "<p>\n"
                + "<strong>Table of Nutritional Information</strong>\n"
                + "</p>\n"
                + "<div xmlns:asp=\"remove\" class=\"tableWrapper\">\n"
                + "<table class=\"nutritionTable\">\n"
                + "<thead>\n"
                + "<tr>\n"
                + "<th scope=\"col\"></th><th scope=\"col\">(as sold) per 100g</th><th scope=\"col\"> % adult RI per 100g</th><th scope=\"col\">adult RI</th>\n"
                + "</tr>\n"
                + "</thead>\n"
                + "<tbody>\n"
                + "<tr>\n"
                + "<th scope=\"row\" class=\"rowHeader\">Energy kJ</th><td>222</td><td>-</td><td>8400</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<th scope=\"row\" class=\"rowHeader\">Energy kcal</th><td> " + NUTRITION_VALUE + " </td><td>3%</td><td>2000</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<th scope=\"row\" class=\"rowHeader\">Fat </th><td>&lt;0.5g</td><td>&lt;1%</td><td>70g</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<th scope=\"row\"> of which saturates </th><td>&lt;0.1g</td><td>&lt;1%</td><td>20g</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<th scope=\"row\" class=\"rowHeader\">Carbohydrate </th><td>11.5g</td><td>4%</td><td>260g</td>\n"
                + "</tr>\n"
                + "<tr>\n"
                + "<th scope=\"row\"> of which sugars </th><td>11.5g</td><td>13%</td><td>90g</td>\n"
                + "</tr>\n"
                + "</tbody>\n"
                + "</table>\n"
                + "</div>\n"
                + "<div class=\"nameTextLookups\"></div>\n"
                + "</div>";
        input = Jsoup.parse(html);
    }

    private void givenInputWithoutNutritionTable() {
        String html =  "<h3>Nutrition</h3>\n"
                + "<p>\n"
                + "<strong>Table of Nutritional Information</strong>\n"
                + "</p>\n"
                + "<div xmlns:asp=\"remove\" class=\"tableWrapper\">\n</div>";
        input = Jsoup.parse(html);
    }

    private void givenAnInvalidInput() {
        String html =  "<div class=\"tableWrapper\">\n" +
                "<table class=\"nutritionTable\">\n" +
                "<thead>\n" +
                "<tr class=\"tableTitleRow\">\n" +
                "<th scope=\"col\">Typical Values</th><th scope=\"col\">Per 100g&nbsp;</th><th scope=\"col\">% based on RI for Average Adult</th>\n" +
                "</tr>\n" +
                "</thead>\n" +
                "<tbody><tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\" rowspan=\"2\">Energy</th><td class=\"tableRow1\">140kJ</td><td class=\"tableRow1\">-</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow0\">\n" +
                "<td class=\"nutritionLevel1\">kcal</td><td class=\"nutritionLevel1\">2%</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow1\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Fat</th><td class=\"nutritionLevel1\">&lt;0.5g</td><td class=\"nutritionLevel1\">-</td>\n" +
                "</tr>\n" +
                "<tr class=\"tableRow0\">\n" +
                "<th scope=\"row\" class=\"rowHeader\">Saturates</th><td class=\"nutritionLevel1\">&lt;0.1g</td><td class=\"nutritionLevel1\">-</td>\n" +
                "</tr></tbody></table>";
        input = Jsoup.parse(html);
    }

}