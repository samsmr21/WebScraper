package com.sainsbury.adaptor;

import com.sainsbury.domain.ProductInfo;
import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductInfoBuilderTest {

    public static final String TEST_URL = "test url";
    public static final BigDecimal PRICE_PER_UNIT = BigDecimal.valueOf(12.12);
    public static final String TEST_PRODUCT_TITLE = "test product title";

    private ProductTitleParser productTitleParser = mock(ProductTitleParser.class);
    private ProductPriceParser productPriceParser = mock(ProductPriceParser.class);
    private ProductPageUrlParser productPageUrlParser = mock(ProductPageUrlParser.class);

    private ProductInfoBuilder productInfoBuilder;
    private Element element = new Element("test html");

    @Before
    public void setup() {
        productInfoBuilder = new ProductInfoBuilder(productTitleParser,
                productPriceParser,
                productPageUrlParser);
        when(productPageUrlParser.getProductPageUrl(element)).thenReturn(TEST_URL);
        when(productPriceParser.getProductPrice(element)).thenReturn(PRICE_PER_UNIT);
        when(productTitleParser.getProductTitle(element)).thenReturn(TEST_PRODUCT_TITLE);
    }

    @Test
    public void shouldReturnProductInfo() {

        ProductInfo productInfo = productInfoBuilder.buildProductInfo(element);

        assertThat(productInfo).isNotNull();
        assertThat(productInfo.getProductPageUrl()).isEqualTo(TEST_URL);
        assertThat(productInfo.getTitle()).isEqualTo(TEST_PRODUCT_TITLE);
        assertThat(productInfo.getUnitPrice()).isEqualTo(PRICE_PER_UNIT);
    }

    @Test(expected = ProductParsingException.class)
    public void receivedExceptionIsThrownUpStream() {

        givenExceptionWhileRetrievingTitle();

        productInfoBuilder.buildProductInfo(element);
    }

    private void givenExceptionWhileRetrievingTitle() {
        when(productTitleParser.getProductTitle(element)).thenThrow(ProductParsingException.class);
    }

}