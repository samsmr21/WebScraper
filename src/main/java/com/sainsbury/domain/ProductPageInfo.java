package com.sainsbury.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductPageInfo {

    private Double kcalPer100g;

    private String description;
}
