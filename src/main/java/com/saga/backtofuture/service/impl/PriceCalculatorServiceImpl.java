package com.saga.backtofuture.service.impl;

import com.saga.backtofuture.service.PriceCalculatorService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author shantanuK
 */
@Service
public class PriceCalculatorServiceImpl implements PriceCalculatorService {

    private static final String SAGA_MOVIE_1 = "BACK TO THE FUTURE 1";
    private static final String SAGA_MOVIE_2 = "BACK TO THE FUTURE 2";
    private static final String SAGA_MOVIE_3 = "BACK TO THE FUTURE 3";

    private static final Integer SAGA_DVD_COST = 15;
    private static final Integer NON_SAGA_DVD_COST = 20;

    private static final Set<String> STANDARD_SET = new HashSet(Arrays.asList(SAGA_MOVIE_1, SAGA_MOVIE_2, SAGA_MOVIE_3));

    private static final Logger LOGGER = Logger.getLogger(PriceCalculatorServiceImpl.class.getName());

    @Override
    public Integer calculateTotalPrice(List<String> inputMovieList) {
        LOGGER.info("Calculate total price of all dvds");
        Map<String, Integer> sagaMoviesMap = new HashMap();
        Map<String, Integer> nonSagaMoviesMap = new HashMap();
        for (String movieName : inputMovieList) {
            String movieNameInUpperCase = movieName.toUpperCase();
            if (STANDARD_SET.contains(movieNameInUpperCase)) {
                // If it's a saga movie
                if (sagaMoviesMap.containsKey(movieNameInUpperCase)) {
                    sagaMoviesMap.replace(movieNameInUpperCase, sagaMoviesMap.get(movieNameInUpperCase) + SAGA_DVD_COST);
                } else {
                    sagaMoviesMap.put(movieNameInUpperCase, SAGA_DVD_COST);
                }
            } else {
                // If it's not a saga movie
                if (nonSagaMoviesMap.containsKey(movieNameInUpperCase)) {
                    nonSagaMoviesMap.replace(movieNameInUpperCase, nonSagaMoviesMap.get(movieNameInUpperCase) + NON_SAGA_DVD_COST);
                } else {
                    nonSagaMoviesMap.put(movieNameInUpperCase, NON_SAGA_DVD_COST);
                }
            }
        }
        Integer discountInPercent = computeDiscountInPercent(sagaMoviesMap);
        return computeTotalPrice(sagaMoviesMap, discountInPercent, nonSagaMoviesMap);
    }

    /**
     * The method to calculate discount for Saga movies.
     *
     * @param sagaMoviesMap the map of saga movies
     * @return the discount in percent
     */
    private Integer computeDiscountInPercent(Map<String, Integer> sagaMoviesMap) {
        Integer differentSagaMovies = 0;
        differentSagaMovies = sagaMoviesMap.containsKey(SAGA_MOVIE_1) ? differentSagaMovies + 1 : differentSagaMovies;
        differentSagaMovies = sagaMoviesMap.containsKey(SAGA_MOVIE_2) ? differentSagaMovies + 1 : differentSagaMovies;
        differentSagaMovies = sagaMoviesMap.containsKey(SAGA_MOVIE_3) ? differentSagaMovies + 1 : differentSagaMovies;

        Integer discount = 0;
        if (differentSagaMovies == 2) {
            discount = 10;
        } else if (differentSagaMovies == 3) {
            discount = 20;
        }
        LOGGER.log(Level.FINE, "Discount in percent: {0}", discount);
        return discount;
    }

    /**
     * The method to compute total price for all the dvds.
     *
     * @param sagaMoviesMap    the map of saga movies
     * @param discountToApply  the discount to be applied
     * @param nonSagaMoviesMap the map of non-saga movies
     * @return the total price after applying the discount
     */
    private Integer computeTotalPrice(Map<String, Integer> sagaMoviesMap, Integer discountToApply, Map<String, Integer> nonSagaMoviesMap) {
        Integer sagaMoviesTotalAmount = 0;
        sagaMoviesTotalAmount = sagaMoviesMap.containsKey(SAGA_MOVIE_1) ? sagaMoviesMap.get(SAGA_MOVIE_1) : sagaMoviesTotalAmount;
        sagaMoviesTotalAmount = sagaMoviesMap.containsKey(SAGA_MOVIE_2) ? sagaMoviesTotalAmount + sagaMoviesMap.get(SAGA_MOVIE_2) : sagaMoviesTotalAmount;
        sagaMoviesTotalAmount = sagaMoviesMap.containsKey(SAGA_MOVIE_3) ? sagaMoviesTotalAmount + sagaMoviesMap.get(SAGA_MOVIE_3) : sagaMoviesTotalAmount;
        Integer discountedAmount = sagaMoviesTotalAmount * (100 - discountToApply) / 100;
        Integer nonSagaMoviesTotalAmount = 0;
        for (String key : nonSagaMoviesMap.keySet()) {
            nonSagaMoviesTotalAmount += nonSagaMoviesMap.get(key);
        }
        LOGGER.log(Level.FINE, "Saga movies discounted amount: {0}", discountedAmount);
        LOGGER.log(Level.FINE, "Non-Saga movies amount: {0}", nonSagaMoviesTotalAmount);
        return discountedAmount + nonSagaMoviesTotalAmount;
    }
}
