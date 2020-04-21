package com.sainsbury.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Setter
public class Products {

    private List<Product> results;

    private Total total;

    public Products(List<Product> products) {
        this.results = products;
        MathContext mc = new MathContext(3, RoundingMode.HALF_EVEN);
        BigDecimal gross = products.stream()
                .map(Product::getUnitPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, BigDecimal.ROUND_DOWN);
        total = new Total(gross, gross.divide(BigDecimal.valueOf(6), mc).setScale(2));
    }
}
