package com.sainsbury.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductInfo {

    private String title;

    private BigDecimal unitPrice;

    private String productPageUrl;

    @Setter
    private ProductPageInfo productPageInfo;
}
