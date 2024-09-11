package org.example.utils;

import org.example.model.Product;

import java.util.List;
import java.util.Scanner;

public class Utils {
    public static void printProducts(List<Product> productList) {
        productList
                .forEach(System.out::println);
    }

    public static Product printProductsWithIndexes(List<Product> productList, Scanner scanner) {
        for (int i = 0; i < productList.size(); i++) {
            System.out.println((i + 1) + ". " + productList.get(i));
        }
        System.out.print("Choose product: ");
        int index = scanner.nextInt();
        return productList.get(index - 1);
    }
}
