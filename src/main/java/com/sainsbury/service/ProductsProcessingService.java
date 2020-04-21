package com.sainsbury.service;

import com.sainsbury.adaptor.ProductInfoBuilder;
import com.sainsbury.adaptor.ProductPageInfoBuilder;
import com.sainsbury.domain.ProductInfo;
import com.sainsbury.dto.Products;
import com.sainsbury.web.HtmlConnectionProvider;
import com.sainsbury.web.WebScraper;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductsProcessingService {

    private final ProductInfoBuilder productInfoBuilder;
    private final ProductPageInfoBuilder productPageInfoBuilder;
    private final ProductTransformer productTransformer;

    public Products processProductsHomePage(String url) {
        Elements htmlProducts = getHtmlProductList(url);

        List<ProductInfo> productInfoList = getProductInfoList(htmlProducts);
        loadProductPageInfo(productInfoList);

        return productTransformer.transformDomainToDto(productInfoList);
    }

    private void loadProductPageInfo(List<ProductInfo> productInfoList) {
        productInfoList.forEach(productInfo -> {
            Element productPageInfoDocument = getProductPageInfoDocument(productInfo.getProductPageUrl());
            productInfo.setProductPageInfo(productPageInfoBuilder.buildProductPageInfo(productPageInfoDocument));
        });
    }

    private List<ProductInfo> getProductInfoList(Elements htmlProducts) {
        return htmlProducts.stream().map(product -> productInfoBuilder.buildProductInfo(product)).collect(Collectors.toList());
    }

    private Elements getHtmlProductList(String url) {
        return getHtmlDocument(url).getDocument().get().select("div.product");
    }

    private Element getProductPageInfoDocument(String url) {
        return getHtmlDocument(url).getDocument().get();
    }

    private WebScraper getHtmlDocument(String url) {
        HtmlConnectionProvider htmlConnectionProvider = new HtmlConnectionProvider(url);
        return new WebScraper(htmlConnectionProvider);
    }
}
