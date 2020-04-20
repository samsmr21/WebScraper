package com.sainsbury.adaptor;

import com.sainsbury.domain.ProductInfo;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Element;

@AllArgsConstructor
public class ProductInfoBuilder {

    private final ProductTitleParser productTitleParser;
    private final ProductPriceParser productPriceParser;
    private final ProductPageUrlParser productPageUrlParser;

    public ProductInfo buildProductInfo(Element element) {

        return ProductInfo
                .builder()
                .title(productTitleParser.getProductTitle(element))
                .unitPrice(productPriceParser.getProductPrice(element))
                .productPageUrl(productPageUrlParser.getProductPageUrl(element))
                .build();
    }
}
