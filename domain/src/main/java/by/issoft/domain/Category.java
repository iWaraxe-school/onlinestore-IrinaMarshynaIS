package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private String name;
    private List<Product> productList;

    public Category(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }
    public void printAllProduct() {
        System.out.println(String.format("%s","________________________________________________________________________________________"));
        System.out.println("Category " + name + ".");
        System.out.println(String.format("%s","________________________________________________________________________________________"));

        for (Product product : productList) {
            System.out.println(product.toString());
        }

    }
}
