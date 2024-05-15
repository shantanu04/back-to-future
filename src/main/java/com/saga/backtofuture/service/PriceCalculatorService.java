package com.saga.backtofuture.service;

import java.util.List;

/**
 * @author shantanuK
 */
public interface PriceCalculatorService {

    /**
     * The method to calculate the total price of dvds.
     *
     * @param movieList the list of movies
     * @return the total price
     */
    Integer calculateTotalPrice(List<String> movieList);
}
