package com.sainsbury.adaptor;

import com.sainsbury.exceptions.ProductParsingException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ProductNutritionInfoParser {

    public Integer getProductNutritionValue(Element productPageDocument) {
        try {
            Elements tables =  productPageDocument.select("table.nutritionTable");
            if (tables.size() > 0) {
                Elements rows = tables.first().select("tbody").first().select("tr");
                for(int i=0; i< rows.size(); i++) {
                    if (rows.get(i).select("th")
                            .first().text().toLowerCase().equals("energy")) {
                        return valueFromSingleRow(rows.get(i + 1));
                    } else if (rows.get(i).select("th")
                            .first().text().toLowerCase().equals("energy kcal")) {
                        return valueFromSingleRow(rows.get(i));
                    }
                }}
            return null;
        } catch(Exception e) {
            throw new ProductParsingException("Failed to retrieve nutrition information: " + e.getMessage());
        }
    }

    private Integer valueFromSingleRow(Element row) {
        return Integer.parseInt(row.select("td").first().text().replaceAll("[^0-9, ^.]", ""));
    }
}
