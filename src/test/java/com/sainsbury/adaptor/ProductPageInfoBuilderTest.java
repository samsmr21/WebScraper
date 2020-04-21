package com.sainsbury.adaptor;

import com.sainsbury.domain.ProductPageInfo;
import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductPageInfoBuilderTest {

    public static final String TEST_PRODUCT_DESCRIPTION = "Test product description";
    public static final Integer NUTRITION_VALUE = 33;
    private ProductDescriptionParser productDescriptionParser = mock(ProductDescriptionParser.class);
    private ProductNutritionInfoParser productNutritionInfoParser = mock(ProductNutritionInfoParser.class);

    private ProductPageInfoBuilder productPageInfoBuilder;
    private Document document = new Document("some document");

    @Before
    public void setup() {
        productPageInfoBuilder = new ProductPageInfoBuilder(productDescriptionParser,
                productNutritionInfoParser);
        when(productDescriptionParser.getProductDescription(document)).thenReturn(TEST_PRODUCT_DESCRIPTION);
        when(productNutritionInfoParser.getProductNutritionValue(document)).thenReturn(NUTRITION_VALUE);
    }

    @Test
    public void shouldReturnProductPageInfo() {
        ProductPageInfo productPageInfo = productPageInfoBuilder.buildProductPageInfo(document);

        assertThat(productPageInfo).isNotNull();
        assertThat(productPageInfo.getDescription()).isEqualTo(TEST_PRODUCT_DESCRIPTION);
        assertThat(productPageInfo.getKcalPer100g()).isEqualTo(NUTRITION_VALUE);
    }

    @Test(expected = ProductParsingException.class)
    public void receivedExceptionIsThrownUpStream() {

        givenExceptionWhileRetrievingDescription();

        productPageInfoBuilder.buildProductPageInfo(document);
    }

    private void givenExceptionWhileRetrievingDescription() {
        when(productDescriptionParser.getProductDescription(document)).thenThrow(ProductParsingException.class);
    }
}