package com.sainsbury.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class Total {

    private BigDecimal gross;

    private BigDecimal vat;
}
