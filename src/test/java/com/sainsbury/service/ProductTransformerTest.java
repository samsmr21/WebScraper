package com.sainsbury.service;

import com.sainsbury.domain.ProductInfo;
import com.sainsbury.domain.ProductPageInfo;
import com.sainsbury.dto.Product;
import com.sainsbury.dto.Products;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTransformerTest {

    public static final String TEST_PRODUCT_TITLE = "Test product title";
    public static final String TEST_PRODUCT_DESCRIPTION = "Test product description";
    public static final BigDecimal UNIT_PRICE1 = BigDecimal.valueOf(22.7);
    public static final BigDecimal UNIT_PRICE2 = BigDecimal.valueOf(37.3);
    public static final BigDecimal UNIT_PRICE3 = BigDecimal.valueOf(12.5);
    public static final Integer NUTRITION_VALUE = 35;
    private ProductTransformer productTransformer;

    private ProductInfo productInfo1;
    private ProductInfo productInfo2;
    private ProductInfo productInfo3;

    @Before
    public void setup() {
        productTransformer = new ProductTransformer();
        productInfo1 = buildProductInfo(UNIT_PRICE1);
        productInfo2 = buildProductInfo(UNIT_PRICE2);
        productInfo3 = buildProductInfo(UNIT_PRICE3);
    }

    private ProductInfo buildProductInfo(BigDecimal unitPrice) {
        return ProductInfo
                .builder()
                .title(TEST_PRODUCT_TITLE)
                .unitPrice(unitPrice)
                .productPageInfo(ProductPageInfo
                        .builder()
                        .description(TEST_PRODUCT_DESCRIPTION)
                        .kcalPer100g(NUTRITION_VALUE)
                        .build())
                .build();
    }

    @Test
    public void shouldTransformFromDomainToDto() {
        Product product = productTransformer.transformDomainToDto(productInfo1);

        assertThat(product).isNotNull();
        assertThat(product.getTitle()).isEqualTo(TEST_PRODUCT_TITLE);
        assertThat(product.getKcalPer100g()).isEqualTo(NUTRITION_VALUE);
        assertThat(product.getUnitPrice()).isEqualTo(UNIT_PRICE1);
        assertThat(product.getDescription()).isEqualTo(TEST_PRODUCT_DESCRIPTION);
    }

    @Test
    public void shouldTransformListFromDomainToDto() {
        Products products = productTransformer.transformDomainToDto(Arrays.asList(productInfo1, productInfo2, productInfo3));

        assertThat(products).isNotNull();
        assertThat(products.getTotal().getGross()).isEqualTo(BigDecimal.valueOf(72.50).setScale(2));
        assertThat(products.getTotal().getVat()).isEqualTo(BigDecimal.valueOf(12.10).setScale(2));
        assertThat(products.getResults().size()).isEqualTo(3);
        assertProduct(products.getResults().get(0), UNIT_PRICE1);
        assertProduct(products.getResults().get(1), UNIT_PRICE2);
        assertProduct(products.getResults().get(2), UNIT_PRICE3);
    }

    private void assertProduct(Product product, BigDecimal unitPrice) {
        assertThat(product.getTitle()).isEqualTo(TEST_PRODUCT_TITLE);
        assertThat(product.getKcalPer100g()).isEqualTo(NUTRITION_VALUE);
        assertThat(product.getUnitPrice()).isEqualTo(unitPrice);
        assertThat(product.getDescription()).isEqualTo(TEST_PRODUCT_DESCRIPTION);
    }
}