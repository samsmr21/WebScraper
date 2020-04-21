package com.sainsbury.service;

import com.sainsbury.domain.ProductInfo;
import com.sainsbury.dto.Product;
import com.sainsbury.dto.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductTransformer {

    public Products transformDomainToDto(List<ProductInfo> productInfoList) {
        List<Product> products = new ArrayList<>();
        productInfoList.stream().forEach(productInfo -> products.add(transformDomainToDto(productInfo)));
        return new Products(products);
    }

    public Product transformDomainToDto(ProductInfo productInfo) {
        return Product.builder()
                .title(productInfo.getTitle())
                .kcalPer100g(productInfo.getProductPageInfo().getKcalPer100g())
                .unitPrice(productInfo.getUnitPrice())
                .description(productInfo.getProductPageInfo().getDescription())
                .build();
    }
}
