package com.saga.backtofuture.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shantanuK
 */
@ExtendWith(MockitoExtension.class)
class PriceCalculatorServiceImplTest {

    @InjectMocks
    private PriceCalculatorServiceImpl priceCalculatorService;

    @BeforeEach
    void setUp() {
        System.out.println("The test " + PriceCalculatorServiceImplTest.class + " is started");
    }

    @AfterEach
    void tearDown() {
        System.out.println("The test " + PriceCalculatorServiceImplTest.class + " is finished");
    }

    @Test
    void shouldEvaluatePriceForThreeSagaMovies() {
        // Given
        List<String> inputMovieList = new ArrayList<>();
        inputMovieList.add("BACK TO THE FUTURE 1");
        inputMovieList.add("BACK TO THE FUTURE 2");
        inputMovieList.add("BACK TO THE FUTURE 3");

        // When
        Integer actual = priceCalculatorService.calculateTotalPrice(inputMovieList);

        // Then
        Assertions.assertNotEquals(0, actual);
        Assertions.assertEquals(36, actual);
    }

    @Test
    void shouldEvaluatePriceForTwoSagaMovies() {
        // Given
        List<String> inputMovieList = new ArrayList<>();
        inputMovieList.add("BACK TO THE FUTURE 1");
        inputMovieList.add("BACK TO THE FUTURE 3");

        // When
        Integer actual = priceCalculatorService.calculateTotalPrice(inputMovieList);

        // Then
        Assertions.assertNotEquals(0, actual);
        Assertions.assertEquals(27, actual);
    }

    @Test
    void shouldEvaluatePriceForOneSagaMovies() {
        // Given
        List<String> inputMovieList = new ArrayList<>();
        inputMovieList.add("BACK TO THE FUTURE 1");

        // When
        Integer actual = priceCalculatorService.calculateTotalPrice(inputMovieList);

        // Then
        Assertions.assertNotEquals(0, actual);
        Assertions.assertEquals(15, actual);
    }

    @Test
    void shouldEvaluatePriceForFourSagaMovies() {
        // Given
        List<String> inputMovieList = new ArrayList<>();
        inputMovieList.add("BACK TO THE FUTURE 1");
        inputMovieList.add("BACK TO THE FUTURE 2");
        inputMovieList.add("BACK TO THE FUTURE 3");
        inputMovieList.add("BACK TO THE FUTURE 2");

        // When
        Integer actual = priceCalculatorService.calculateTotalPrice(inputMovieList);

        // Then
        Assertions.assertNotEquals(0, actual);
        Assertions.assertEquals(48, actual);
    }

    @Test
    void shouldEvaluatePriceForThreeSagaAndOneNonSagaMovies() {
        // Given
        List<String> inputMovieList = new ArrayList<>();
        inputMovieList.add("BACK TO THE FUTURE 1");
        inputMovieList.add("BACK TO THE FUTURE 2");
        inputMovieList.add("BACK TO THE FUTURE 3");
        inputMovieList.add("LA CHEVRE");

        // When
        Integer actual = priceCalculatorService.calculateTotalPrice(inputMovieList);

        // Then
        Assertions.assertNotEquals(0, actual);
        Assertions.assertEquals(56, actual);
    }
}