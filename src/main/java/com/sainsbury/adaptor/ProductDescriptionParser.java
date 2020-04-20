package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.nodes.Document;

public class ProductDescriptionParser {

    public String getProductDescription(Document productPageDocument) {
        try {
            return productPageDocument.select("div.productText").first().select("p").first().text();
        } catch(Exception e) {
            throw new ProductParsingException("Failed to retrieve product description: " + e.getMessage());
        }
    }
}
