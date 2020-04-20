package com.sainsbury.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ProductInfo {

    private String title;

    private BigDecimal unitPrice;

    private String productPageUrl;

    private ProductPageInfo productPageInfo;
}
