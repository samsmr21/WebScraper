package com.sainsbury.service;

import com.sainsbury.domain.ProductInfo;
import com.sainsbury.domain.ProductPageInfo;
import com.sainsbury.dto.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTransformerTest {

    public static final String TEST_PRODUCT_TITLE = "Test product title";
    public static final String TEST_PRODUCT_DESCRIPTION = "Test product description";
    public static final BigDecimal UNIT_PRICE = BigDecimal.valueOf(22.7);
    public static final Integer NUTRITION_VALUE = 35;
    private ProductTransformer productTransformer;

    private ProductInfo productInfo;

    @Before
    public void setup() {
        productTransformer = new ProductTransformer();
        productInfo = ProductInfo
                .builder()
                .title(TEST_PRODUCT_TITLE)
                .unitPrice(UNIT_PRICE)
                .productPageInfo(ProductPageInfo
                        .builder()
                        .description(TEST_PRODUCT_DESCRIPTION)
                        .kcalPer100g(NUTRITION_VALUE)
                        .build())
                .build();
    }

    @Test
    public void shouldTransformFromDomainToDto() {
        Product product = productTransformer.transformDomainToDto(productInfo);

        assertThat(product).isNotNull();
        assertThat(product.getTitle()).isEqualTo(TEST_PRODUCT_TITLE);
        assertThat(product.getKcalPer100g()).isEqualTo(NUTRITION_VALUE);
        assertThat(product.getUnitPrice()).isEqualTo(UNIT_PRICE);
        assertThat(product.getDescription()).isEqualTo(TEST_PRODUCT_DESCRIPTION);
    }

}