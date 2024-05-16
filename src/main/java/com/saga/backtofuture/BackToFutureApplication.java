package com.saga.backtofuture;

import com.saga.backtofuture.service.PriceCalculatorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author shantanuK
 */
@SpringBootApplication
public class BackToFutureApplication {

    /**
     * The main method to run the application.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(BackToFutureApplication.class, args);
        PriceCalculatorService priceCalculatorService = applicationContext.getBean(PriceCalculatorService.class);
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your string: ");
            List<String> inputList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.equalsIgnoreCase("END")) {
                    inputList.add(line);
                } else {
                    System.out.println("The total price of all DVDs is: " + priceCalculatorService.calculateTotalPrice(inputList) + " euros.");
                    System.exit(0);
                    scanner.close();
                }
            }
        }
    }

}
