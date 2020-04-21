package com.sainsbury.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductPageInfo {

    private Integer kcalPer100g;

    private String description;
}
