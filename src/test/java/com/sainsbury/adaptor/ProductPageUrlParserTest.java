package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductPageUrlParserTest {

    public static final String PRODUCT_PAGE_URL = "http://someproduct.com";
    private Element input;
    private ProductPageUrlParser productPageUrlParser;

    @Before
    public void setup() {
        productPageUrlParser = new ProductPageUrlParser();
    }

    @Test
    public void shouldReturnProductPageUrlWhenInputIsValid() {
        givenAValidInput();

        String productPageUrl = productPageUrlParser.getProductPageUrl(input);

        assertThat(productPageUrl).isEqualTo(PRODUCT_PAGE_URL);
    }

    @Test(expected = ProductParsingException.class)
    public void shouldThrowAParsingExceptionWhenInputFormatIsWrong() {
        givenAnInvalidInput();

        productPageUrlParser.getProductPageUrl(input);
    }

    private void givenAValidInput() {
        String html = "<div id=\"hasCrossSell_124101\" class=\"product hasCrossSell \"> \n" +
                "<div class=\"productInfo\">\n" +
                "<div class=\"productNameAndPromotions\">\n" +
                "<h3>\n" +
                "<a href=\"" + PRODUCT_PAGE_URL + "\">\n" +
                "Sainsbury's Blueberries 400g\n" +
                "<img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/ExtendedSitesCatalogAssetStore/images/catalog/productImages/74/0000001835274/0000001835274_L.jpg\" alt=\"\">\n" +
                "</a></h3></div></div>";
        input = Jsoup.parse(html);
    }

    private void givenAnInvalidInput() {
        String html = "<div id=\"hasCrossSell_124101\" class=\"product hasCrossSell \"> \n" +
                "<div class=\"productInfo\">\n" +
                "<div class=\"productNameAndPromotions\">\n" +
                "<h3> <div>\n" +
                "Sainsbury's Blueberries 400g\n" +
                "<img src=\"../../../../../../../c2.sainsburys.co.uk/wcsstore7.23.1.52/ExtendedSitesCatalogAssetStore/images/catalog/productImages/74/0000001835274/0000001835274_L.jpg\" alt=\"\">\n" +
                "</div></h3></div></div>";
        input = Jsoup.parse(html);
    }
}