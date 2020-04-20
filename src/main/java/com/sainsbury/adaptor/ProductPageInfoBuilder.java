package com.sainsbury.adaptor;

import com.sainsbury.domain.ProductPageInfo;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;

@AllArgsConstructor
public class ProductPageInfoBuilder {

    private final ProductDescriptionParser productDescriptionParser;
    private final ProductNutritionInfoParser productNutritionInfoParser;

    public ProductPageInfo buildProductPageInfo(Document productPageDocument) {

        return ProductPageInfo
                .builder()
                .description(productDescriptionParser.getProductDescription(productPageDocument))
                .kcalPer100g(productNutritionInfoParser.getProductNutritionValue(productPageDocument))
                .build();
    }
}
