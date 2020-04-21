package com.sainsbury.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.sainsbury.dto.Products;

import java.util.logging.Logger;

public class PrintingTextService {

    private static final Logger logger = Logger.getLogger(PrintingTextService.class.getName());

    private ObjectMapper mapper;

    public PrintingTextService() {
        mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    }

    public void printToConsole(Products products) {
        try {
            System.out.println("------------- List of products in json format --------------");
            System.out.println();
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(products));
            System.out.println();
            System.out.println("------------------------------------------------------------");
        } catch (JsonProcessingException e) {
            logger.severe("Printing service failed to print: " + e.getMessage());
        }
    }
}
