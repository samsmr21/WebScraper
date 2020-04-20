package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.nodes.Element;

public class ProductPageUrlParser {

    public String getProductPageUrl(Element element) {
        try {
            return element
                    .selectFirst("div.productNameAndPromotions")
                    .select("a")
                    .first()
                    .attr("abs:href");
        }catch(Exception e) {
            throw new ProductParsingException("Failed to retrieve product page url: " + e.getMessage());
        }
    }
}
