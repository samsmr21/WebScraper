package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.nodes.Element;

public class ProductTitleParser {

    public String getProductTitle(Element element) {
        try {
            String title = element.selectFirst("div.productNameAndPromotions")
                    .select("a")
                    .first().text();
            return title.split(",")[0];
        } catch(Exception e) {
            throw new ProductParsingException("Failed to parse title: " + e.getMessage());
        }
    }
}
