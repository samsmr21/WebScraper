package com.sainsbury.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class Product {

    private String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer kcalPer100g;

    private BigDecimal unitPrice;

    private String description;
}
