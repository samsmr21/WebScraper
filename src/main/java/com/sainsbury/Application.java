package com.sainsbury;

import com.sainsbury.adaptor.*;
import com.sainsbury.dto.Products;
import com.sainsbury.service.PrintingTextService;
import com.sainsbury.service.ProductTransformer;
import com.sainsbury.service.ProductsProcessingService;

import java.util.logging.Logger;

public class Application {

  private static final Logger logger = Logger.getLogger(Application.class.getName());

  public static void main(String[] args) {

    String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";

    try {
      PrintingTextService printingTextService = new PrintingTextService();
      ProductsProcessingService productsProcessingService = getProductsProcessingService();

      Products products = productsProcessingService.processProductsHomePage(url);
      printingTextService.printToConsole(products);
    }catch (Exception e){
      logger.severe(e.getMessage());
    }
  }

  private static ProductsProcessingService getProductsProcessingService() {
    ProductInfoBuilder productInfoBuilder = new ProductInfoBuilder(new ProductTitleParser(),
            new ProductPriceParser(),
            new ProductPageUrlParser());
    ProductPageInfoBuilder productPageInfoBuilder = new ProductPageInfoBuilder(new ProductDescriptionParser(),
            new ProductNutritionInfoParser());
    return new ProductsProcessingService(productInfoBuilder,
            productPageInfoBuilder,
            new ProductTransformer());
  }
}
