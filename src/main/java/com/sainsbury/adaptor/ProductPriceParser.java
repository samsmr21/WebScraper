package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductPriceParser {

    public BigDecimal getProductPrice(Element element) {
        try {
            String priceString = element.selectFirst("p.pricePerUnit").text();
            return new BigDecimal(priceString.replaceAll("[^0-9, ^.]", "")).setScale(2, RoundingMode.DOWN);
        } catch(Exception e) {
            throw new ProductParsingException("Failed to parse price: " + e.getMessage());
        }
    }
}
