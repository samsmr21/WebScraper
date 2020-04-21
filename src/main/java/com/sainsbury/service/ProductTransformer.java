package com.sainsbury.service;

import com.sainsbury.domain.ProductInfo;
import com.sainsbury.dto.Product;

public class ProductTransformer {

    public Product transformDomainToDto(ProductInfo productInfo) {
        return Product.builder()
                .title(productInfo.getTitle())
                .kcalPer100g(productInfo.getProductPageInfo().getKcalPer100g())
                .unitPrice(productInfo.getUnitPrice())
                .description(productInfo.getProductPageInfo().getDescription())
                .build();
    }
}
